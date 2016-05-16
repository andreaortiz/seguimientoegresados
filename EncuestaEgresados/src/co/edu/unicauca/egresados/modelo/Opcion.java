package co.edu.unicauca.egresados.modelo;

public class Opcion {
	
	private int Opcion_Id;
	private int Pregunta_Id;
	private String Opcion_Enunciado;
	private int Opcion_Cont;
	
	public Opcion()
	{
		
	}

	public Opcion(int opcion_Id, int pregunta_Id, String opcion_Enunciado, int opcion_Cont) 
	{
		super();
		Opcion_Id = opcion_Id;
		Pregunta_Id = pregunta_Id;
		Opcion_Enunciado = opcion_Enunciado;
		Opcion_Cont = opcion_Cont;
	}

	public int getOpcion_Id() {
		return Opcion_Id;
	}

	public void setOpcion_Id(int opcion_Id) {
		Opcion_Id = opcion_Id;
	}

	public int getPregunta_Id() {
		return Pregunta_Id;
	}

	public void setPregunta_Id(int pregunta_Id) {
		Pregunta_Id = pregunta_Id;
	}

	public String getOpcion_Enunciado() 
	{
		return Opcion_Enunciado;
	}

	public void setOpcion_Enunciado(String opcion_Enunciado) {
		Opcion_Enunciado = opcion_Enunciado;
	}

	public int getOpcion_Cont() {
		return Opcion_Cont;
	}

	public void setOpcion_Cont(int opcion_Cont) {
		Opcion_Cont = opcion_Cont;
	}
	
}

