package co.edu.unicauca.egresados.modelo;

public class Usuario {
	
	private int id;
	private String nombreUsuario;
	private String contrasenia;
	private String nombre;
	private String apellido;
	private String correo;
	
	public Usuario()
	{
		
	}
	public Usuario(int Id, String NombreUsuario, String Contraseña, String Nombre, String Apellidos, String Correo)
	{
		this.id = Id;
		this.nombreUsuario = NombreUsuario;
		this.contrasenia = Contraseña;
		this.nombre = Nombre;
		this.apellido = Apellidos;
		this.correo = Correo;
	}
	public int getId() {
		return id;
	}
	public void setId(int usuario_Id) {
		id = usuario_Id;
	}
	public String getNombreUsuario() {
		return nombreUsuario;
	}
	public void setNombreUsuario(String usuario_NombreUsuario) {
		nombreUsuario = usuario_NombreUsuario;
	}
	public String getContrasenia() {
		return contrasenia;
	}
	public void setContrasenia(String usuario_Contraseña) {
		contrasenia = usuario_Contraseña;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String usuario_Nombre) {
		nombre = usuario_Nombre;
	}
	public String getApellido() {
		return apellido;
	}
	public void setApellido(String usuario_Apellido) {
		apellido = usuario_Apellido;
	}
	public String getCorreo() {
		return correo;
	}
	public void setCorreo(String usuario_Correo) {
		correo = usuario_Correo;
	}
	
}
