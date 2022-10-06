package ccut.model.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;


@TableName("address")
@ApiModel("用户地址")
public class Address implements Serializable {
    @ApiModelProperty("地址id")
    @TableId(type = IdType.AUTO)
    private Long id;
    @ApiModelProperty("详细地址")
    private String location;
    private String permission;
    @ApiModelProperty("所属用户的id")
    private Integer userId;
    @ApiModelProperty("联系人电话")
    private String phone;

    public void setId(Long id) {
        this.id = id;
    }

    @ApiModelProperty("联系人姓名")
    private String contactname;
    @ApiModelProperty("省")
    private String province;
    @ApiModelProperty("市")
    private String city;
    @ApiModelProperty("区")
    private String area;
    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    public void setLocation(String location) {
        this.location = location;
    }

    public void setPermission(String permission) {
        this.permission = permission;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setContactname(String contactname) {
        this.contactname = contactname;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setArea(String area) {
        this.area = area;
    }


    public Long getId() {
        return this.id;
    }

    public String getLocation() {
        return this.location;
    }

    public String getPermission() {
        return this.permission;
    }

    public Integer getUserId() {
        return this.userId;
    }

    public String getPhone() {
        return this.phone;
    }

    public String getContactname() {
        return this.contactname;
    }

    public String getProvince() {
        return this.province;
    }

    public String getCity() {
        return this.city;
    }

    public String getArea() {
        return this.area;
    }


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
        Address other = (Address) that;
        return (((getId() == null) ? (other.getId() == null) : getId().equals(other.getId())) && (
                (getLocation() == null) ? (other.getLocation() == null) : getLocation().equals(other.getLocation())) && (
                (getPermission() == null) ? (other.getPermission() == null) : getPermission().equals(other.getPermission())) && (
                (getUserId() == null) ? (other.getUserId() == null) : getUserId().equals(other.getUserId())) && (
                (getPhone() == null) ? (other.getPhone() == null) : getPhone().equals(other.getPhone())) && (
                (getContactname() == null) ? (other.getContactname() == null) : getContactname().equals(other.getContactname())) && (
                (getProvince() == null) ? (other.getProvince() == null) : getProvince().equals(other.getProvince())) && (
                (getCity() == null) ? (other.getCity() == null) : getCity().equals(other.getCity())) && (
                (getArea() == null) ? (other.getArea() == null) : getArea().equals(other.getArea())));
    }


    public int hashCode() {
        int prime = 31;
        int result = 1;
        result = 31 * result + ((getId() == null) ? 0 : getId().hashCode());
        result = 31 * result + ((getLocation() == null) ? 0 : getLocation().hashCode());
        result = 31 * result + ((getPermission() == null) ? 0 : getPermission().hashCode());
        result = 31 * result + ((getUserId() == null) ? 0 : getUserId().hashCode());
        result = 31 * result + ((getPhone() == null) ? 0 : getPhone().hashCode());
        result = 31 * result + ((getContactname() == null) ? 0 : getContactname().hashCode());
        result = 31 * result + ((getProvince() == null) ? 0 : getProvince().hashCode());
        result = 31 * result + ((getCity() == null) ? 0 : getCity().hashCode());
        result = 31 * result + ((getArea() == null) ? 0 : getArea().hashCode());
        return result;
    }


    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(this.id);
        sb.append(", location=").append(this.location);
        sb.append(", permission=").append(this.permission);
        sb.append(", userId=").append(this.userId);
        sb.append(", phone=").append(this.phone);
        sb.append(", contactname=").append(this.contactname);
        sb.append(", province=").append(this.province);
        sb.append(", city=").append(this.city);
        sb.append(", area=").append(this.area);
        sb.append(", serialVersionUID=").append(1L);
        sb.append("]");
        return sb.toString();
    }
}

