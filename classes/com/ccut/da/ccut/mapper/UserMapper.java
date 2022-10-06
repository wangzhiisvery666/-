package ccut.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import ccut.model.pojo.User;

@Mapper
public interface UserMapper extends BaseMapper<User> {
  User userIsExist(@Param("username") String paramString1, @Param("password") String paramString2);
  
  int updateAvatarByInt(@Param("id") Integer paramInteger, @Param("newAvatar") String paramString);
  
  int updateUsernameById(@Param("id") Integer paramInteger, @Param("username") String paramString);
  
  int updatePasswordById(@Param("id") Integer paramInteger, @Param("newPassword") String paramString);
  
  int updatePasswordByPhone(@Param("phone") String paramString1, @Param("newPassword") String paramString2);
  
  User selectByPhone(@Param("phone") String paramString);
}
