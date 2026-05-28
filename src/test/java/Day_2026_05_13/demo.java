package Day_2026_05_13;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

//@SpringBootTest
public class demo {

    private static final String SECRET = "0123456789abcdef0123456789abcdef";

    private static final SecretKey KEY =
            Keys.hmacShaKeyFor(SECRET.getBytes(StandardCharsets.UTF_8));



    @Test
    public void genJwt(){
        Map<String, Object> claims = new HashMap<>();
        claims.put("name", "tom");
        claims.put("password", 123);

        String jwt = Jwts.builder()
                .claims(claims)
                .signWith(KEY)
                .expiration(new Date(System.currentTimeMillis() + 3600 * 1000))
                .compact();

        System.out.println(jwt);
    }


    @Test
    public void parseJwt() {
        String jwt = "eyJhbGciOiJIUzI1NiJ9.eyJwYXNzd29yZCI6MTIzLCJuYW1lIjoidG9tIiwiZXhwIjoxNzc5MzU2MTIwfQ.IVrwoOG1tL_NyRXXuzaTDE_4TdKNqki2nLFZsEO14CQ";

        Claims payloads = (Claims) Jwts.parser()
                .verifyWith(KEY)
                .build()
                .parse(jwt)
                .getPayload();

        System.out.println(payloads);


    }
}
