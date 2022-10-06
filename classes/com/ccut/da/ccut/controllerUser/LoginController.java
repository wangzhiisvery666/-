package ccut.controllerUser;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
 import io.swagger.annotations.ApiResponse;
 import io.swagger.annotations.ApiResponses;
 import javax.servlet.http.HttpServletRequest;

 import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ccut.common.CommonResponse;
import ccut.model.VO.CompleteLoginVo;
import ccut.service.impl.UserServiceImpl;


@RestController
@RequestMapping({"/login"})
@Api(tags = {"登陆模块"})
public class LoginController {


    @Autowired
    UserServiceImpl userService;

    @GetMapping({"/verify"})
    @ApiResponses({@ApiResponse(code = 108, message = "用户名为空"),
            @ApiResponse(code = 109, message = "密码为空"),
            @ApiResponse(code = 110, message = "用户名或密码错误")})
    @ApiOperation("登陆接口")
    public CommonResponse<CompleteLoginVo> doLogin(String username, String password, HttpServletRequest request) {

        return this.userService.login(username, password, request);

    }


    @GetMapping({"/out"})
    @ApiResponses({@ApiResponse(code = 200, message = "成功退出")})
    @ApiOperation("退出接口")
 public CommonResponse<Boolean> logOut(HttpServletRequest request) {

        return this.userService.logOut(request);
    }

    @GetMapping({"/verificationPhone"})
    @ApiOperation("找回密码1先获取验证码")
    @ApiResponses({@ApiResponse(code = 200, message = "验证码返回成功"),
            @ApiResponse(code = 100, message = "参数为空"),
            @ApiResponse(code = 131, message = "手机号格式有误"),
            @ApiResponse(code = 130, message = "该电话未绑定账号")})
  public CommonResponse<String> verificationPhone(String phone) {
        return this.userService.verificationPhone(phone);
    }

    @PostMapping({"/retrievePassword"})

    @ApiOperation("找回密码2重置密码")

    @ApiResponses({@ApiResponse(code = 200, message = "成功找回密码"),
            @ApiResponse(code = 104, message = "密码长度小于6"),
            @ApiResponse(code = 100, message = "参数为空"), @ApiResponse(code = 107, message = "验证码错误"),
            @ApiResponse(code = 501, message = "内部异常")})
 public CommonResponse<Boolean> RetrievePassword(String phone, String code, String newPassword) {

        return this.userService.RetrievePassword(phone, code, newPassword);

    }

}
