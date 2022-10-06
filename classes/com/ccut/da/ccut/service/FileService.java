package ccut.service;

import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import ccut.common.CommonResponse;

@Service
public interface FileService {
  CommonResponse<String> sendAvatar(MultipartFile paramMultipartFile, HttpServletRequest paramHttpServletRequest);
}
