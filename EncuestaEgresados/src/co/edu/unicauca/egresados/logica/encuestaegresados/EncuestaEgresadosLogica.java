package co.edu.unicauca.egresados.logica.encuestaegresados;

import java.sql.SQLException;
import java.util.ArrayList;

import co.edu.unicauca.egresados.dao.EncuestaEgresados;
import co.edu.unicauca.egresados.modelo.Encuesta;
import co.edu.unicauca.egresados.modelo.Opcion;
import co.edu.unicauca.egresados.modelo.Pregunta;
import co.edu.unicauca.egresados.modelo.Usuario;

public class EncuestaEgresadosLogica {
	
	private EncuestaEgresados objEncuestaDAO;
	
	public EncuestaEgresadosLogica()
	{
		objEncuestaDAO = new EncuestaEgresados();
	}
	
	public ArrayList<Pregunta> CargarPreguntas(int Encuesta_Id)
	{
		ArrayList<Pregunta> listapreguntas = new ArrayList<Pregunta>();
		try
		{
			listapreguntas = objEncuestaDAO.cargarPreguntas(Encuesta_Id);
		}catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
		return listapreguntas;
	}
	public ArrayList<Encuesta> CargarEncuestas(String Usuario_Id)
	{
		ArrayList<Encuesta> listaencuestas = new ArrayList<Encuesta>();
		try
		{
			listaencuestas = objEncuestaDAO.cargarEncuestas(Usuario_Id);
		}catch(Exception e)
		{
			System.out.println("Error");
		}
		return listaencuestas;
	}
	
	public boolean InsertarEncuesta(Encuesta enc) {
		boolean result;
		boolean existe = false;
		try {
			ArrayList<Encuesta> encuestas = new ArrayList<Encuesta>();
			encuestas = this.CargarEncuestas(enc.getUsuario_Id());
			for(int i=0;i<encuestas.size();i++){
				if(encuestas.get(i).getEncuesta_Id() == enc.getEncuesta_Id()){
					existe = true;
					i = encuestas.size();
				}
			}
			
			if(existe){
				result = false;
			}
			else{
				objEncuestaDAO.InsertarEncuesta(enc);
				result = true;
			}
			
		} catch (Exception e) {
			System.out.println("Error");
			result = false;
		}
		return result;
	}
	
	public boolean editarEncuesta(Encuesta old, Encuesta nueva){
		boolean result;
		try {
			this.objEncuestaDAO.EditarEncuesta(old,nueva);
			result = true;
			
		} catch (Exception e) {
			System.out.println("Error en edición de encuesta");
			result = false;
		}
		return result;
	}
	
	public Encuesta obtenerEncuesta(int encId, String usuId){
		Encuesta encuesta = null;
		try {
			ArrayList<Encuesta> encuestas = new ArrayList<Encuesta>();
			encuestas = this.CargarEncuestas(usuId);
			for(int i=0;i<encuestas.size();i++){
				if(encuestas.get(i).getEncuesta_Id() == encId && encuestas.get(i).getUsuario_Id().equals(usuId))
				{
					encuesta = new Encuesta();
					encuesta = encuestas.get(i);
					i = encuestas.size();
				}
			}
		} catch (Exception e) {
			System.out.println("Error");
		}
		return encuesta;
	}
	
	public boolean InsertarPregunta(Pregunta preg, ArrayList<String> opciones)
	{
		boolean result;
		boolean esta = false;
		try
		{
			ArrayList<Pregunta> preguntas = new ArrayList<Pregunta>();
			preguntas = CargarPreguntas(preg.getEncuesta_Id());
			for(int i=0;i<preguntas.size();i++)
			{
				if(preguntas.get(i).getPregunta_Enunciado().equalsIgnoreCase(preg.getPregunta_Enunciado()))
				{
					esta = true;
					i = preguntas.size();
				}
			}
			if(esta)
			{
				result = false;
			}
			else
			{
				if(opciones.size() == 0)
				{
					objEncuestaDAO.InsertarPregunta(preg);
					result = true;
				}
				else
				{
					objEncuestaDAO.InsertarPregunta(preg);
					int resultado = objEncuestaDAO.DevolverPregunta(preg.getPregunta_Enunciado());
					if(resultado == -1)
					{
						System.out.println("Error");
						result = false;
					}
					else
					{
						for(int i=0;i<opciones.size();i++)
						{
							Opcion objopcion = new Opcion();
							objopcion.setPregunta_Id(resultado);
							objopcion.setOpcion_Enunciado(opciones.get(i));
							objopcion.setOpcion_Cont(0);
							objEncuestaDAO.InsertarOpcion(objopcion);
						}
						result = true;
					}
				}
			}
			
		}catch(Exception e)
		{
			System.out.println("Error");
			result = false;
		}
		return result;
	}
	public void EliminarPregunta(int pregid)
	{
		try
		{
			
			objEncuestaDAO.eliminarPregunta(pregid);
			
		}catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
	}
	public boolean editarPregunta(Pregunta old, Pregunta nueva){
		boolean result;
		try {
			this.objEncuestaDAO.EditarPregunta(old,nueva);
			result = true;
			
		} catch (Exception e) {
			System.out.println("Error en edición de pregunta");
			result = false;
		}
		return result;
	}
	public ArrayList<Usuario> cargarInicioSesion(){
		ArrayList<Usuario> results = new ArrayList<Usuario>();
		try {
			results = this.objEncuestaDAO.inicioUsuario();

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return results;
	}
	public int numPreguntas(int enc_id)
	{
		int result = 0;
		try {
			result = this.objEncuestaDAO.numPreguntasByEnc(enc_id);
			
		} catch (Exception e) {
			System.out.println("Hubo un error en el conteo");
		}
		return result;
	}
	

	///Pol Taborda
	public ArrayList<Opcion> obtenerOpcionesPol(int Pregunta_Id)
	{
		ArrayList<Opcion> listaOpciones = new ArrayList<Opcion>();
		try
		{
	
			listaOpciones = objEncuestaDAO.cargarOpciones(Pregunta_Id);
		}catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
		return listaOpciones;
	}
	
	public boolean editaOpcion(Opcion old, String nueva){
		boolean result;
		try {
			this.objEncuestaDAO.EditarOpcionePol(old,nueva);
			result = true;
			
		} catch (Exception e) {
			System.out.println("Error en edición de Opcio");
			result = false;
		}
		return result;
	}

	public boolean eliminaOpcion(Opcion oldOpc) 
	{
		System.out.println("Oldpcon "+oldOpc);
		boolean result;
		try {
			this.objEncuestaDAO.EliminarOpcionePol(oldOpc);
			result = true;
			
		} catch (Exception e) {
			System.out.println("Error en Eimiinado de Opcion");
			result = false;
		}
		return result;
	} 
	
}
