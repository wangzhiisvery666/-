package ccut.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import ccut.Exception.CustomizeException;
import ccut.common.CommonResponse;
import ccut.common.ErrorEnum;
import ccut.mapper.AddressMapper;
import ccut.model.pojo.Address;
import ccut.model.request.RequestAddress;
import ccut.service.AddressService;
import java.util.HashMap;
import java.util.List;
import ccut.utils.JwtUtil;
import javax.servlet.http.HttpServletRequest;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Slf4j
public class AddressServiceImpl extends ServiceImpl<AddressMapper, Address> implements AddressService {

    @Autowired
    AddressMapper addressMapper;


    @Autowired
    AddressService addressService;


    @Autowired
    AddressServiceImpl addressService1;


    @Transactional(rollbackFor = {Exception.class})
    public CommonResponse<Boolean> addAdress(RequestAddress requestAddress, HttpServletRequest httpServletRequest) {
        if (requestAddress == null) {
            throw new CustomizeException(ErrorEnum.PARAMETER_EXCEPTION_NULL);
        }
        boolean allBlank = StringUtils.isAllBlank(requestAddress.getPermission(), requestAddress.getArea(), requestAddress
                .getCity(), requestAddress.getPhone(), requestAddress.getProvince(), requestAddress
                .getLocation(), requestAddress.getContactname());

        if (allBlank) {
            throw new CustomizeException(ErrorEnum.PARAMETER_EXCEPTION_NULL);
        }


        Address address1 = new Address();
        BeanUtils.copyProperties(requestAddress, address1);
        HashMap<String, String> decodeTokenToUserInfo = JwtUtil.getDecodeTokenToUserInfo(httpServletRequest);
        String s = decodeTokenToUserInfo.get("uid");

        Integer userId = Integer.valueOf(Integer.parseInt(s));
        String permission = requestAddress.getPermission();
        address1.setUserId(userId);


        QueryWrapper<Address> objectQueryWrapper = new QueryWrapper();
        objectQueryWrapper.eq("user_id", userId);

        long count = this.addressService.count((Wrapper) objectQueryWrapper);

        if (count >= 3L) {
            throw new CustomizeException(ErrorEnum.NO_MORE_THAN_THREE_ADDRESSES);
        }


        if (count == 0L) {
            address1.setPermission("1");
        }

        if (count != 0L && permission.equals("1")) {

            this.addressMapper.updatePermissionByUserId(userId);
            address1.setPermission("1");
        }

        if (count != 0L && permission.equals("0")) {
            address1.setPermission("0");
        }
        int row = this.addressMapper.insert(address1);

        if (row < 0) {
            throw new CustomizeException(ErrorEnum.ADDRESS_ADD_FAILED);
        }
        log.info("addAdress添加地址成功");
        return new CommonResponse("地址添加成功", "200", Boolean.valueOf(true));
    }


    @Transactional(rollbackFor = {Exception.class})
    public CommonResponse<Boolean> deleteAdress(Address address) {
        try {
            Long id = address.getId();
            Integer userId = address.getUserId();
            String permission = address.getPermission();

            this.addressMapper.deleteById(id);

            if (permission != null && permission.equals("1")) {

                QueryWrapper<Address> objectQueryWrapper = new QueryWrapper();
                objectQueryWrapper.eq("user_id", userId);
                List<Address> addresses = this.addressMapper.selectList((Wrapper) objectQueryWrapper);

                if (addresses.size() > 0) {
                    Address address1 = addresses.get(0);
                    address1.setPermission("1");
                    this.addressMapper.updateById(address1);
                }
            }
        } catch (Exception e) {
            throw new CustomizeException(ErrorEnum.ADDRESS_DELETE_FAILED);
        }

        log.info("deleteAdress地址删除地址成功");
        return new CommonResponse("地址删除成功", "200", Boolean.valueOf(true));
    }


    public CommonResponse<List<Address>> getAdress(HttpServletRequest request) {
        String s = (String) JwtUtil.getDecodeTokenToUserInfo(request).get("uid");
        int uid = Integer.parseInt(s);

        List<Address> list = null;

        try {
            list = this.addressMapper.selectOrderByPermission(Integer.valueOf(uid));
        } catch (Exception e) {
            throw new CustomizeException(ErrorEnum.FAILED_TO_GET_ADDRESS);
        }


        return new CommonResponse("地址列表", "200", list);
    }


    @Transactional(rollbackFor = {Exception.class})
    public CommonResponse<Boolean> updateAdress(Address requestAddress) {
        Integer uid = requestAddress.getUserId();
        String permission = requestAddress.getPermission();

        if (permission.equals("1")) {
            this.addressMapper.updatePermissionByUserId(uid);
        }
        int row = this.addressMapper.updateById(requestAddress);
        if (row < 0) {
            throw new CustomizeException(ErrorEnum.FAIL_TO_EDIT);
        }
        log.info("修改地址成功");
        return new CommonResponse("地址修改成功", "200", Boolean.valueOf(true));
    }


    public Address getDefaultAddress(int id) {
        QueryWrapper<Address> QueryWrapper = new QueryWrapper();
        QueryWrapper.eq("user_id", Integer.valueOf(id));
        QueryWrapper.eq("permission", Character.valueOf('1'));

        return (Address) this.addressMapper.selectOne((Wrapper) QueryWrapper);
    }


    public CommonResponse<Boolean> getAddressSum(HttpServletRequest request) {
        HashMap<String, String> decodeTokenToUserInfo = JwtUtil.getDecodeTokenToUserInfo(request);
        String s = decodeTokenToUserInfo.get("uid");
        int userId = Integer.parseInt(s);


        QueryWrapper<Address> objectQueryWrapper = new QueryWrapper();
        objectQueryWrapper.eq("user_id", Integer.valueOf(userId));

        long count = this.addressService.count((Wrapper) objectQueryWrapper);

        return new CommonResponse("地址个数获取成功", "200", Boolean.valueOf((count >= 3L)));
    }
}

