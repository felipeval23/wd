package com.app.wd.security.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.mapstruct.Mapping;
import com.app.wd.security.dto.TokenDTO;
import com.app.wd.security.entity.Token;

@Mapper
public interface TokenMapper {

    TokenMapper INSTANCE = Mappers.getMapper(TokenMapper.class);
	
	@Mapping(target = "id", ignore = true)
	Token toEntity(TokenDTO tokenDTO);

	TokenDTO toDTO(Token token);
}
