package ccut.controllerManage;

 import io.swagger.annotations.*;
 import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.web.bind.annotation.GetMapping;
 import org.springframework.web.bind.annotation.PutMapping;
 import org.springframework.web.bind.annotation.RequestMapping;
 import org.springframework.web.bind.annotation.RequestParam;
 import org.springframework.web.bind.annotation.RestController;

 import ccut.common.CommonResponse;
 import ccut.model.pojo.Store;
 import ccut.service.StoreService;
 import java.util.List;

 @RestController
 @RequestMapping({"/StoreManage"})
 @Api(tags = {"店铺管理模块"})
 public class StoreManageController {


   @GetMapping({"/getAllStore"})
   public CommonResponse<List<Store>> getAllStore() {
     return this.storeService.getAllStore();
   }

   @Autowired
   StoreService storeService;

   @PutMapping({"/putStoreStatus"})
   @ApiOperation("修改店铺经营状态 停用 0 或者 启用 1")
   @ApiResponses({
           @ApiResponse(code = 200, message = "success")
   })
   @ApiImplicitParams({@ApiImplicitParam(name = "id", value = "店铺id", required = true), @ApiImplicitParam(name = "message", value = "整改消息", required = true)})
   public CommonResponse<Boolean> putStoreStatus(@RequestParam("id") int id, @RequestParam("message") String message) {
     return this.storeService.putStoreStatus(id, message);
   }
 }

