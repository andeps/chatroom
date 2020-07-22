package mon.sof.common.tool.token;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import mon.sof.common.exception.BaseException;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Zoufx
 * @description JSON Web Token认证帮助类
 * @date 2019/11/8
 */
public class JWTHelper {

    /**
     * 创建登录验证token
     *
     * @param userJsonToken
     * @return java.lang.String
     * @author Zoufx
     * @date 2019/11/19
     */
    public static String createToken4Login(String userJsonToken) {
        Algorithm algorithm = Algorithm.HMAC256(UserTokenTypeEnum.TOKEN.getName());
        String token = JWT.create().withHeader(JWTHelper.createTokenHeader("HS256", "JWT"))
                .withClaim("userToken", userJsonToken) // 设置 载荷 Payload
                .withIssuer("systemServer") // 签名是由谁生成 例如 服务器
                .withSubject("login token") // 签名的主题
                // .withNotBefore(new Date()) //定义在什么时间之前，该jwt都是不可用的
                .withAudience("APP") // 签名的观众 也可以理解谁接受签名的
                .withIssuedAt(new Date()) // 生成签名的时间
                .withExpiresAt(JWTHelper.createExpireTime(1))
                .sign(algorithm);
        return token;
    }

    /**
     * 验证登录token
     *
     * @param token
     * @return java.lang.String
     * @author Zoufx
     * @date 2019/11/11
     */
    public static String verifyToken4Login(String token) {
        Algorithm algorithm = Algorithm.HMAC256(UserTokenTypeEnum.TOKEN.getName());
        JWTVerifier verifier = JWT.require(algorithm).withIssuer("systemServer").build(); // 生成解析token的JWTVerifier实例
        DecodedJWT jwt = null;
        try {
            jwt = verifier.verify(token);
        } catch (JWTVerificationException e) {
            throw new BaseException("token验证失败");
        }
        return jwt.getClaim("userToken").asString();
    }

    private static Map<String, Object> createTokenHeader(String alg, String typ) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("alg", alg);
        map.put("typ", typ);

        return map;
    }

    private static Date createExpireTime(int hour) {
        Calendar c = Calendar.getInstance();
        c.add(Calendar.HOUR_OF_DAY, hour);
        return c.getTime();
    }
}
