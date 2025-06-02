package com.app.wd.security.service;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.app.wd.security.entity.User;
import com.app.wd.security.repository.UserRepository;
import com.app.wd.utilidades.Mensajes;


@Configuration
public class CustomUserDetailsService implements UserDetailsService {

  @Autowired
  private UserRepository userRepository;

  @Override
  public UserDetails loadUserByUsername(String username) {
	  Optional<User> userOptional = userRepository.findByUsername(username);
      if (userOptional.isEmpty()) {
          throw new UsernameNotFoundException(Mensajes.TXT_USUARIO_NOT_FOUND + username);
      }
      User user = userOptional.get();
      // Construye y devuelve los detalles del usuario
      return User.builder()
    		  .id(user.getId())
              .username(user.getUsername())
              .password(user.getPassword())
              .roleId(user.getRoleId())
              .cambioPassword(user.getCambioPassword())
              .build();
  }
}
