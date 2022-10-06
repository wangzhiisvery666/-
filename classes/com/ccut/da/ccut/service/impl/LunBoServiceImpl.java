package ccut.service.impl;


 import ccut.mapper.LunboMapper;
 import ccut.model.pojo.lunBo;
 import ccut.service.LunBoService;
 import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
 import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.stereotype.Service;


 @Service
 @Slf4j
 public class LunBoServiceImpl
   extends ServiceImpl<LunboMapper, lunBo>
   implements LunBoService
 {


   @Autowired
   LunboMapper lunboMapper;



   public List<String> getUrl() {
     ArrayList<String> strings = new ArrayList<>();
     List<lunBo> list = this.lunboMapper.selectList(null);

     Iterator<lunBo> iterator = list.iterator();
     while (iterator.hasNext()) {
       String url = ((lunBo)iterator.next()).getUrl();
       strings.add(url);
     }
     log.info("====================获取轮播图URL成功==================");
     return strings;
   }
 }

