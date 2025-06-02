package com.app.wd.utilidades;

import java.io.Serializable;
import org.springframework.stereotype.Service;

import com.app.wd.security.entity.User;

@Service
public class UserLoginApp implements Serializable{
	
	private static final long serialVersionUID = 4720102170442752495L;
	
	protected User usuarioInfoDTO;

	public User getUsuarioInfoDTO() {
		return usuarioInfoDTO;
	}

	public void setUsuarioInfoDTO(User usuarioInfoDTO) {
		this.usuarioInfoDTO = usuarioInfoDTO;
	}
}
