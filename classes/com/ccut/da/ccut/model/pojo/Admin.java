package ccut.model.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;

@TableName("admin")
public class Admin implements Serializable {
  @TableId(type = IdType.AUTO)
  private Integer id;
  private String name;

  public void setId(Integer id) { this.id = id; } private String password; @TableField(exist = false) private static final long serialVersionUID = 1L; public void setName(String name) { this.name = name; } public void setPassword(String password) { this.password = password; }




  public Integer getId() {
    return this.id;
  }


  public String getName() {
    return this.name;
  }


  public String getPassword() {
    return this.password;
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
    Admin other = (Admin) that;
    return (((getId() == null) ? (other.getId() == null) : getId().equals(other.getId())) && (
      (getName() == null) ? (other.getName() == null) : getName().equals(other.getName())) && (
      (getPassword() == null) ? (other.getPassword() == null) : getPassword().equals(other.getPassword())));
  }


  public int hashCode() {
    int prime = 31;
    int result = 1;
    result = 31 * result + ((getId() == null) ? 0 : getId().hashCode());
    result = 31 * result + ((getName() == null) ? 0 : getName().hashCode());
    result = 31 * result + ((getPassword() == null) ? 0 : getPassword().hashCode());
    return result;
  }


  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append(getClass().getSimpleName());
    sb.append(" [");
    sb.append("Hash = ").append(hashCode());
    sb.append(", id=").append(this.id);
    sb.append(", name=").append(this.name);
    sb.append(", password=").append(this.password);
    sb.append(", serialVersionUID=").append(1L);
    sb.append("]");
    return sb.toString();
  }
}
