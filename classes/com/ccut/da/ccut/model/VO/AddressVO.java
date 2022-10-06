package ccut.model.VO;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel("地址")
@Data
public class AddressVO {
    @ApiModelProperty("地址id")
    private Long addressId;
    @ApiModelProperty("地址内容")
    private String location;
    @ApiModelProperty("地址是否为默认 1 为是默认 0 为不是默认")
    private String permission;
    @ApiModelProperty("电话")
    private String phone;
    @ApiModelProperty("联系人姓名")
    private String contactName;
    @ApiModelProperty("省")
    private String province;
    @ApiModelProperty("市")
    private String city;
    @ApiModelProperty("区")
    private String area;
    @ApiModelProperty("所属用户")
    private Integer userId;
}


