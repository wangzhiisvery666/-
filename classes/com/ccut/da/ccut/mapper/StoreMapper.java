package ccut.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import ccut.model.pojo.Store;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface StoreMapper extends BaseMapper<Store> {
  List<Store> getAllStore();
  
  int updateStatus(@Param("id") int paramInt, @Param("message") String paramString);
}
