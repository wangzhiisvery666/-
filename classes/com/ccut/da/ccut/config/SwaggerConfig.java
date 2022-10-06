package ccut.config;

import org.springframework.context.annotation.Bean;
 import org.springframework.context.annotation.Configuration;
 import org.springframework.web.servlet.config.annotation.EnableWebMvc;
 import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
 import springfox.documentation.builders.ApiInfoBuilder;
 import springfox.documentation.builders.RequestHandlerSelectors;
 import springfox.documentation.service.ApiInfo;
 import springfox.documentation.spi.DocumentationType;
 import springfox.documentation.spring.web.plugins.Docket;

 @Configuration
 @EnableWebMvc
 public class SwaggerConfig
   implements WebMvcConfigurer
 {
   @Bean
   public Docket admin_api() {
     return (new Docket(DocumentationType.OAS_30))
       .apiInfo(apiInfo2())
       .select()
       .apis(RequestHandlerSelectors.basePackage("com.ccut.dachuang.ccut.controllerManage"))

       .build()
       .groupName("管理系统接口");
   }


   @Bean
   public Docket user_api() {
     return (new Docket(DocumentationType.OAS_30))
       .select()
       .apis(RequestHandlerSelectors.basePackage("com.ccut.dachuang.ccut.controllerUser"))

       .build()
       .groupName("平台接口")

       .apiInfo(apiInfo1());
   }

   private ApiInfo apiInfo1() {
     return (new ApiInfoBuilder())
       .title("平台网站")
       .description("平台网站api")
       .version("1.0")

       .build();
   }
   private ApiInfo apiInfo2() {
     return (new ApiInfoBuilder())
       .title("管理系统")
       .description("管理系统api")
       .version("1.0")
       .build();
   }
 }

