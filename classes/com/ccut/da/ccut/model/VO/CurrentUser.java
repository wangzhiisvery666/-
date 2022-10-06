package ccut.model.VO;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel
@Data
public class CurrentUser {
    @ApiModelProperty("当前用户username")
    private String username;
    @ApiModelProperty("当前用户avatar")
    private String avatar;
    @ApiModelProperty("当前用户sex")
    private String sex;
    @ApiModelProperty("当前用户phoneNumber")
    private String phoneNumber;
    @ApiModelProperty("当前用户defaultAddress")
    private String defaultAddress;

}

