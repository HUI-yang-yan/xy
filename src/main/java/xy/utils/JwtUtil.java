package xy.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;

import static xy.context.InterceptorContext.EXPIRATION_TIME;
import static xy.context.InterceptorContext.SECRET_KEY;

public class JwtUtil {

    // 生成jwt令牌
    public static String generateToken(Long userId) {
        return Jwts.builder()
                .claim("userId", userId)
                .setSubject(String.valueOf(userId))
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY.getBytes())
                .compact();
    }

    public static boolean validateToken(String token) {
        try {
            getClaims(token);
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
                .setSigningKey(SECRET_KEY.getBytes())
                .parseClaimsJws(token)
                .getBody();
    }

    public static Long getUserId(String token) {
        try {
            Claims claims = Jwts.parser()
                    .setSigningKey(SECRET_KEY.getBytes())
                    .parseClaimsJws(token)
                    .getBody();
            // 假设你在创建 token 时放了 userId 字段（long 类型）
            return claims.get("userId", Long.class);
        } catch (Exception e) {
            return null;
        }
    }
}
