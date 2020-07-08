package com.oauth2.demo.jwt;

import java.math.BigDecimal;
import java.util.Date;

import javax.crypto.spec.SecretKeySpec;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.CompressionCodecs;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class JwtTokenProvider {
	
	
	SecretKeySpec key;
	
	private BigDecimal days = new BigDecimal(30);
	private BigDecimal hours = new BigDecimal(24);
	private BigDecimal minuts = new BigDecimal(60);
	private BigDecimal seconds = new BigDecimal(60);
	private BigDecimal milliseconds = new BigDecimal(1000);
	private BigDecimal time = days
			.multiply(hours)
			.multiply(minuts)
			.multiply(seconds)
			.multiply(milliseconds);
	private Long expire_time = time.longValue();
	 
    /**
     * @param key
     *            密钥(例如：12345678)
     */
    public JwtTokenProvider(String key) {
        SecretKeySpec secretKeySpec = new SecretKeySpec(key.getBytes(), SignatureAlgorithm.HS512.getJcaName());
        this.key = secretKeySpec;
    }
 
    /**
     * 生成token
     * 
     * @return
     */
    public String createToken(Claims claims) {
    	Date expiresDate = new Date(System.currentTimeMillis() + expire_time);// expire_time为token有效时长, 单位毫秒
        String compactJws = Jwts.builder()
        		.setClaims(claims)
        		//.setPayload(JSONObject.toJSONString(claims))
                .compressWith(CompressionCodecs.DEFLATE)
                .setExpiration(expiresDate)
                .signWith(SignatureAlgorithm.HS512, key).compact();
        return compactJws;
    }
 
    /** token转换为 */
    public Claims parseToken(String token) {
        try {
            return Jwts.parser().setSigningKey(key).parseClaimsJws(token).getBody();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
