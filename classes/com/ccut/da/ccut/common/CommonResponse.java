package ccut.common;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("统一传递参数")
public class CommonResponse<T> {
    @ApiModelProperty("状态码说明")
    private String message;

    public void setMessage(String message) {
        this.message = message;
    }

    @ApiModelProperty("状态码")
    private String code;
    @ApiModelProperty("存储不同类型的参数返回前端")
    private T data;

    public void setCode(String code) {
        this.code = code;
    }

    public void setData(T data) {
        this.data = data;
    }

    public boolean equals(Object o) {
        if (o == this) return true;
        if (!(o instanceof CommonResponse)) return false;
       CommonResponse<?> other = (CommonResponse) o;
        if (!other.canEqual(this)) return false;
        Object this$message = getMessage(), other$message = other.getMessage();
        if ((this$message == null) ? (other$message != null) : !this$message.equals(other$message)) return false;
        Object this$code = getCode(), other$code = other.getCode();
        if ((this$code == null) ? (other$code != null) : !this$code.equals(other$code)) return false;
        Object this$data = getData(), other$data = other.getData();
        return !((this$data == null) ? (other$data != null) : !this$data.equals(other$data));
    }

    protected boolean canEqual(Object other) {
        return other instanceof CommonResponse;
    }

    public String toString() {
        return "CommonResponse(message=" + getMessage() + ", code=" + getCode() + ", data=" + getData() + ")";
    }


    public String getMessage() {
        return this.message;
    }

    public String getCode() {
        return this.code;
    }

    public T getData() {
        return this.data;
    }

    public CommonResponse(String message, String code, T data) {
        this.message = message;
        this.code = code;
        this.data = data;
    }
}
