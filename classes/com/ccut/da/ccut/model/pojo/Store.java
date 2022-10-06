package ccut.model.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

/**
 *
 * @TableName STORE
 */
@TableName(value ="STORE")
@Data
@ApiModel("店铺")
public class Store implements Serializable {
    /**
     * 商家id
     */
    @TableId(type = IdType.AUTO)
    @ApiModelProperty("商家id")
    private Long id;

    /**
     * 所属用户
     */
    @ApiModelProperty("商家id")

    private Integer userId;

    /**
     * 店铺名
     */
    @ApiModelProperty("商家id")

    private String storeName;

    /**
     * 社会信用代码，要和营业执照相同
     */
    @ApiModelProperty("社会信用代码，要和营业执照相同 91410100MA46JBBX3K")

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
     * 营业执照复印件
     */
    @ApiModelProperty("营业执照复印件")

    private String businessLicense;

    /**
     * 1 营业状态 0 停业
     */
    @ApiModelProperty("1 营业状态 0 停业")

    private Integer status;

    /**
     * 店铺描述
     */
    @ApiModelProperty("店铺描述")

    private String storeDescription;

    /**
     * 申请时间 不跟随update修改而变动
     */
    @ApiModelProperty("申请时间 不跟随update修改而变动")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date createDate;

    /**
     * 如果店铺违规 用于店铺的整改意见
     */
    @ApiModelProperty("如果店铺违规 用于店铺的整改意见")
    private  String message;
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
        Store other = (Store) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
                && (this.getUserId() == null ? other.getUserId() == null : this.getUserId().equals(other.getUserId()))
                && (this.getStoreName() == null ? other.getStoreName() == null : this.getStoreName().equals(other.getStoreName()))
                && (this.getCreditCode() == null ? other.getCreditCode() == null : this.getCreditCode().equals(other.getCreditCode()))
                && (this.getContactName() == null ? other.getContactName() == null : this.getContactName().equals(other.getContactName()))
                && (this.getContactPhone() == null ? other.getContactPhone() == null : this.getContactPhone().equals(other.getContactPhone()))
                && (this.getCompanyAddress() == null ? other.getCompanyAddress() == null : this.getCompanyAddress().equals(other.getCompanyAddress()))
                && (this.getBusinessLicense() == null ? other.getBusinessLicense() == null : this.getBusinessLicense().equals(other.getBusinessLicense()))
                && (this.getStatus() == null ? other.getStatus() == null : this.getStatus().equals(other.getStatus()))
                && (this.getStoreDescription() == null ? other.getStoreDescription() == null : this.getStoreDescription().equals(other.getStoreDescription()))
                && (this.getCreateDate() == null ? other.getCreateDate() == null : this.getCreateDate().equals(other.getCreateDate()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getUserId() == null) ? 0 : getUserId().hashCode());
        result = prime * result + ((getStoreName() == null) ? 0 : getStoreName().hashCode());
        result = prime * result + ((getCreditCode() == null) ? 0 : getCreditCode().hashCode());
        result = prime * result + ((getContactName() == null) ? 0 : getContactName().hashCode());
        result = prime * result + ((getContactPhone() == null) ? 0 : getContactPhone().hashCode());
        result = prime * result + ((getCompanyAddress() == null) ? 0 : getCompanyAddress().hashCode());
        result = prime * result + ((getBusinessLicense() == null) ? 0 : getBusinessLicense().hashCode());
        result = prime * result + ((getStatus() == null) ? 0 : getStatus().hashCode());
        result = prime * result + ((getStoreDescription() == null) ? 0 : getStoreDescription().hashCode());
        result = prime * result + ((getCreateDate() == null) ? 0 : getCreateDate().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", userId=").append(userId);
        sb.append(", auditName=").append(storeName);
        sb.append(", creditCode=").append(creditCode);
        sb.append(", contactName=").append(contactName);
        sb.append(", contactPhone=").append(contactPhone);
        sb.append(", companyAddress=").append(companyAddress);
        sb.append(", businessLicense=").append(businessLicense);
        sb.append(", status=").append(status);
        sb.append(", storeDescription=").append(storeDescription);
        sb.append(", createDate=").append(createDate);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}