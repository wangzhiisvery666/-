package ccut.service;

import ccut.model.pojo.lunBo;
import java.util.List;

import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.stereotype.Service;

@Service
public interface LunBoService extends IService<lunBo> {
  List<String> getUrl();
}
