package ccut.model.VO;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

@ApiModel("店铺申请表")
@Data
public class StoreApplicationVO {
    @ApiModelProperty("店铺名")
    private String auditName;
    @ApiModelProperty("社会信用代码，要和营业执照相同 ")
    private String creditCode;
    @ApiModelProperty("联系人姓名")
    private String contactName;
    @ApiModelProperty("联系人电话")
    private String contactPhone;
    @ApiModelProperty("公司地址用于邮寄合同")
    private String companyAddress;
    @ApiModelProperty("营业执照复印件")
    private String businessLicense;
    @ApiModelProperty("店铺描述")
    private String storeDescription;
    @ApiModelProperty("申请时间 不随修改更新")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createDate;
}
