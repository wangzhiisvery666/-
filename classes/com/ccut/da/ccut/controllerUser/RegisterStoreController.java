package ccut.controllerUser;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import ccut.common.CommonResponse;
import ccut.model.VO.StoreApplicationVO;
import ccut.model.request.StoreAuditRequest;
import ccut.service.StoreAuditService;

@RestController
@RequestMapping({"/userStore"})
@Api(tags = {"商家入驻模块"})
@Slf4j
public class RegisterStoreController {


    @Autowired
    StoreAuditService storeAuditService;


    @PostMapping({"/addStoreAudit"})
    @ApiOperation("上传申请2")
    @ApiResponses({@ApiResponse(code = 200, message = "success"), @ApiResponse(code = 100, message = "错误：参数为空"), @ApiResponse(code = 133, message = "信用编号格式错误"), @ApiResponse(code = 131, message = "手机号格式有误"), @ApiResponse(code = 501, message = "内部异常")})
    public CommonResponse<Boolean> addStoreAudit(StoreAuditRequest storeAuditRequest) {
        return this.storeAuditService.addStoreAudit(storeAuditRequest);
    }


    @PostMapping({"/addPicture"})
    @ApiOperation("上传申请1")
    @ApiResponses({@ApiResponse(code = 200, message = "success"), @ApiResponse(code = 100, message = "错误：参数为空"), @ApiResponse(code = 112, message = "图片超过5mb"), @ApiResponse(code = 111, message = "文件类型错误"), @ApiResponse(code = 113, message = "图片上传失败"), @ApiResponse(code = 501, message = "内部异常")})
    public CommonResponse<Boolean> uploadFile(@RequestPart("file") MultipartFile file) {
        return this.storeAuditService.addPicture(file);
    }


    @GetMapping({"/getStatus"})
    @ApiOperation("获取申请状态")
    @ApiResponses({@ApiResponse(code = 200, message = "success"), @ApiResponse(code = 135, message = "未申请")})
    public CommonResponse<Integer> getStatus() {
        return this.storeAuditService.getStatus();
    }


    @GetMapping({"/getApplication"})
    @ApiOperation("查看提交")
    @ApiResponses({@ApiResponse(code = 200, message = "success"), @ApiResponse(code = 135, message = "未申请")})
    public CommonResponse<StoreApplicationVO> putApplication() {
        return this.storeAuditService.putApplication();
    }


    @DeleteMapping({"/deleteApplication"})
    @ApiOperation("撤回申请")
    @ApiResponses({@ApiResponse(code = 200, message = "success")})
    public CommonResponse<StoreApplicationVO> deleteApplication() {
        return this.storeAuditService.deleteApplication();
    }
}


