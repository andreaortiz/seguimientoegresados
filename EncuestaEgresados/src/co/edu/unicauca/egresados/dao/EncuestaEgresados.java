package co.edu.unicauca.egresados.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import co.edu.unicauca.egresados.conexion.Conexion;
import co.edu.unicauca.egresados.modelo.Encuesta;
import co.edu.unicauca.egresados.modelo.Opcion;
import co.edu.unicauca.egresados.modelo.Pregunta;
import co.edu.unicauca.egresados.modelo.Usuario;

public class EncuestaEgresados {
	
	public EncuestaEgresados()
	{
		
	}
	
	public void InsertarEncuesta(Encuesta enc) throws SQLException{
		Conexion objconexion = new Conexion("ENCUESTA");
		String consulta = "INSERT INTO ENCUESTA (EN_TIT,EN_DES,EN_STATE,USU_ID) VALUES ('"+enc.getEncuesta_Titulo()+"','"+enc.getEncuesta_Descripcion()+"','En construcción','"+enc.getUsuario_Id()+"')";
		ResultSet result = null;
		result = objconexion.executeQueryRSET(consulta);
		result.close();
		objconexion.close();
	}
	
	public void EditarEncuesta(Encuesta old, Encuesta nueva) throws SQLException{
		Conexion objconexion = new Conexion("ENCUESTA");
		String consulta = "UPDATE ENCUESTA " +
				          "SET EN_ID = "+nueva.getEncuesta_Id()+"," +
				              "EN_TIT = '"+nueva.getEncuesta_Titulo()+"'," +
				              "EN_DES = '"+nueva.getEncuesta_Descripcion()+"'," +
				              "EN_STATE = '"+nueva.getEncuesta_Estado()+"' " +
				          "WHERE EN_ID = "+old.getEncuesta_Id()+" AND " +
				                 "USU_ID = "+old.getUsuario_Id();
		ResultSet result = null;
		result = objconexion.executeQueryRSET(consulta);
		result.close();
		objconexion.close();
	}
	
