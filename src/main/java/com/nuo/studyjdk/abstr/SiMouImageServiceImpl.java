package com.nuo.studyjdk.abstr;


import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class SiMouImageServiceImpl {

    public static void main(String[] args){
        long tokenTime = 7200;
        Date expiredAt = new Date(System.currentTimeMillis() +
                tokenTime * 1000);
        Date notBefore = new Date(System.currentTimeMillis() - 5 * 1000);
        Algorithm algo = Algorithm.HMAC256("Y5ylhPQsd6zE0my0OtD58DNNE5nOsw6i");
        Map<String, Object> header = new HashMap<>();
        header.put("alg", "HS256");
        String token = JWT.create()
                .withIssuer("2XEPNHaog50MqsjwuuAnRFzGjvv")
                .withHeader(header)
                .withExpiresAt(expiredAt)
                .withNotBefore(notBefore)
                .sign(algo);
        System.out.println("Bearer " + token);
    }

}
