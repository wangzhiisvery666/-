package ccut.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import ccut.model.pojo.StoreAudit;
import java.util.List;

@Mapper
public interface StoreAuditMapper extends BaseMapper<StoreAudit> {
  List<StoreAudit> getAllOrder();

  int updateApproval(@Param("id") int paramInt, @Param("app") String paramString1, @Param("aResults") String paramString2);
  
  int storeName(@Param("auditName") String paramString);
  
  int updatePictrueByDate(@Param("uid") Integer paramInteger, @Param("pic") String paramString);
  
  Integer getStatus(@Param("uid") Integer paramInteger);
  
  StoreAudit getApplication(@Param("uid") Integer paramInteger);
  
  int exceptionDeleteApp(@Param("uid") Integer paramInteger);
}
