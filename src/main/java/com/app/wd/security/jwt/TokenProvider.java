package com.app.wd.security.jwt;

import java.security.Key;
import java.security.SignatureException;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.app.wd.security.dto.TokenDTO;
import com.app.wd.security.entity.Token;
import com.app.wd.security.entity.User;
import com.app.wd.security.mapper.TokenMapper;
import com.app.wd.security.repository.TokenRepository;
import com.app.wd.security.repository.UserRepository;
import com.app.wd.utilidades.Constantes;
import com.app.wd.utilidades.Validador;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class TokenProvider {
	
	Key key = Keys.secretKeyFor(SignatureAlgorithm.HS512);

	@Autowired
	TokenRepository tokenRepository;
	
	@Autowired
	UserRepository userRepository;
	
	@SuppressWarnings("deprecation")
	public String createToken(Authentication authentication) {
	    UserDetails userDetails = (UserDetails) authentication.getPrincipal();
	    Date now = new Date();
	    Date expiryDate = new Date(now.getTime() + 3600000);
	    
	    
	 
	    String token = Jwts.builder()
	        .setSubject(userDetails.getUsername())
	        .setIssuedAt(new Date())
	        .setExpiration(expiryDate)
	        .signWith(SignatureAlgorithm.HS512, key)
	        .compact();
	    
	    User userEntity = userRepository.findByUsername(userDetails.getUsername())
	    		.orElseThrow(() -> new RuntimeException("User not found"));
	    
	    TokenDTO tokenDTO = new TokenDTO();
	    tokenDTO.setToken(token);
	    tokenDTO.setExpiradatetoken(expiryDate);
	    tokenDTO.setUsuario(userEntity);
	    tokenDTO.setFecAcceso(now);
	    
	    Token tokenEntity = TokenMapper.INSTANCE.toEntity(tokenDTO);
	    
	    tokenRepository.save(tokenEntity);
	    
	    return token;
	  }


	  public String resolveToken(HttpServletRequest request) {
	    String bearerToken = request.getHeader(Constantes.HEADER_AUTHORIZATION_KEY);
	    if (StringUtils.hasText(bearerToken) && bearerToken.startsWith(Constantes.TOKEN_BEARER_PREFIX)) {
	      return bearerToken.substring(7);
	    }
	    return null;
	  }


	  @SuppressWarnings("deprecation")
	  public boolean validateToken(String token) throws SignatureException {
	    // Check if the token is valid and not expired
	    try {
	    	if(Validador.cadenaEstaVaciaONula(token)) {
	    		return false;
	    	}else {
	    		Jwts.parser().setSigningKey(key).parseClaimsJws(token);	    			    		
	    	}
	      return true;
	    } catch (MalformedJwtException ex) {
	      log.error("Invalid JWT token");
	    } catch (IllegalArgumentException ex) {
	      log.error("JWT claims string is empty");
	    }
	    return false;
	  }

	  @SuppressWarnings("deprecation")
	  public String getUsername(String token) {
	    // Extract the username from the JWT token
	    return Jwts.parser()
	        .setSigningKey(key)
	        .parseClaimsJws(token)
	        .getBody()
	        .getSubject();
	  }
	  
	  public Claims extractAllClaims(String token) {
	        return Jwts
	                .parserBuilder()
	                .setSigningKey(key)
	                .build()
	                .parseClaimsJws(token)
	                .getBody();
	   }
	 
}
