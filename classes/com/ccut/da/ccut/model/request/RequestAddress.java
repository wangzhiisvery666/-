package ccut.model.request;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
 import io.swagger.annotations.ApiModel;
 import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

 @TableName("address")
 @ApiModel("添加的地址")
 @Data
 public class RequestAddress implements Serializable {

     @ApiModelProperty("1为默认地址 0为普通")
     private String permission;
     @ApiModelProperty("省")
     private String province;
     @ApiModelProperty("市")
     private String city;
     @ApiModelProperty("县")
     private String area;
     @ApiModelProperty("详细地址")
     private String location;
     @ApiModelProperty("电话")
     private String phone;
     @ApiModelProperty("联系人姓名")
     private String contactname;
     @TableField(exist = false)
     private static final long serialVersionUID = 1L;
 }
