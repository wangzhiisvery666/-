package ccut.config;

import ccut.interceptor.JWTInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;



@Configuration
@EnableWebMvc
public class InterceptorConfig
        implements WebMvcConfigurer {
    @Autowired
    JWTInterceptor jwtInterceptor;

    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor((HandlerInterceptor) new JWTInterceptor())


                .addPathPatterns(new String[]{"/**"
                }).excludePathPatterns(new String[]{"/LunBo/**", "/login/**", "/register/**", "/admin/**", "/StoreManage/**"
                }).excludePathPatterns(new String[]{"/swagger-resources/**", "/webjars/**", "/v3/**", "/swagger-ui/**"});
    }
}