	public void EditarPregunta(Pregunta old, Pregunta nueva) throws SQLException{
		Conexion objconexion = new Conexion("ENCUESTA");
		String obli;
		if(nueva.isObligatorio())
		{
			obli = "SI";
		}
		else
		{
			obli = "NO";
		}
		String consulta = "UPDATE PREGUNTA " +
				          "SET PRE_ID = "+nueva.getPregunta_Id()+"," +
				              "PRE_ENUN = '"+nueva.getPregunta_Enunciado()+"'," +
				              "PRE_OBLIGATORIO = '"+obli+"' " +
				          "WHERE PRE_ID = "+old.getPregunta_Id()+" AND " +
				                 "EN_ID = "+old.getEncuesta_Id();
		ResultSet result = null;
		result = objconexion.executeQueryRSET(consulta);
		result.close();
		objconexion.close();
	}
	
	
	public void InsertarPregunta(Pregunta preg) throws SQLException
	{
		Conexion objconexion = new Conexion("ENCUESTA");
		String consulta;
		if(preg.isObligatorio())
		{
			consulta = "INSERT INTO PREGUNTA (EN_ID,PRE_ENUN,PRE_TIPO,PRE_OBLIGATORIO) VALUES ('"+preg.getEncuesta_Id()+"','"+preg.getPregunta_Enunciado()+"','"+preg.getPregunta_Tipo()+"','SI')";
		}
		else
		{
			consulta = "INSERT INTO PREGUNTA (EN_ID,PRE_ENUN,PRE_TIPO,PRE_OBLIGATORIO) VALUES ('"+preg.getEncuesta_Id()+"','"+preg.getPregunta_Enunciado()+"','"+preg.getPregunta_Tipo()+"','NO')";
		}
		ResultSet result = null;
		result = objconexion.executeQueryRSET(consulta);
		result.close();
		objconexion.close();
	}
	public void InsertarOpcion(Opcion opcion) throws SQLException
	{
		Conexion objconexion = new Conexion("ENCUESTA");
		String consulta = "INSERT INTO OPCION (PRE_ID,OP_ENUN,OP_CONT) VALUES ('"+opcion.getPregunta_Id()+"','"+opcion.getOpcion_Enunciado()+"','"+opcion.getOpcion_Cont()+"')";
		ResultSet result = null;
		result = objconexion.executeQueryRSET(consulta);
		result.close();
		objconexion.close();
	}
	public ArrayList<Usuario> CargarUsuarios() throws SQLException
	{
		ArrayList<Usuario> listausuarios = new ArrayList<Usuario>();
		Conexion objconexion = new Conexion("ENCUESTA");
		String consulta = "SELECT * FROM USUARIO";
		ResultSet result = null;
		result = objconexion.executeQueryRSET(consulta);
		try
		{
			while(result.next())
			{
				Usuario user = new Usuario();
				user.setId(Integer.parseInt(result.getString("USU_ID")));
				user.setNombreUsuario(result.getString("USU_NOMUSU"));
				user.setContrasenia(result.getString("USU_PASS"));
				user.setNombre(result.getString("USU_NOMB"));
				user.setApellido(result.getString("USU_APEL"));
				user.setCorreo(result.getString("USU_EMAIL"));
				listausuarios.add(user);
			}
		}catch(Exception e)
		{
			System.out.println("Error");
		}
		result.close();
		objconexion.close();
		return listausuarios;
	}
	public ArrayList<Encuesta> cargarEncuestas(String Usuario_Id) throws SQLException
	{
		ArrayList<Encuesta> listaencuestas = new ArrayList<Encuesta>();
		Conexion objconexion = new Conexion("ENCUESTA");
		String consulta = "SELECT * FROM ENCUESTA WHERE USU_ID = '"+Usuario_Id+"' ORDER BY en_id";
		ResultSet result = null;
		result = objconexion.executeQueryRSET(consulta);
		try
		{
			while(result.next())
			{
				Encuesta encuesta = new Encuesta();
				encuesta.setEncuesta_Id(Integer.parseInt(result.getString("EN_ID")));
				encuesta.setUsuario_Id(result.getString("USU_ID"));
				encuesta.setEncuesta_Titulo(result.getString("EN_TIT"));
				encuesta.setEncuesta_Descripcion(result.getString("EN_DES"));
				encuesta.setEncuesta_Estado(result.getString("EN_STATE"));
				listaencuestas.add(encuesta);
			}
		}catch(Exception e)
		{
			System.out.println("Error");
		}
		result.close();
		objconexion.close();
		return listaencuestas;
	}
	public ArrayList<Pregunta> cargarPreguntas(java.lang.Integer Encuesta_Id) throws SQLException
	{
		ArrayList<Pregunta> listapreguntas = new ArrayList<Pregunta>();
		Conexion objconexion = new Conexion("ENCUESTA");
		String consulta = "SELECT * FROM PREGUNTA WHERE EN_ID = '"+Encuesta_Id+"' ORDER BY pre_id";
		ResultSet result = null;
		result = objconexion.executeQueryRSET(consulta);
		try
		{
			while(result.next())
			{
				Pregunta pregunta = new Pregunta();
				pregunta.setPregunta_Id(Integer.parseInt(result.getString("PRE_ID")));
				pregunta.setEncuesta_Id(Integer.parseInt(result.getString("EN_ID")));
				pregunta.setPregunta_Enunciado(result.getString("PRE_ENUN"));
				pregunta.setPregunta_Tipo(result.getString("PRE_TIPO"));
				if(result.getString("PRE_OBLIGATORIO").equalsIgnoreCase("SI"))
				{
					pregunta.setObligatorio(true);
				}
				else
				{
					pregunta.setObligatorio(false);
				}
				listapreguntas.add(pregunta);
			}
		}catch(Exception e)
		{
			System.out.println("Ocurrio un error");
		}
		result.close();
		objconexion.close();
		return listapreguntas;
	}
	public ArrayList<Opcion> cargarOpciones(int Pregunta_Id) throws SQLException
	{
		ArrayList<Opcion> listaopciones = new ArrayList<Opcion>();
		Conexion objconexion = new Conexion("ENCUESTA");
		String consulta = "SELECT * FROM OPCION WHERE PRE_ID = '"+Pregunta_Id+"' ORDER BY op_id";
		ResultSet result = null;
		result = objconexion.executeQueryRSET(consulta);
		try
		{
			while(result.next())
			{
				Opcion opcion = new Opcion();
				opcion.setOpcion_Id(Integer.parseInt(result.getString("OP_ID")));
				opcion.setPregunta_Id(Integer.parseInt(result.getString("PRE_ID")));
				opcion.setOpcion_Enunciado(result.getString("OP_ENUN"));
				opcion.setOpcion_Cont(Integer.parseInt(result.getString("OP_CONT")));
				
			
				listaopciones.add(opcion);
			}
		}catch(Exception e)
		{
			System.out.println("Error");
		}
		result.close();
		objconexion.close();

	
		return listaopciones;
	}
	public int DevolverPregunta(String enunpregunta) throws SQLException
	{
		int resultado = -1;
		Conexion objconexion = new Conexion("ENCUESTA");
		String consulta = "SELECT * FROM PREGUNTA WHERE PRE_ENUN = '"+enunpregunta+"'";
		ResultSet result = null;
		result = objconexion.executeQueryRSET(consulta);
		try
		{
			while(result.next())
			{
				resultado = Integer.parseInt(result.getString("PRE_ID"));
			}
			
		}catch(Exception e)
		{
			resultado = -1; 
			System.out.println(e.getMessage());
		}
		result.close();
		objconexion.close();
		return resultado;
	}
	public void eliminarPregunta(int id)throws NumberFormatException, SQLException {
		Conexion conexionObj = new Conexion("ENCUESTA");
		String sqlquery2 = "DELETE FROM PREGUNTA WHERE PRE_ID='"+ id + "'";
		ResultSet rset2 = null;
		rset2 = conexionObj.executeQueryRSET(sqlquery2);
		rset2.close();
		conexionObj.close();
	}
	public ArrayList<Usuario> inicioUsuario() throws SQLException {
		ArrayList<Usuario> usuarios = new ArrayList<Usuario>();
		Conexion conexionObj = new Conexion("ENCUESTA");
		String sqlquery2 = "select * from usuario";
		ResultSet rset2 = null;
		rset2 = conexionObj.executeQueryRSET(sqlquery2);
		
		try{
			while (rset2.next()) {
				Usuario u = new Usuario();
				u.setNombreUsuario(rset2.getString("USU_NOMUSU"));
				u.setContrasenia(rset2.getString("USU_PASS"));
				u.setId(Integer.parseInt(rset2.getString("USU_ID")));
				usuarios.add(u);
			}
		}catch(SQLException e){
			e.printStackTrace();
		}
		rset2.close();
		conexionObj.close();

		return usuarios;
	}
	public int numPreguntasByEnc(int enc_id) throws SQLException
	{
		int numPreg = -1;
		Conexion conexionObj = new Conexion("ENCUESTA");
		String sqlquery2 = "select count(*) as numpreguntas from PREGUNTA where EN_ID = "+enc_id;
		ResultSet rset2 = null;
		rset2 = conexionObj.executeQueryRSET(sqlquery2);
		
		try{
			while (rset2.next()) {
				numPreg = Integer.parseInt(rset2.getString("numpreguntas"));
			}
		}catch(SQLException e){
			e.printStackTrace();
		}
		rset2.close();
		conexionObj.close();

		return numPreg;
	}

	public void EditarOpcionePol(Opcion old, String nueva) throws SQLException 
	{
		// TODO Auto-generated method stub
		
	
		
		Conexion objconexion = new Conexion("ENCUESTA");
		String consulta = "UPDATE OPCION " +
				          "SET OP_ID = "+old.getOpcion_Id()+"," +
				              "OP_ENUN = '"+nueva +"' "+
				          "WHERE OP_ID = "+old.getOpcion_Id();
		

		
		
		ResultSet result = null;
		result = objconexion.executeQueryRSET(consulta);
		result.close();
		objconexion.close();
		
	}

	public void EliminarOpcionePol(Opcion oldOpc) throws SQLException 
	{
		Conexion conexionObj = new Conexion("ENCUESTA");
		
		String sqlquery2 = "DELETE FROM OPCION WHERE OP_ID='"+ oldOpc.getOpcion_Id()+ "'" ;
		ResultSet rset2 = null;
		rset2 = conexionObj.executeQueryRSET(sqlquery2);
		rset2.close();
		conexionObj.close();
		
	}
	
	

	}

