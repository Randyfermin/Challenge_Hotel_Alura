package modelo;

public class Usuario {

	private int Id;
	private String NOMBRE_COMPLETO;
	private String UserName;
	private String UserPwd;

	public Usuario(String nombre, String userName, String userPwd) {
		NOMBRE_COMPLETO = nombre;
		UserName = userName;
		UserPwd = userPwd;
	}
	
	public Usuario() {
	}

	public int getId() {
		return Id;
	}

	public void setId(int id) {
		Id = id;
	}

	public String getUserName() {
		return UserName;
	}

	public void setUserName(String userName) {
		UserName = userName;
	}

	public String getUserPwd() {
		return UserPwd;
	}

	public void setUserPwd(String pwd) {
		UserPwd = pwd;
	}

	public String getNOMBRE_COMPLETO() {
		return NOMBRE_COMPLETO;
	}

	public void setNOMBRE_COMPLETO(String nombre) {
		NOMBRE_COMPLETO = nombre;
	}
}
