CREATE TABLE cita (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    titulo VARCHAR(255) NOT NULL,
    fecha_hora TIMESTAMP NOT NULL,
    ubicacion VARCHAR(255),
    descripcion TEXT,
    estado VARCHAR(50)
);

CREATE TABLE historial (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    cita_id BIGINT NOT NULL,
    fecha_evento TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    tipo_evento VARCHAR(100), 
    comentario TEXT,
    FOREIGN KEY (cita_id) REFERENCES cita(id) ON DELETE CASCADE
);
