package ccut.model.VO;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel("完成登陆的展示")
@Data
public class CompleteLoginVo {
    @ApiModelProperty("用户名")
    private String name;
    @ApiModelProperty("头像")
    private String avatar;
    @ApiModelProperty("店铺状态")
    private Integer status;
    @ApiModelProperty("token")
    private String token;
}

