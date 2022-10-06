package ccut.utils;


import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;

import ccut.Exception.CustomizeException;
import ccut.common.ErrorEnum;
import ccut.model.pojo.User;
import java.util.Calendar;
import java.util.HashMap;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;


@Service
public class JwtUtil {
    private static final String SECRET = "!@#$%^&*";
    public static final String UID = "uid";

    public static String createToken(User user) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(10, 1);
        JWTCreator.Builder builder = JWT.create();

        builder.withClaim("uid", user.getId());

        return builder.withExpiresAt(calendar.getTime())
                .sign(Algorithm.HMAC256("!@#$%^&*"));
    }


    public static DecodedJWT decodeToken(String token) {
        if (token == null) {
            throw new CustomizeException(ErrorEnum.NOT_LOGGED_IN);
        }


        JWTVerifier jWTVerifier = JWT.require(Algorithm.HMAC256("!@#$%^&*")).build();
        return jWTVerifier.verify(token);
    }


    public static HashMap<String, String> getDecodeTokenToUserInfo(HttpServletRequest request) {
        String token = request.getHeader("token");

        if (token == null) {
            throw new CustomizeException(ErrorEnum.NOT_LOGGED_IN);
        }


        JWTVerifier jWTVerifier = JWT.require(Algorithm.HMAC256("!@#$%^&*")).build();
        DecodedJWT verify = jWTVerifier.verify(token);

        Claim claim = verify.getClaim("uid");
        Integer userId = claim.asInt();

        HashMap<String, String> objectObjectHashMap = new HashMap<>();
        objectObjectHashMap.put("uid", userId + "");

        return objectObjectHashMap;
    }
}
