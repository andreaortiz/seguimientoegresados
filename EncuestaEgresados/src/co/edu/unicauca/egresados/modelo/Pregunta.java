package co.edu.unicauca.egresados.modelo;

public class Pregunta {
	
	private int Pregunta_Id;
	private int Encuesta_Id;
	private String Pregunta_Enunciado;
	private String Pregunta_Tipo;
	private boolean obligatorio;
	public Pregunta()
	{
		
	}

	public Pregunta(int pregunta_Id, int encuesta_Id, String pregunta_Enunciado, String pregunta_Tipo, boolean obligatorio) {
		super();
		Pregunta_Id = pregunta_Id;
		Encuesta_Id = encuesta_Id;
		Pregunta_Enunciado = pregunta_Enunciado;
		Pregunta_Tipo = pregunta_Tipo;
		this.obligatorio = obligatorio;
	}

	public int getPregunta_Id() {
		return Pregunta_Id;
	}

	public void setPregunta_Id(int pregunta_Id) {
		Pregunta_Id = pregunta_Id;
	}

	public int getEncuesta_Id() {
		return Encuesta_Id;
	}

	public void setEncuesta_Id(int encuesta_Id) {
		Encuesta_Id = encuesta_Id;
	}

	public String getPregunta_Enunciado() {
		return Pregunta_Enunciado;
	}

	public void setPregunta_Enunciado(String pregunta_Enunciado) {
		Pregunta_Enunciado = pregunta_Enunciado;
	}

	public String getPregunta_Tipo() {
		return Pregunta_Tipo;
	}

	public void setPregunta_Tipo(String pregunta_Tipo) {
		Pregunta_Tipo = pregunta_Tipo;
	}

	public boolean isObligatorio() {
		return obligatorio;
	}

	public void setObligatorio(boolean obligatorio) {
		this.obligatorio = obligatorio;
	}
	
}
