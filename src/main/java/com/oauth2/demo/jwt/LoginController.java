package com.oauth2.demo.jwt;

import java.math.BigDecimal;
import java.math.BigInteger;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;



@Controller
public class LoginController {
	
	
	@RequestMapping(value="login",method = RequestMethod.GET)
	@ResponseBody
    public String login() {
		
		//当登录成功时返回给用户一个token
		// 密钥 12345678
        JwtTokenProvider jwtTokenProvider = new JwtTokenProvider("12345678");
        UserClaims claims = new UserClaims();
        claims.setUserName("Tony");
        claims.setEmail("tony@qq.com");
       
        String token = jwtTokenProvider.createToken(claims);
        
        
        Claims userClaims = jwtTokenProvider.parseToken(token);
        System.out.println("解析出来的Toekn内容：" +  userClaims.get("userName"));
      
        
        return token;
             
    }
	
	public static void main(String args[]){
		try{
			Long time1 = (long) 1594179492;
			Long time2 = (long) 1596771492;
			Long time3 = time2 - time1;
			
			System.out.println(time1);
			System.out.println(time2);
			//BigDecimal time = BigDecimal(1000*60*60*24*30);
//			BigDecimal days = new BigDecimal(30);
//			BigDecimal hours = new BigDecimal(24);
//			BigDecimal minuts = new BigDecimal(60);
//			BigDecimal seconds = new BigDecimal(60);
//			BigDecimal milliseconds = new BigDecimal(1000);
//			BigDecimal time = days
//					.multiply(hours)
//					.multiply(minuts)
//					.multiply(seconds)
//					.multiply(milliseconds);
//			long time_ = time.longValue();
			long days_ = time3/ (60 * 60 * 24);
//			//long days_ = time.divide(1000);
			System.out.println("天数"+days_);
			JwtTokenProvider jwtTokenProvider = new JwtTokenProvider("12345678");
			 Claims userClaims = jwtTokenProvider.parseToken("eyJhbGciOiJIUzUxMiIsInppcCI6IkRFRiJ9.eNqqViotTi3yS8xNVbJSCsnPq1TSUUrNTczMAXJLgFyHwkK95PxckGhFgZKVoamlmbm5oYG5YS0AAAD__w.Tf-JW6c4LArSYacfxhtT9aF-Zjbxj5_SgQFlyS09untR2mhd_3rwNCDKYBlXaHTCl5KrovzvBoqNbxph02Ro4Q");
		        System.out.println("解析出来的Toekn内容：" +  userClaims.get("userName"));
		} catch (Exception e){
			System.out.println("token 已经过期");
		}
		
	}

	

}
