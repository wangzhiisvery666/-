package ccut.controllerUser;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import ccut.common.CommonResponse;
import java.io.IOException;
import ccut.service.FileService;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@Api(tags = {"上传文件模块"})
@RequestMapping({"/uploadFile"})
@RestController
public class FileController {
    @Autowired
    FileService fileService;

    @PostMapping({"/Avatar"})
    @ApiOperation("上传头像接口")
    @ApiResponses({@ApiResponse(code = 111, message = "头像应为图片类型"), @ApiResponse(code = 112, message = "头像超过5mb"), @ApiResponse(code = 113, message = "头像上传失败")})
    public CommonResponse<String> transAvatar(@RequestPart("img") MultipartFile file, HttpServletRequest request) throws IOException {
        return this.fileService.sendAvatar(file, request);
    }
}

