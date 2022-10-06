package ccut.model.VO;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel("注册功能传递参数")
@Data
public class RegisterVo {
    @ApiModelProperty("用户名 1、不得为空 2、长度不得小于4。 3、不包含特殊字符")
    private String username;
    @ApiModelProperty("用户密码 1、不得为空 2、长度不得小于6。")
    private String password;
    @ApiModelProperty("用户手机号 1、不得为空")
    private String phoneNumber;
    @ApiModelProperty("验证码")
    private String code;

}
