package com.app.wd.security.service.impl;

public interface IUsuarioService {

	void validateResetToken(String token);

	void resetPassword(String token, String newPassword);
}
