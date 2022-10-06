package ccut.service.impl;

import ccut.mapper.StoreAuditMapper;
import ccut.mapper.UserMapper;
import ccut.model.VO.CurrentUser;
import ccut.model.pojo.Address;
import ccut.model.pojo.User;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import ccut.Exception.CustomizeException;
import ccut.common.BaseThreadLocal;
import ccut.common.CommonResponse;
import ccut.common.ErrorEnum;
import ccut.model.VO.CompleteLoginVo;
import ccut.model.VO.RegisterVo;
import ccut.service.AddressService;
import ccut.service.UserService;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;
import ccut.utils.CommonUtils;
import ccut.utils.JwtUtil;
import javax.servlet.http.HttpServletRequest;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Autowired
    UserMapper userMapper;


    @Autowired
    RedisTemplate<String, String> redisTemplate;


    @Autowired
    AddressService addressService;

    @Autowired
    StoreAuditMapper storeAuditMapper;


    public CommonResponse<Boolean> addUser(RegisterVo registerVo) {
        if (registerVo == null) {
            throw new CustomizeException(ErrorEnum.PARAMETER_EXCEPTION_NULL);
        }
        User user = new User();
        String username = registerVo.getUsername();
        String phoneNumber = registerVo.getPhoneNumber();

        String password = CommonUtils.md5(registerVo.getPassword());
        user.setUsername(username);
        user.setPhoneNumber(phoneNumber);
        user.setPassword(password);


        CommonUtils.checkPhone(phoneNumber);

        checkUsernameAndPhoneNumberAndPassword(username, password, phoneNumber);

        checkUsername(username);

        String key = username + ":" + phoneNumber;
        log.info("==============={}", key);

        String code = (String) this.redisTemplate.opsForValue().get(key);
        log.info("==============={}", code);
        log.info("=============={}", registerVo.getCode());
        if (code != null && code.equals(registerVo.getCode())) {
            log.info("验证码验证成功");
        } else {
            throw new CustomizeException(ErrorEnum.VERIFICATION_CODE_ERROR);
        }


        int insert = this.userMapper.insert(user);
        if (insert == 0) {
            throw new CustomizeException(ErrorEnum.SQL_ERROR);
        }
        log.info("==============成功注册一名用户=================");
        return new CommonResponse("success", "200", Boolean.valueOf(true));
    }


    public CommonResponse<String> getVerificationCode(String username, String password, String phoneNumber) {
        checkUsernameAndPhoneNumberAndPassword(username, password, phoneNumber);


        String code = CommonUtils.geCode();
        String key = username + ":" + phoneNumber;

        this.redisTemplate.opsForValue().set(key, code, 180L, TimeUnit.SECONDS);
        log.info("==================验证码获取成功:{}  过期时间为3分钟", code);
        return new CommonResponse("验证码获取成功请,在三分钟之内完成注册", "200", code);
    }


    public CommonResponse<CompleteLoginVo> login(String username, String password, HttpServletRequest request) {
        if (StringUtils.isEmpty(username)) {
            throw new CustomizeException(ErrorEnum.USERNAME_IS_EMPTY);
        }

        if (StringUtils.isEmpty(password)) {
            throw new CustomizeException(ErrorEnum.PASSWORD_IS_EMPTY);
        }


        String pw = CommonUtils.md5(password);

        User user = null;
        try {
            user = this.userMapper.userIsExist(username, pw);
        } catch (RuntimeException e) {
            throw e;
        }

        if (user == null) {
            throw new CustomizeException(ErrorEnum.WRONG_USER_NAME_OR_PASSWORD);
        }

        String token = JwtUtil.createToken(user);
        CompleteLoginVo completeLoginVo = new CompleteLoginVo();
        completeLoginVo.setName(user.getUsername());
        completeLoginVo.setAvatar(user.getAvatar());


        Integer id = user.getId();
        Integer status = this.storeAuditMapper.getStatus(id);
        if (status == null || status <= 0) {
            completeLoginVo.setStatus(3);
        } else {
            completeLoginVo.setStatus(status);
        }


        completeLoginVo.setToken(token);


        String token1 = request.getHeader("token");
        System.out.println(token1);

        if (!"".equals(token1) && JwtUtil.decodeToken(token1).getClaim("uid").asInt().equals(user.getId())) {
            log.info("用户[{}],已经登陆", username);
            throw new CustomizeException(ErrorEnum.THE_USER_IS_LOGGED_IN);
        }
        log.info("欢迎: [{}] 的登陆", user.getUsername());

        return new CommonResponse("登陆成功", "200", completeLoginVo);
    }


    public CommonResponse<Boolean> logOut(HttpServletRequest request) {
        String token = request.getHeader("token");
        return new CommonResponse("退出成功请把token清空", "200", Boolean.TRUE);
    }


    public CommonResponse<Boolean> updateUsername(HttpServletRequest request, String username) {
        if (username == null) {
            throw new CustomizeException(ErrorEnum.PARAMETER_EXCEPTION_NULL);
        }
        HashMap<String, String> decodeTokenToUserInfo = JwtUtil.getDecodeTokenToUserInfo(request);
        String Oid = decodeTokenToUserInfo.get("uid");
        QueryWrapper<User> objectQueryWrapper = new QueryWrapper();
        objectQueryWrapper.eq("USERNAME", username);
        if (this.userMapper.exists((Wrapper) objectQueryWrapper)) {
            log.info("==昵称未改变==");
            throw new CustomizeException(ErrorEnum.PLEASE_ENTER_A_NEW_NICKNAME);
        }

        checkUsername(username);
        int id = Integer.parseInt(Oid);
        this.userMapper.updateUsernameById(id, username);
        log.info("updateUsername昵称修改成功==");
        return new CommonResponse("修改成功", "200", Boolean.TRUE);
    }


    public CommonResponse<CurrentUser> getCurrentUser(HttpServletRequest request) {
        String s = (String) JwtUtil.getDecodeTokenToUserInfo(request).get("uid");

        int id = Integer.parseInt(s);
        User user = userMapper.selectById(id);
        CurrentUser currentUser = new CurrentUser();
        currentUser.setUsername(user.getUsername());
        currentUser.setAvatar(user.getAvatar());
        currentUser.setSex(user.getSex());
        currentUser.setPhoneNumber(user.getPhoneNumber());

        Address defaultAddress = this.addressService.getDefaultAddress(id);
        if (defaultAddress == null) {
            currentUser.setDefaultAddress("");
        }

        if (defaultAddress != null) {
            String allAddress = defaultAddress.getProvince() + defaultAddress.getCity() + defaultAddress.getArea() + defaultAddress.getLocation();
            currentUser.setDefaultAddress(allAddress);
        }
        return new CommonResponse("当前用户信息", "200", currentUser);
    }


    public void checkUsernameAndPhoneNumberAndPassword(String username, String password, String phoneNumber) {
        if (StringUtils.isAnyEmpty(new CharSequence[]{username, password, phoneNumber})) {
            throw new CustomizeException(ErrorEnum.PARAMETER_EXCEPTION_NULL);
        }

        if (password.length() < 6) {
            throw new CustomizeException(ErrorEnum.SHORT_PASSWORD_LENGTH);
        }
        checkUsername(username);
        QueryWrapper<User> userQueryWrapper1 = new QueryWrapper();
        userQueryWrapper1.eq("PHONE_NUMBER", phoneNumber);

        if (this.userMapper.exists((Wrapper) userQueryWrapper1)) {
            log.info("该手机号已绑定账号");
            throw new CustomizeException(ErrorEnum.PHONE_NUMBER_ALREADY_BOUND);
        }
    }


    public void checkUsername(String username) {
        if (username.length() < 4) {
            throw new CustomizeException(ErrorEnum.USER_REGISTRATION_ERROR);
        }


        String regex = "^[0-9a-zA-Z_]{1,}$";
        if (!username.matches(regex)) {
            throw new CustomizeException(ErrorEnum.USERNAME_CONTAINS_SENSITIVE_WORDS);
        }

        QueryWrapper<User> userQueryWrapper = new QueryWrapper();
        userQueryWrapper.eq("USERNAME", username);

        boolean exists = this.userMapper.exists((Wrapper) userQueryWrapper);
        if (exists) {
            log.info("用户名已经存在");
            throw new CustomizeException(ErrorEnum.USERNAME_EXIST);
        }
    }


    public CommonResponse<String> verificationPhone(String phone) {
        CommonUtils.checkPhone(phone);

        User user = this.userMapper.selectByPhone(phone);

        if (user == null) {
            throw new CustomizeException(ErrorEnum.PHONE_NOT_BOUND);
        }

        String code = CommonUtils.geCode();
        this.redisTemplate.opsForValue().set(phone, code, 180L, TimeUnit.SECONDS);
        log.info("找回密码验证码发送成功");
        return new CommonResponse("获取验证码成功", "200", code);
    }


    public CommonResponse<Boolean> updatePassword(String oldPassword, String newPassword1, String newPassword2) {
        boolean allBlank = StringUtils.isAllBlank(new CharSequence[]{oldPassword, newPassword1, newPassword2});

        if (allBlank) {
            throw new CustomizeException(ErrorEnum.PASSWORD_IS_EMPTY);
        }
        Integer id = BaseThreadLocal.getCurrentId();
        oldPassword = CommonUtils.md5(oldPassword);

        User user = userMapper.selectById(id);
        String password = user.getPassword();

        if (!password.equals(oldPassword)) {
            throw new CustomizeException(ErrorEnum.PASSWORD_VERIFICATION_FAILED);
        }

        if (!newPassword1.equals(newPassword2)) {
            throw new CustomizeException(ErrorEnum.TWO_PASSWORDS_ARE_DIFFERENT);
        }


        if (newPassword1.length() < 6) {
            throw new CustomizeException(ErrorEnum.SHORT_PASSWORD_LENGTH);
        }

        String newPassword = CommonUtils.md5(newPassword1);


        int i = this.userMapper.updatePasswordById(id, newPassword);

        if (i < 0) {
            throw new CustomizeException(ErrorEnum.UPDATE_PASSWORD_FAILED);
        }
        log.info("密码修改成功");
        return new CommonResponse("修改成功", "200", Boolean.valueOf(true));
    }


    public CommonResponse<Boolean> RetrievePassword(String phone, String code, String newPassword) {
        if (StringUtils.isAllBlank(new CharSequence[]{phone, code, newPassword})) {
            throw new CustomizeException(ErrorEnum.PARAMETER_EXCEPTION_NULL);
        }

        String s = (String) this.redisTemplate.opsForValue().get(phone);


        if (s == null || !s.equals(code)) {
            throw new CustomizeException(ErrorEnum.VERIFICATION_CODE_ERROR);
        }

        CommonUtils.checkPhone(phone);

        newPassword = CommonUtils.md5(newPassword);

        int i = this.userMapper.updatePasswordByPhone(phone, newPassword);
        if (i < 0) {
            throw new CustomizeException(ErrorEnum.INTERNAL_ERROR);
        }
        return new CommonResponse("成功找回密码", "200", Boolean.valueOf(true));
    }
}
