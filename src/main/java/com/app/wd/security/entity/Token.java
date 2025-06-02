package com.app.wd.security.entity;

import java.io.Serializable;
import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Getter
@Setter
@Table(name = "accesstoken")
public class Token implements Serializable{

  private static final long serialVersionUID = -2915381945776441141L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  
  @Column(name="token")
  private String token;
  
  @Column(name="expiracion_fecha")
  private Date expiradatetoken;
  
  @ManyToOne
  @JoinColumn(name = "usuario_id")
  private User usuario;

  @Column(name = "fec_creacion")
  private Date fecAcceso;
  
}
