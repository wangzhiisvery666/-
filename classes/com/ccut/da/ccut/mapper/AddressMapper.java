package ccut.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import ccut.model.pojo.Address;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface AddressMapper extends BaseMapper<Address> {
  int updatePermissionByUserId(@Param("uid") Integer paramInteger);
  
  @Select({"select * from address WHERE user_id=#{uid} order by  permission desc"})
  List<Address> selectOrderByPermission(@Param("uid") Integer paramInteger);
}

