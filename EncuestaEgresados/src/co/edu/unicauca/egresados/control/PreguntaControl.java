package co.edu.unicauca.egresados.control;

import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import co.edu.unicauca.egresados.logica.encuestaegresados.EncuestaEgresadosLogica;
import co.edu.unicauca.egresados.modelo.Encuesta;
import co.edu.unicauca.egresados.modelo.Pregunta;

@ManagedBean(name= "pregcontrol")
@SessionScoped
public class PreguntaControl implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private boolean obligatorio;
	private EncuestaEgresadosLogica egresadoslogica;
	
	public PreguntaControl()
	{
		
		egresadoslogica = new EncuestaEgresadosLogica();
	}
	
	public EncuestaEgresadosLogica getEgresadoslogica() {
		return egresadoslogica;
	}

	public void setEgresadoslogica(EncuestaEgresadosLogica egresadoslogica) {
		this.egresadoslogica = egresadoslogica;
	}

	public boolean isObligatorio() {
		return obligatorio;
	}

	public void setObligatorio(boolean obligatorio) {
		this.obligatorio = obligatorio;
	}





	
}
