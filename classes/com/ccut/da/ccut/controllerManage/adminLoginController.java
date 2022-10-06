package ccut.controllerManage;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ccut.common.CommonResponse;
import ccut.model.pojo.Admin;
import ccut.service.AdminService;


@RestController
@Api(tags = {"管理员登陆"})
@RequestMapping({"/admin"})
public class adminLoginController
{
  @ApiOperation("管理员登陆接口")
  @PostMapping({"/login"})
  public CommonResponse<Boolean> adminLogin(@RequestParam("name") String name, @RequestParam("password") String password) {
    Admin admin = new Admin();
    admin.setName(name);
    admin.setPassword(password);
    return this.adminService.adminLogin(admin);
  }

  @Autowired
  AdminService adminService;
}

