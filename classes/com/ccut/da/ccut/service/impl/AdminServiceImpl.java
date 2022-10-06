package ccut.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import ccut.mapper.AdminMapper;
import ccut.model.pojo.Admin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ccut.Exception.CustomizeException;
import ccut.common.CommonResponse;
import ccut.common.ErrorEnum;
import ccut.service.AdminService;


@Service
public class AdminServiceImpl
  extends ServiceImpl<AdminMapper, Admin>
  implements AdminService
{
  @Autowired
  AdminMapper adminMapper;

  public CommonResponse<Boolean> adminLogin(Admin admin) {
     LambdaQueryWrapper<Admin> objectLambdaQueryWrapper = new LambdaQueryWrapper();
     objectLambdaQueryWrapper.eq(Admin::getName, admin.getName());
     objectLambdaQueryWrapper.eq(Admin::getPassword, admin.getPassword());
     boolean exists = this.adminMapper.exists((Wrapper)objectLambdaQueryWrapper);

     if (!exists) {
       throw new CustomizeException(ErrorEnum.WRONG_USER_NAME_OR_PASSWORD);
     }
     return new CommonResponse("登陆成功", "200", Boolean.valueOf(true));
   }
 }
