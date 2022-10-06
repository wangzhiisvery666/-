package ccut.model.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 *
 * @TableName store_audit
 */
@TableName(value ="store_audit")
@Data
@ApiModel("店铺审核表")
public class StoreAudit implements Serializable {
    /**
     * 店铺审核表id
     */
    @ApiModelProperty("店铺审核表id")
    @TableId(type = IdType.AUTO)
    private Long auditId;

    /**
     * 所属用户id
     */
    @ApiModelProperty("所属用户id")
    private Integer userId;

    /**
     * 店铺名
     */
    @ApiModelProperty("店铺名")
    private String auditName;

    /**
     * 社会信用代码，要和营业执照相同
     */
    @ApiModelProperty("社会信用代码，要和营业执照相同 ")
    private String creditCode;

    /**
     * 联系人姓名
     */
    @ApiModelProperty("联系人姓名")
    private String contactName;

    /**
     * 联系人电话
     */
    @ApiModelProperty("联系人电话")
    private String contactPhone;

    /**
     * 公司地址用于邮寄合同
     */
    @ApiModelProperty("公司地址用于邮寄合同")
    private String companyAddress;

    /**
     * 公司所在区域
     */
//    @ApiModelProperty("公司所在区域")
//    private String companyArea;

    /**
     * 营业执照复印件
     */
    @ApiModelProperty("营业执照复印件")
    private String businessLicense;

//    /**
//     * 店铺外景照片
//     */
//    @ApiModelProperty("店铺外景照片")
//    private String storeExterior;
//
//    /**
//     * 店铺内照片
//     */
//    @ApiModelProperty("店铺内照片")
//    private String storePhotos;

    /**
     * 店铺描述
     */
    @ApiModelProperty("店铺描述")
    private String storeDescription;

    /**
     * 用于描述审核状态: 审核中 1 审核通过 2 审核失败 0
     */
    @ApiModelProperty("用于描述审核状态: 审核中 1 审核通过 2 审核失败 3")
    private Integer approvalStatus;

    /**
     * 审核的信息
     */
    @ApiModelProperty("审核的信息 整改结果、或者审核通过等信息")
    private String auditResults;

    /**
     * 申请时间
     */
    @ApiModelProperty("申请时间 不随修改更新")
    private Date createDate;

    /**
     * 审核时间
     */
    @ApiModelProperty("审核时间:通过审核的时候更新")
    private Date updateDate;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        StoreAudit other = (StoreAudit) that;
        return (this.getAuditId() == null ? other.getAuditId() == null : this.getAuditId().equals(other.getAuditId()))
                && (this.getUserId() == null ? other.getUserId() == null : this.getUserId().equals(other.getUserId()))
                && (this.getAuditName() == null ? other.getAuditName() == null : this.getAuditName().equals(other.getAuditName()))
                && (this.getCreditCode() == null ? other.getCreditCode() == null : this.getCreditCode().equals(other.getCreditCode()))
                && (this.getContactName() == null ? other.getContactName() == null : this.getContactName().equals(other.getContactName()))
                && (this.getContactPhone() == null ? other.getContactPhone() == null : this.getContactPhone().equals(other.getContactPhone()))
                && (this.getCompanyAddress() == null ? other.getCompanyAddress() == null : this.getCompanyAddress().equals(other.getCompanyAddress()))
//            && (this.getCompanyArea() == null ? other.getCompanyArea() == null : this.getCompanyArea().equals(other.getCompanyArea()))
                && (this.getBusinessLicense() == null ? other.getBusinessLicense() == null : this.getBusinessLicense().equals(other.getBusinessLicense()))
//            && (this.getStoreExterior() == null ? other.getStoreExterior() == null : this.getStoreExterior().equals(other.getStoreExterior()))
//            && (this.getStorePhotos() == null ? other.getStorePhotos() == null : this.getStorePhotos().equals(other.getStorePhotos()))
                && (this.getStoreDescription() == null ? other.getStoreDescription() == null : this.getStoreDescription().equals(other.getStoreDescription()))
                && (this.getApprovalStatus() == null ? other.getApprovalStatus() == null : this.getApprovalStatus().equals(other.getApprovalStatus()))
                && (this.getAuditResults() == null ? other.getAuditResults() == null : this.getAuditResults().equals(other.getAuditResults()))
                && (this.getCreateDate() == null ? other.getCreateDate() == null : this.getCreateDate().equals(other.getCreateDate()))
                && (this.getUpdateDate() == null ? other.getUpdateDate() == null : this.getUpdateDate().equals(other.getUpdateDate()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getAuditId() == null) ? 0 : getAuditId().hashCode());
        result = prime * result + ((getUserId() == null) ? 0 : getUserId().hashCode());
        result = prime * result + ((getAuditName() == null) ? 0 : getAuditName().hashCode());
        result = prime * result + ((getCreditCode() == null) ? 0 : getCreditCode().hashCode());
        result = prime * result + ((getContactName() == null) ? 0 : getContactName().hashCode());
        result = prime * result + ((getContactPhone() == null) ? 0 : getContactPhone().hashCode());
        result = prime * result + ((getCompanyAddress() == null) ? 0 : getCompanyAddress().hashCode());
//        result = prime * result + ((getCompanyArea() == null) ? 0 : getCompanyArea().hashCode());
        result = prime * result + ((getBusinessLicense() == null) ? 0 : getBusinessLicense().hashCode());
//        result = prime * result + ((getStoreExterior() == null) ? 0 : getStoreExterior().hashCode());
//        result = prime * result + ((getStorePhotos() == null) ? 0 : getStorePhotos().hashCode());
        result = prime * result + ((getStoreDescription() == null) ? 0 : getStoreDescription().hashCode());
        result = prime * result + ((getApprovalStatus() == null) ? 0 : getApprovalStatus().hashCode());
        result = prime * result + ((getAuditResults() == null) ? 0 : getAuditResults().hashCode());
        result = prime * result + ((getCreateDate() == null) ? 0 : getCreateDate().hashCode());
        result = prime * result + ((getUpdateDate() == null) ? 0 : getUpdateDate().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", auditId=").append(auditId);
        sb.append(", userId=").append(userId);
        sb.append(", auditName=").append(auditName);
        sb.append(", creditCode=").append(creditCode);
        sb.append(", contactName=").append(contactName);
        sb.append(", contactPhone=").append(contactPhone);
        sb.append(", companyAddress=").append(companyAddress);
//        sb.append(", companyArea=").append(companyArea);
        sb.append(", businessLicense=").append(businessLicense);
//        sb.append(", storeExterior=").append(storeExterior);
//        sb.append(", storePhotos=").append(storePhotos);
        sb.append(", storeDescription=").append(storeDescription);
        sb.append(", approvalStatus=").append(approvalStatus);
        sb.append(", auditResults=").append(auditResults);
        sb.append(", createDate=").append(createDate);
        sb.append(", updateDate=").append(updateDate);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}