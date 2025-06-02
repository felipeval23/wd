package com.app.wd.security.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.wd.security.dto.TokenDTO;
import com.app.wd.security.entity.Token;


public interface TokenRepository extends JpaRepository<Token, Long> {
	
	Token save(TokenDTO tokenDTO);

	Token findByToken(String token);
}
