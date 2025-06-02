package com.app.wd.security.controller;

import javax.security.auth.login.AccountLockedException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.wd.security.dto.AuthenticationRequest;
import com.app.wd.security.dto.AuthenticationResponseWithUser;
import com.app.wd.security.entity.EntityResponse;
import com.app.wd.security.entity.User;
import com.app.wd.security.jwt.TokenProvider;
import com.app.wd.security.repository.UserRepository;
import com.app.wd.security.service.impl.IUsuarioService;
import com.app.wd.utilidades.Constantes;
import com.app.wd.utilidades.Mensajes;
import com.app.wd.utilidades.UserLoginApp;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@RestController
@CrossOrigin
@RequestMapping(Constantes.REQUEST_URL_AUTH)
public class LoginController {

	@Autowired
	private TokenProvider jwtTokenProvider;

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private UserLoginApp userLoginApp;

	@Autowired
	private UserRepository userRepository;
	

	@PostMapping(value = Constantes.REQUEST_TOKEN_URL)
	public ResponseEntity<?> authenticateUser(@RequestBody AuthenticationRequest authenticationRequest) {
		try {
			Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
					authenticationRequest.getUsername(), authenticationRequest.getPassword()));
			SecurityContextHolder.getContext().setAuthentication(authentication);
			String jwt = jwtTokenProvider.createToken(authentication);

			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			
			User userInfo = userRepository.findByUsername(auth.getName())
					.orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
			
			if (userInfo.getEstadoId() != null && 
				    (userInfo.getEstadoId().getCodValor().equals("B") || userInfo.getEstadoId().getCodValor().equals("I"))) {
				    throw new AccountLockedException("El usuario está bloqueado.");
				}

			userLoginApp.setUsuarioInfoDTO(userInfo);

			User user = (User) authentication.getPrincipal();
			boolean setCambioPassword = user.getCambioPassword();
			AuthenticationResponseWithUser response = new AuthenticationResponseWithUser(jwt, setCambioPassword,
					userInfo);
			return ResponseEntity.ok(response);

		} catch (BadCredentialsException ex) {
			// Credenciales incorrectas (usuario o contraseña)
			EntityResponse<?> errorResponse = new EntityResponse<>();
			errorResponse.setSuccess(false);
			errorResponse.setMessage(Mensajes.TXT_BAD_CREDENCIALES);
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(errorResponse);
		} catch (UsernameNotFoundException ex) {
			// Registro no encontrado
			EntityResponse<?> errorResponse = new EntityResponse<>();
			errorResponse.setSuccess(false);
			errorResponse.setMessage(Mensajes.TXT_USUARIO_NOT_FOUND);
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
		} catch (AccountLockedException ex) {
	        // Usuario bloqueado
	        EntityResponse<?> errorResponse = new EntityResponse<>();
	        errorResponse.setSuccess(false);
	        errorResponse.setMessage("Su cuenta está bloqueada. Por favor, contacte al administrador.");
	        return ResponseEntity.status(HttpStatus.LOCKED).body(errorResponse);

	    } catch (Exception e) {
			// Otros errores internos del servidor
			EntityResponse<?> errorResponse = new EntityResponse<>();
			errorResponse.setSuccess(false);
			errorResponse.setMessage(Mensajes.TXT_ERROR_APLICACION);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
		}
	}


	@PostMapping(value = Constantes.REQUEST_LOGOUT)
	public ResponseEntity<?> logout(HttpServletRequest request, HttpServletResponse response) {
		try {
			new SecurityContextLogoutHandler().logout(request, response,
					SecurityContextHolder.getContext().getAuthentication());
			return ResponseEntity.ok(HttpStatus.OK);
		} catch (Exception e) {
			// Otros errores internos del servidor
			EntityResponse<?> errorResponse = new EntityResponse<>();
			errorResponse.setSuccess(false);
			errorResponse.setMessage(Mensajes.TXT_ERROR_APLICACION);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
		}
	}
}