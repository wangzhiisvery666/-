package ccut.service;

import com.baomidou.mybatisplus.extension.service.IService;
import ccut.common.CommonResponse;
import ccut.model.pojo.Store;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public interface StoreService extends IService<Store> {
  CommonResponse<List<Store>> getAllStore();
  
  CommonResponse<Boolean> putStoreStatus(int paramInt, String paramString);
}
