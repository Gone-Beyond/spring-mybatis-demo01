package Day_2026_05_13.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.Map;

/**
 * JWT 工具类
 *
 * 作用：
 * 1. 生成 JWT 令牌
 * 2. 解析 JWT 令牌
 */
@Component
public class JwtUtils {

    /**
     * 私有密钥
     *
     * HS256 要求密钥长度至少 256 bit，也就是 32 字节。
     * 这里这个字符串是 32 个英文字符，UTF-8 下就是 32 字节。
     *
     * 真实项目中不要把密钥写死在代码里，后面可以放到 application.yml 或环境变量中。
     */
    private static final String SECRET = "0123456789abcdef0123456789abcdef";

    /**
     * 令牌有效期：12 小时
     *
     * 单位：毫秒
     */
    private static final long EXPIRE_TIME = 12 * 60 * 60 * 1000L;

    /**
     * 真正用于签名和验签的密钥对象
     */
    private static final SecretKey KEY =
            Keys.hmacShaKeyFor(SECRET.getBytes(StandardCharsets.UTF_8));

    /**
     * 生成 JWT 令牌
     *
     * @param claims 要存入 JWT 第二部分 payload 的业务数据
     * @return JWT 字符串
     */
    public static String generateJwt(Map<String, Object> claims) {
        return Jwts.builder()
                .claims(claims)
                .expiration(new Date(System.currentTimeMillis() + EXPIRE_TIME))
                .signWith(KEY)
                .compact();
    }

    /**
     * 解析 JWT 令牌
     *
     * @param jwt 前端传过来的 JWT 字符串
     * @return JWT 第二部分 payload 中的数据
     */
    public static Claims parseJwt(String jwt) {
        return Jwts.parser()
                .verifyWith(KEY)
                .build()
                .parseSignedClaims(jwt)
                .getPayload();
    }
}