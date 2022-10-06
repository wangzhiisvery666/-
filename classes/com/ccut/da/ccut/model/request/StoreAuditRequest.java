package ccut.model.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel("店铺入住参数")
@Data
public class StoreAuditRequest {
    @ApiModelProperty("店铺名")
    private String auditName;
    @ApiModelProperty("社会信用代码，要和营业执照相同 测试用例 91410100MA46JBBX3K")
    private String creditCode;
    @ApiModelProperty("联系人姓名")
    private String contactName;
    @ApiModelProperty("联系人电话")
    private String contactPhone;
    @ApiModelProperty("公司地址用于邮寄合同")
    private String companyAddress;
    @ApiModelProperty("店铺描述")
    private String storeDescription;
}
