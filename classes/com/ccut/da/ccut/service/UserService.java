package ccut.service;

import javax.servlet.http.HttpServletRequest;

import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.stereotype.Service;

import ccut.common.CommonResponse;
import ccut.model.VO.CompleteLoginVo;
import ccut.model.VO.CurrentUser;
import ccut.model.VO.RegisterVo;
import ccut.model.pojo.User;

@Service
public interface UserService extends IService<User> {
  CommonResponse<Boolean> addUser(RegisterVo paramRegisterVo);
  
  CommonResponse<String> getVerificationCode(String paramString1, String paramString2, String paramString3);
  
  CommonResponse<CompleteLoginVo> login(String paramString1, String paramString2, HttpServletRequest paramHttpServletRequest);
  
  CommonResponse<Boolean> logOut(HttpServletRequest paramHttpServletRequest);
  
  CommonResponse<Boolean> updateUsername(HttpServletRequest paramHttpServletRequest, String paramString);
  
  CommonResponse<CurrentUser> getCurrentUser(HttpServletRequest paramHttpServletRequest);
  
  CommonResponse<String> verificationPhone(String paramString);
  
  CommonResponse<Boolean> updatePassword(String paramString1, String paramString2, String paramString3);
  
  CommonResponse<Boolean> RetrievePassword(String paramString1, String paramString2, String paramString3);
}

