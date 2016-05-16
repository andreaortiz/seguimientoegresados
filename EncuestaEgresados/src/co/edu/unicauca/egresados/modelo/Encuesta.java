package co.edu.unicauca.egresados.modelo;

public class Encuesta {
	
	private int Encuesta_Id;
	private String Usuario_Id;
	private String Encuesta_Titulo;
	private String Encuesta_Descripcion;
	private String Encuesta_Estado;
	
	public Encuesta()
	{
		this.Encuesta_Id = -1;
		this.Usuario_Id = "";
		this.Encuesta_Titulo = "";
		this.Encuesta_Descripcion = "";
		this.Encuesta_Estado = "";
	}

	public Encuesta(int encuesta_Id, String usuario_Id, String encuesta_Titulo, String encuesta_Descripcion,
			String encuesta_Estado) {
		super();
		Encuesta_Id = encuesta_Id;
		Usuario_Id = usuario_Id;
		Encuesta_Titulo = encuesta_Titulo;
		Encuesta_Descripcion = encuesta_Descripcion;
		Encuesta_Estado = encuesta_Estado;
	}

	public int getEncuesta_Id() {
		return Encuesta_Id;
	}

	public void setEncuesta_Id(int encuesta_Id) {
		Encuesta_Id = encuesta_Id;
	}

	public String getUsuario_Id() {
		return Usuario_Id;
	}

	public void setUsuario_Id(String usuario_Id) {
		Usuario_Id = usuario_Id;
	}

	public String getEncuesta_Titulo() {
		return Encuesta_Titulo;
	}

	public void setEncuesta_Titulo(String encuesta_Titulo) {
		Encuesta_Titulo = encuesta_Titulo;
	}

	public String getEncuesta_Descripcion() {
		return Encuesta_Descripcion;
	}

	public void setEncuesta_Descripcion(String encuesta_Descripcion) {
		Encuesta_Descripcion = encuesta_Descripcion;
	}

	public String getEncuesta_Estado() {
		return Encuesta_Estado;
	}

	public void setEncuesta_Estado(String encuesta_Estado) {
		Encuesta_Estado = encuesta_Estado;
	}
	
}

