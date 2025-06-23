package xy.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import xy.context.BaseContext;

import java.util.Date;

import static xy.context.InterceptorContext.EXPIRATION_TIME;
import static xy.context.InterceptorContext.SECRET_KEY;

public class JwtUtil {

    //生成jwt令牌
    public static String generateToken(String username ) {

        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
                .compact();
    }

    public static boolean validateToken(String token) {
        try {
            getClaims(token);// 解析没报错说明合法
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public static String getCurrentIdFromToken(String token) {
        return getClaims(token).getSubject();
    }

    private static Claims getClaims(String token) {
        return Jwts.parser()
                .setSigningKey(SECRET_KEY)
                .parseClaimsJws(token)
                .getBody();
    }
}
