package ccut.service.impl;

import javax.servlet.http.HttpServletRequest;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ccut.common.BaseThreadLocal;
import ccut.common.CommonResponse;
import ccut.mapper.UserMapper;
import ccut.service.FileService;
import ccut.utils.CommonUtils;
import ccut.utils.FileUploadUtils;

@Service
@Slf4j
public class FileServiceImpl implements FileService {

  @Autowired
  UserMapper userMapper;

  public CommonResponse<String> sendAvatar(MultipartFile avatar, HttpServletRequest request) {
    try {
      Integer currentId = BaseThreadLocal.getCurrentId();

      String storagePath = "img/avatar/";
      Integer currentId1 = BaseThreadLocal.getCurrentId();

      String avatarName = CommonUtils.md5(currentId1 + "");

      String avatarUrl = FileUploadUtils.upLoadImg(avatar, storagePath, avatarName);
      log.info("头像上传成功地址为: {}", avatarUrl);


      this.userMapper.updateAvatarByInt(currentId, avatarUrl);

      return new CommonResponse("成功返回头像url", "200", avatarUrl);
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }
}

