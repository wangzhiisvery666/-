package ccut.controllerUser;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import ccut.common.CommonResponse;
import ccut.model.VO.CurrentUser;
import ccut.model.pojo.Address;
import ccut.model.request.RequestAddress;
import ccut.service.AddressService;
import ccut.service.UserService;

import java.util.List;


@RestController
@RequestMapping({"/userInfo"})
@Api(tags = {"修改用户信息模块"})
public class UserInfoController {
    @Autowired
    UserService userService;
    @Autowired
    AddressService addressService;

    @PutMapping({"/updateUsername"})
    @ApiOperation("修改昵称接口")
    @ApiImplicitParam(name = "newUsername", value = "传入新昵称", required = true, paramType = "path", dataType = "String", defaultValue = "360700")
    @ApiResponses({
            @ApiResponse(code = 200, message = "success"),
            @ApiResponse(code = 100, message = "参数为空"),
            @ApiResponse(code = 101, message = "用户名长度小于4"),
            @ApiResponse(code = 102, message = "用户名已存在"),
            @ApiResponse(code = 104, message = "密码长度小于6"),
            @ApiResponse(code = 105, message = "用户名包含敏感词"),
            @ApiResponse(code = 117, message = "请输入新昵称")})
    public CommonResponse<Boolean> updateUsername(HttpServletRequest request, @RequestParam("newUsername") String newUsername) {
        return this.userService.updateUsername(request, newUsername);
    }


    @PostMapping({"/addAddress"})
    @ApiOperation("添加地址接口")
    @ApiResponses({@ApiResponse(code = 200, message = "success"), @ApiResponse(code = 119, message = "地址添加失败"), @ApiResponse(code = 126, message = "地址不得超过三个"), @ApiResponse(code = 100, message = "参数为空")})
    public CommonResponse<Boolean> addAddress(@RequestBody RequestAddress requestAddress, HttpServletRequest httpServletRequest) {
        return this.addressService.addAdress(requestAddress, httpServletRequest);
    }


    @DeleteMapping({"/deleteAddress"})
    @ApiOperation("删除地址接口")
    @ApiResponses({@ApiResponse(code = 200, message = "success"), @ApiResponse(code = 120, message = "地址删除失败")})
    public CommonResponse<Boolean> deleteAddress(@RequestBody Address deleteAddress) {
        return this.addressService.deleteAdress(deleteAddress);
    }


    @PutMapping({"/updateAddress"})
    @ApiOperation("修改地址接口")
    @ApiResponses({@ApiResponse(code = 200, message = "success"), @ApiResponse(code = 121, message = "地址修改失败")})
    public CommonResponse<Boolean> updateAddress(@RequestBody Address updateAddress) {
        return this.addressService.updateAdress(updateAddress);
    }


    @GetMapping({"/getAddress"})
    @ApiOperation("获取地址接口")
    @ApiResponses({@ApiResponse(code = 200, message = "获取地址成功"), @ApiResponse(code = 122, message = "获取地址失败")})
    public CommonResponse<List<Address>> getAddress(HttpServletRequest request) {
        return this.addressService.getAdress(request);
    }


    @GetMapping({"/getCurrentUser"})
    @ApiOperation("获取当前的用户信息")
    @ApiResponses({@ApiResponse(code = 200, message = "获取当前用户成功")})
    public CommonResponse<CurrentUser> getCurrentUser(HttpServletRequest request) {
        return this.userService.getCurrentUser(request);
    }


    @GetMapping({"/getAddressSum"})
    @ApiOperation("获取用户地址个数")
    @ApiResponses({@ApiResponse(code = 200, message = "获取用户地址个数成功")})
    public CommonResponse<Boolean> getAddressSum(HttpServletRequest request) {
        return this.addressService.getAddressSum(request);
    }


    @PutMapping({"/updatePassword"})
    @ApiOperation("修改密码")
    @ApiResponses({@ApiResponse(code = 200, message = "获取用户地址个数成功")})
    public CommonResponse<Boolean> updatePassword(String oldPassword, String newPassword1, String newPassword2) {
        return this.userService.updatePassword(oldPassword, newPassword1, newPassword2);
    }
}

