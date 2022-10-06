package ccut.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.web.multipart.MultipartFile;

import ccut.common.CommonResponse;
import ccut.model.VO.StoreApplicationVO;
import ccut.model.pojo.StoreAudit;
import ccut.model.request.StoreAuditRequest;
import java.util.List;

public interface StoreAuditService extends IService<StoreAudit> {
  CommonResponse<Boolean> addStoreAudit(StoreAuditRequest paramStoreAuditRequest);
  
  CommonResponse<Boolean> addPicture(MultipartFile paramMultipartFile);
  
  CommonResponse<List<StoreAudit>> getStoreAudit();
  
  CommonResponse<Boolean> approval(int paramInt, String paramString1, String paramString2);
  
  CommonResponse<Integer> getStatus();
  
  CommonResponse<StoreApplicationVO> putApplication();
  
  CommonResponse<StoreApplicationVO> deleteApplication();
}

