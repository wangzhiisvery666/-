package ccut.service.impl;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import ccut.Exception.CustomizeException;
import ccut.common.CommonResponse;
import ccut.common.ErrorEnum;
import ccut.mapper.StoreMapper;
import ccut.model.pojo.Store;
import ccut.service.StoreService;
import java.util.List;

@Slf4j
@Service
public class StoreServiceImpl  extends ServiceImpl<StoreMapper, Store> implements StoreService {


    @Autowired
    StoreMapper storeMapper;


    public CommonResponse<List<Store>> getAllStore() {
        List<Store> allStore = storeMapper.getAllStore();
        log.info("获取店铺列表成功");
        return new CommonResponse("查看列表成功", "200", allStore);
    }


    public CommonResponse<Boolean> putStoreStatus(int id, String message) {
        int i = this.storeMapper.updateStatus(id, message);
        if (i <= 0) {
            throw new CustomizeException(ErrorEnum.INTERNAL_ERROR);
        }
        log.info("id 为: {} 的店铺已经停用", Integer.valueOf(id));
        return new CommonResponse("商家停止营业", "200", Boolean.valueOf(true));
    }
}
