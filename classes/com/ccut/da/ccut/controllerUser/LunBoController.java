package ccut.controllerUser;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import ccut.common.CommonResponse;
import ccut.service.LunBoService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;




@RestController
@RequestMapping({"/LunBo"})
@Api(tags = {"轮播图"})
public class LunBoController
{
  @Autowired
  LunBoService lunBoService;

  @ApiOperation("获取轮播图url接口 ")
  @ApiResponses({@ApiResponse(response = CommonResponse.class, message = "成功后返回url的集合", code = 200)})
  @GetMapping({"/getUrl"})
  public CommonResponse<List<String>> getUrl() {
    List<String> strings = this.lunBoService.getUrl();
    return new CommonResponse("success", "200", strings);
  }
}

