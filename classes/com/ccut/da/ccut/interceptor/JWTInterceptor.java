package ccut.interceptor;

import com.auth0.jwt.exceptions.AlgorithmMismatchException;
 import com.auth0.jwt.exceptions.SignatureVerificationException;
 import com.auth0.jwt.exceptions.TokenExpiredException;
 import com.auth0.jwt.interfaces.Claim;
 import com.auth0.jwt.interfaces.DecodedJWT;
 import javax.servlet.http.HttpServletRequest;
 import javax.servlet.http.HttpServletResponse;
 import org.slf4j.Logger;
 import org.slf4j.LoggerFactory;
 import org.springframework.stereotype.Component;
 import org.springframework.web.servlet.HandlerInterceptor;

import ccut.Exception.CustomizeException;
import ccut.common.BaseThreadLocal;
import ccut.common.ErrorEnum;
import ccut.utils.JwtUtil;

@Component
 public class JWTInterceptor implements HandlerInterceptor {
   private static final Logger log = LoggerFactory.getLogger(JWTInterceptor.class);

   public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
     response.setHeader("Access-Control-Allow-Origin", "*");
     response.setHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS");
     response.setHeader("Access-Control-Max-Age", "3600");
     response.setHeader("Access-Control-Allow-Headers", "*");
     response.setHeader("Access-Control-Allow-Credentials", "true");

     log.info("request请求地址path:[{}] uri:[{}]", request.getServletPath(), request.getRequestURI());
     String token = request.getHeader("token");

     log.info("token:" + token);

     if ("".equals(token) || token == null) {
       log.error("发生错误：token为空");
       throw new CustomizeException(ErrorEnum.NOT_LOGGED_IN);
     }

     try {
       DecodedJWT decodedJWT = JwtUtil.decodeToken(token);
       Claim claim = decodedJWT.getClaim("uid");
       Integer integer = claim.asInt();

       BaseThreadLocal.setCurrentId(integer);
     }
     catch (SignatureVerificationException e) {
       log.error("=====================无效签名=========");
       throw new CustomizeException(ErrorEnum.TOKEN_ERROR);
     } catch (TokenExpiredException e) {
       log.error("====================token过期========");
       throw new CustomizeException(ErrorEnum.SESSION_EXPIRED);
     } catch (AlgorithmMismatchException e) {
       log.error("===========token算法不一致============");
       throw new CustomizeException(ErrorEnum.TOKEN_ERROR);
     } catch (Exception e) {
       log.error("================token无效===========");
       throw new CustomizeException(ErrorEnum.TOKEN_ERROR);
     }
     return true;
   }

   private Long getUserId(String userToken) {
     Long userId = null;
     return userId;
   }
   private boolean checkAuth(Long userId, String requestURI) {
     return true;
   }
 }

