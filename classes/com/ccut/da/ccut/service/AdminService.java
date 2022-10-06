package ccut.service;

import com.baomidou.mybatisplus.extension.service.IService;

import ccut.common.CommonResponse;
import ccut.model.pojo.Admin;

public interface AdminService extends IService<Admin> {
  CommonResponse<Boolean> adminLogin(Admin paramAdmin);
}
