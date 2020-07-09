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
//        claims.setUserName("Tony");
//        claims.setEmail("tony@qq.com");
       
        String token = jwtTokenProvider.createToken(claims);
        
        
        Claims userClaims = jwtTokenProvider.parseToken(token);
        System.out.println("解析出来的Toekn内容：" +  userClaims.get("userName"));
      
        
        return token;
             
    }
	
	public static void main(String args[]){
		try{
			
			JwtTokenProvider jwtTokenProvider = new JwtTokenProvider("78345024");
			Claims userClaims = jwtTokenProvider.parseToken("eyJhbGciOiJIUzUxMiIsInppcCI6IkRFRiJ9.eNqqVirNTFGyUjJU0lFKrShQsjI0tTSzMDe0MDarBQAAAP__.LrclOivyjgcxnGdvsKKTuqjJJY9nlppzIfezqS3NabRugWs_zaxxB58rYpPIw-lBkpTj93zOyEbqYlEbjwlimw");
		        System.out.println("内容：" +  userClaims);
		} catch (Exception e){
			System.out.println("token 已经过期");
		}
		
	}

	

}
