package com.app.wd.utilidades;

public class Constantes {

	public static final String REQUEST_URL_AUTH = "/api/auth";

	public static final String REQUEST_TOKEN_URL = "/login";

	public static final String REQUEST_LOGOUT = "/logout";

	public static final String REQUEST_REFRESH_TOKEN = "/refresh";
	

	public static final String AUTHORITIES_KEY = "CLAIM_TOKEN";
	
	//Roles
	public static final String ROLE_ADMIN = "ADMIN";

	public static final String SIGNING_KEY = "gestionFacturaFacil";

	public static final int ACCESS_TOKEN_VALIDITY_SECONDS = 28800;

	public static final String ISSUER_TOKEN = "ISSUER";

	public static final String HEADER_AUTHORIZATION_KEY = "Authorization";

	public static final String TOKEN_BEARER_PREFIX = "Bearer ";
	
	public static final String PREFIX_ESTUDIANTE = "est-";
	
	
	//ENDPOINT APLICACION
	public static final String URL_BASE = "/api/empresa";
	
	//Cambio password
	public static final String ENDPOINT_CAMBIO_PASSWORD_USUARIO_APP = "/cambiar_contrasenia_usuario";
	public static final String PASSWORD_RESET = "/solicitar_reset_pwd";
	public static final String VALIDA_PASSWORD_RESET = "/valida_token_reset_password";
	public static final String RESET_PASSWORD = "/reset_password";
	
	public static final String ENDPOINT_CREAR_USUARIO_APP = "/crear_usuario";
	public static final String ENDPOINT_CREAR_EMPRESA = "/crear_empresa";
	public static final String ENDPOINT_CREAR_FACTURA = "/crear_factura";
	
	public static final String ENDPOINT_CONSULTAR_USUARIO = "/consultar_usuario";
	public static final String ENDPOINT_CONSULTAR_USUARIO_BY_ID = "/consultar_usuario_id";
	public static final String ENDPOINT_EDITAR_USUARIO = "/editar_usuario";
	
	public static final String ENDPOINT_CONSULTAR_EMPRESA = "/consultar_empresa";
	public static final String ENDPOINT_CONSULTAR_EMPRESA_FACTURA = "/consultar_empresa_factura";
	public static final String ENDPOINT_CONSULTAR_DOCENTE_BY_ID = "/consultar_empresa_id";
	
	public static final String ENDPOINT_CONSULTAR_FACTURA = "/consultar_factura";
	public static final String ENDPOINT_CONSULTAR_ESTUDIANTE_BY_ID = "/consultar_factura_id";
	
	public static final String ENDPOINT_CONSULTAR_CIUDADES = "/consultar_ciudades";
	
	public static final String ENDPOINT_EDITAR_MATERIA = "/editar_usuario";
	public static final String ENDPOINT_EDITAT_EMPRESA = "/editar_empresa";
	public static final String ENDPOINT_EDITAR_FACTURA = "/editar_factura";
	
	public static final String ENDPOINT_ELIMINAR_USUARIO = "/eliminar_usuario";
	public static final String ENDPOINT_ELIMINAR_EMPRESA = "/eliminar_empresa";
	public static final String ENDPOINT_ELIMINAR_FACTURA = "/eliminar_factura";
	
	//ENDPOINT MENU APP
	public static final String ENDPOINT_CREAR_CATEGORIA 	= "/crear_categoria";
	public static final String ENDPOINT_CONSULTAR_CATEGORIA = "consultar_categoria";
	public static final String ENDPOINT_CONSULTAR_CATEGORIA_BY_ID = "consultar_categoria_id";
	public static final String ENDPOINT_EDITAR_CATEGORIA 	= "/editar_categoria";
	public static final String ENDPOINT_ELIMINAR_CATEGORIA 	= "/eliminar_categoria";
	
	
	//ENDPOINT LISTA VALORES
	public static final String ENDPOINT_CONSULTAR_TIPOS_DOCUMENTOS = "/consultar_tipos_documentos";
	public static final String ENDPOINT_CONSULTAR_ROLES_APLICACION = "/consultar_roles_aplicacion";
	public static final String ENDPOINT_CONSULTAR_ESTADOS = "/consultar_estados";
	

	public static final String GENERAR_BOLETIN_PDF = "/generar_boletin_pdf";

	public static final String ENDPOINT_DESCARGAR_CSV = "/descargar_xlsx";
	

	public static final String ENDPOINT_UPLOAD_CSV = "/crear_registro";

	public static final String CD_REFERENCIA_ESTUDIANTE = "ESTUDIANTE";

	public static final String CD_ESTADOS_ESTUDIANTES = "CD_ESTADO_ESTUDIANTE";
	public static final String CD_ESTADO_ACTIVO = "A";
	
	public static final String CD_ESTADO_USUARIO_APP = "CD_ESTADO_USUARIO_APP";
	
	public static final String INSTA_DB_PPAL = "instanciaDBPrincipal";
	
	public static final String ENDPOINT_GENERA_LOGIN = "/generar_login";
	
	//ENDPOINT MENU USUARIO
	public static final String ENDPOINT_MENU_APP_USER = "/menu_list";
}
