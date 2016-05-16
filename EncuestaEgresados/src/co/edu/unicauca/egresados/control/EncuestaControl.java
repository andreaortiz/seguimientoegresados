package co.edu.unicauca.egresados.control;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import org.primefaces.model.SelectableDataModel;
import org.primefaces.event.FlowEvent;
import org.primefaces.event.SelectEvent;

import co.edu.unicauca.egresados.conexion.Conexion;
import co.edu.unicauca.egresados.logica.encuestaegresados.EncuestaEgresadosLogica;
import co.edu.unicauca.egresados.modelo.Encuesta;
import co.edu.unicauca.egresados.modelo.Opcion;
import co.edu.unicauca.egresados.modelo.Pregunta;

@ManagedBean(name= "encuestacontrol")
@SessionScoped
public class EncuestaControl implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private EncuestaEgresadosLogica egresadoslogica;
	private String ruta = "/Encuesta/Encuestas.xhtml";
	private ArrayList<Pregunta> preguntas = new ArrayList<Pregunta>();
	private ArrayList<Pregunta> allPreguntas = new ArrayList<Pregunta>();
	private ArrayList<Encuesta> encuestas = new ArrayList<Encuesta>();
	private ArrayList<Opcion> opciones = new ArrayList<Opcion>();
	private ArrayList<String> stopciones = new ArrayList<String>();
	private String enunciado;
	private String tipopregunta;
	private int Encuesta_Id;
	private int Pregunta_Id;
	private String enunopcion;
	private Encuesta enc;
	private Encuesta encEdit;
	private Encuesta oldEnc;
	private Pregunta preg;
	private Pregunta preEdit;
	private Pregunta oldPre;
	private Opcion oldOpc;
	private Opcion opc = new Opcion();;
	private String enunopc;
	private String opcionseleccionada;
	private boolean obligatorio;
	private String opciontemp;
	
	
	public String getOpciontemp() {
		return opciontemp;
	}

	public void setOpciontemp(String opciontemp) {
		this.opciontemp = opciontemp;
	}

	public boolean isObligatorio() {
		return obligatorio;
	}

	public void setObligatorio(boolean obligatorio) {
		this.obligatorio = obligatorio;
	}
	
	public String getEnunopc() {
		return enunopc;
	}

	public void setEnunopc(String enunopc) {
		this.enunopc = enunopc;
	}
	
	public String getOpcionseleccionada() {
		return opcionseleccionada;
	}

	public void setOpcionseleccionada(String opcionseleccionada) {
		this.opcionseleccionada = opcionseleccionada;
	}

	public EncuestaControl()
	{
		enc = new Encuesta();
		encEdit = new Encuesta();
		oldEnc = new Encuesta();
		preg = new Pregunta();
		preEdit = new Pregunta();
		oldPre = new Pregunta();
		egresadoslogica = new EncuestaEgresadosLogica();
		Opcion op = new Opcion();
		op.setOpcion_Enunciado("Hola");
		opciones.add(op);
		enunopc = "";
		//this.stopciones.add("Opcion1");
	}
	
	public EncuestaEgresadosLogica getEgresadoslogica() {
		return egresadoslogica;
	}

	public void setEgresadoslogica(EncuestaEgresadosLogica egresadoslogica) {
		this.egresadoslogica = egresadoslogica;
	}

	public ArrayList<Pregunta> getPreguntas() {
		return preguntas;
	}
	public void setPreguntas(ArrayList<Pregunta> preguntas) {
		this.preguntas = preguntas;
	}
	public ArrayList<Pregunta> getAllPreguntas() {
		return allPreguntas;
	}
	public void setAllPreguntas(ArrayList<Pregunta> preguntas) {
		this.allPreguntas = preguntas;
	}
	public String getRuta() {
		return ruta;
	}
	public void setRuta(String ruta) {
		this.ruta = ruta;
	}
	
	public int exitRuta(){
		this.ruta = "/Encuesta/Encuestas.xhtml";
		return 1;
	}

	public ArrayList<Encuesta> getEncuestas() {
		return encuestas;
	}

	public void setEncuestas(ArrayList<Encuesta> encuestas) {
		this.encuestas = encuestas;
	}
	

	public int getEncuesta_Id() {
		return Encuesta_Id;
	}

	public void setEncuesta_Id(int encuesta_Id) {
		Encuesta_Id = encuesta_Id;
	}
	
	public int getPregunta_Id(){
		return Pregunta_Id;
	}
	
	public void setPregunta_Id(int pregunta_Id){
		Pregunta_Id = pregunta_Id;
	}
	
	public void putOldEncuestaId(Encuesta enc){
		System.out.print("Se asignó el antiguo Id de la encuesta a");		
		//this.Encuesta_Id = Integer.parseInt(enc.getEncuesta_Id());
		//this.oldEnc = this.egresadoslogica.obtenerEncuesta(enc.getEncuesta_Id(), enc.getUsuario_Id());
		this.oldEnc = enc;
		if(this.encEdit.getEncuesta_Estado().equals("En construcción"))
			this.encEdit.setEncuesta_Estado("En construccion");
		//System.out.println("Nuevo Encuesta Estado = "+this.encEdit.getEncuesta_Estado());
	}
	
	public void putOldPreguntaId(Pregunta pre){	
		//this.Encuesta_Id = Integer.parseInt(enc.getEncuesta_Id());
		//this.oldEnc = this.egresadoslogica.obtenerEncuesta(enc.getEncuesta_Id(), enc.getUsuario_Id());
		this.oldPre = pre;
		
	}
	
	public void putOldOpcionId(Opcion opc){
		System.out.print("Si copie la OPCIONNN a oldPre");
		
		
		this.oldOpc = opc;
		
		System.out.println("Click en PutOldOpcionId");
		System.out.println("Antiguo Opcion_Id = "+this.oldOpc.getOpcion_Id());
		System.out.println("Antiguo Opcion_Enunciado = "+this.oldOpc.getOpcion_Enunciado());
	
	}
	
	public String getEnunciado() {
		return enunciado;
	}

	public void setEnunciado(String enunciado) {
		this.enunciado = enunciado;
	}
	
	public String getTipopregunta() {
		return tipopregunta;
	}

	public void setTipopregunta(String tipopregunta) {
		this.tipopregunta = tipopregunta;
	}
	
	public String getEnunopcion() {
		return enunopcion;
	}

	public void setEnunopcion(String enunopcion) {
		this.enunopcion = enunopcion;
	}
	
	public Encuesta getEnc() {
		if(enc == null){
			enc = new Encuesta();
		}
		return enc;
	}

	public void setEnc(Encuesta enc) {
		this.enc = enc;
	}

	
	public Encuesta getEncEdit() {
		return encEdit;
	}

	public void setEncEdit(Encuesta encEdit) {
		this.encEdit = encEdit;
	}
	
	public Pregunta getPreEdit() {
		return preEdit;
	}

	public void setPreEdit(Pregunta preEdit) {
		this.preEdit = preEdit;
	}

	public Pregunta getOldPre() {
		return oldPre;
	}

	public void setOldPre(Pregunta oldPre) {
		this.oldPre = oldPre;
	}
	
	public void encuestaNull(){
		this.encEdit = new Encuesta();
	}

	public Encuesta getOldEnc() {
		return oldEnc;
	}

	public void setOldEnc(Encuesta oldEnc) {
		this.oldEnc = oldEnc;
	}

	public Pregunta getPreg() {
		if(preg == null){
			preg = new Pregunta();
		}
		return preg;
	}

	public void setPreg(Pregunta preg) {
		this.preg = preg;
	}

	public Opcion getOpc() {
		return opc;
	}

	public void setOpc(Opcion opc) {
		this.opc = opc;
	}
	
	public ArrayList<Opcion> getOpciones() {
		return opciones;
	}

	public void setOpciones(ArrayList<Opcion> opciones) {
		this.opciones = opciones;
	}
	
	public ArrayList<String> getStopciones() {
		return stopciones;
	}

	public void setStopciones(ArrayList<String> stopciones) {
		this.stopciones = stopciones;
	}

	public ArrayList<Encuesta> ObtenerEncuestasUs(String usuario)
	{
		ArrayList<Encuesta> encuestas = new ArrayList<Encuesta>();
		encuestas = egresadoslogica.CargarEncuestas(usuario);
		return encuestas;
	}
	public ArrayList<Pregunta> ObtenerPreguntasUs(int encuesta)
	{
		ArrayList<Pregunta> preguntas1 = new ArrayList<Pregunta>();
		preguntas1 = egresadoslogica.CargarPreguntas(encuesta);
		allPreguntas = preguntas1;
		this.setPreguntas(preguntas1);
		this.ruta = "/Encuesta/Preguntas.xhtml";
		this.Encuesta_Id = encuesta;
		return this.preguntas;
	}
	
	public void EnviarCorreo(int encuesta)
	{
		this.ruta = "/Encuesta/EnviarEncuesta.xhtml";
		this.Encuesta_Id = encuesta;
	}
	
	public String devolverEncuesta(String usu_id)
	{
		Encuesta aux = egresadoslogica.obtenerEncuesta(Encuesta_Id, usu_id);
		return aux.getEncuesta_Titulo();
	}
	public void VolverEncuestas()
	{
		this.ruta = "/Encuesta/Encuestas.xhtml";
	}
	public void VolverPreguntas(int encuesta)
	{
		this.preg.setPregunta_Enunciado("");
        this.preg.setPregunta_Tipo("");
        this.setEnunopcion("");
        this.stopciones.clear();
        this.ruta = "/Encuesta/Preguntas.xhtml";
		this.Encuesta_Id = encuesta;
	}
	
	public void limpiarVariablesPregunta()
	{
		this.preg.setPregunta_Enunciado("");
        this.preg.setPregunta_Tipo("");
        this.setEnunopcion("");
        this.stopciones.clear();       
		this.Encuesta_Id = -1;
	}
	
	public void IrCrearEncuesta(){
		System.out.println("Click en crear encuesta");
		this.ruta = "/Encuesta/CrearEncuesta.xhtml";
	}
	
	public void IrCrearPregunta(int encuesta)
	{
		System.out.println(this.Encuesta_Id);
		this.Encuesta_Id = encuesta;
		this.ruta = "/Encuesta/CreacionPregunta.xhtml";	
	}
	
	public void CrearEncuesta(String usuarioId){
		
		this.enc.setUsuario_Id(usuarioId);
		System.out.println("Creando encuesta con usuario = "+usuarioId);
		boolean result;
		
		result = egresadoslogica.InsertarEncuesta(enc);
		if(result){
			FacesContext context = FacesContext.getCurrentInstance();
	        context.addMessage("successMsg", new FacesMessage(FacesMessage.SEVERITY_INFO, "Encuesta agregada correctamente", ""));
		}
		else{
			FacesContext context = FacesContext.getCurrentInstance();
	        context.addMessage("successMsg", new FacesMessage(FacesMessage.SEVERITY_ERROR, "Ha ocurrido un error o la encuesta ya existe", ""));
		}
		this.enc = new Encuesta();
		this.ruta = "/Encuesta/Encuestas.xhtml";
	}
	
	public void EditarEncuesta(){
		System.out.println("Click en Guardar Edición de encuesta");
		System.out.println("Nuevo Encuesta_Id = "+this.encEdit.getEncuesta_Id());
		System.out.println("Nuevo Encuesta Titulo = "+this.encEdit.getEncuesta_Titulo());
		System.out.println("Nuevo Encuesta Descripcion = "+this.encEdit.getEncuesta_Descripcion());
		System.out.println("Nuevo Encuesta Estado = "+this.encEdit.getEncuesta_Estado());
		System.out.println("Nuevo Encuesta Usuario Id = "+this.encEdit.getUsuario_Id());
		
		if(this.encEdit.getEncuesta_Estado().equals("En construccion"))
			this.encEdit.setEncuesta_Estado("En construcción");
		
		boolean result;
		result = egresadoslogica.editarEncuesta(this.oldEnc,this.encEdit);
		if(result){
			FacesContext context = FacesContext.getCurrentInstance();
	        context.addMessage("successMsg", new FacesMessage(FacesMessage.SEVERITY_INFO, "Encuesta modificada correctamente", ""));
		}
		else{
			FacesContext context = FacesContext.getCurrentInstance();
	        context.addMessage("successMsg", new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error: No pueden existir campos vacios", ""));
		}
		this.oldEnc = new Encuesta();
		this.encEdit = new Encuesta();
	}
	
	public void EditarPregunta(){
		System.out.println("Click en Editar pregunta");
		boolean cambio = true;
		for(int i=0; i<allPreguntas.size(); i++)
		{
			if(preEdit.getPregunta_Enunciado().equals(allPreguntas.get(i).getPregunta_Enunciado())
			   && preEdit.isObligatorio()==allPreguntas.get(i).isObligatorio())
			{
				cambio = false;
				i = allPreguntas.size();
			}
		}
		if(cambio == true)
		{
			System.out.println("Nuevo Pregunta_Id = "+this.preEdit.getPregunta_Id());
			System.out.println("Nuevo Pregunta_Enunciado = "+this.preEdit.getPregunta_Enunciado());
			System.out.println("Nuevo Pregunta_Tipo = "+this.preEdit.getPregunta_Tipo());
			System.out.println("Nuevo obligatorio = "+this.preEdit.isObligatorio());
			boolean result;
			result = egresadoslogica.editarPregunta(this.oldPre,this.preEdit);
			if(result){
				FacesContext context = FacesContext.getCurrentInstance();
		        context.addMessage("successMsg", new FacesMessage(FacesMessage.SEVERITY_INFO, "Pregunta modificada correctamente", ""));
			}
			else{
				FacesContext context = FacesContext.getCurrentInstance();
		        context.addMessage("successMsg", new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error: No pueden existir campos vacios", ""));
			}
			this.oldPre = new Pregunta();
			this.preEdit = new Pregunta();
		}
		else
		{
			/*
			 FacesContext context = FacesContext.getCurrentInstance();
	        context.addMessage("successMsg", new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error: El enunciado de la pregunta ya existe.", ""));
	        this.oldPre = new Pregunta();
			this.preEdit = new Pregunta();
			 */
			
		}
	}
	
	public void CrearPregunta(int encuesta)
	{
		this.preg.setEncuesta_Id(this.Encuesta_Id);
		boolean result;
		result = egresadoslogica.InsertarPregunta(preg,this.stopciones);
		if(result)
		{
			FacesContext context = FacesContext.getCurrentInstance();
	        context.addMessage("successMsg", new FacesMessage(FacesMessage.SEVERITY_INFO, "Pregunta Guardada Satisfactoriamente", ""));
	        this.preg.setPregunta_Enunciado("");
	        this.preg.setPregunta_Tipo("");
	        this.setEnunopcion("");
	        this.preg.setObligatorio(false);
	        this.stopciones.clear();
		}
		else
		{
			FacesContext context = FacesContext.getCurrentInstance();
	        context.addMessage("successMsg", new FacesMessage(FacesMessage.SEVERITY_INFO, "Ha ocurrido un error o la pregunta ya existe", ""));
	        this.preg.setPregunta_Enunciado("");
	        this.preg.setPregunta_Tipo("");
	        this.setEnunopcion("");
	        this.preg.setObligatorio(false);
	        this.stopciones.clear();
		}
		this.ruta = "/Encuesta/Preguntas.xhtml";
		this.Encuesta_Id = encuesta;
	}
	public void IrCrearOpciones(int encuesta)
	{
	
	    
		this.ruta = "/Encuesta/CrearOpciones.xhtml";
	
		this.Encuesta_Id = encuesta;
	
	}
		
	
	public String SiguientePaso(FlowEvent event) {

            return event.getNewStep();
    }
	public ArrayList<Opcion> ObtenerOpciones()
	{
		return this.opciones;
	}
	public void AddOpcion()
	{
		boolean esta = false;
		if(this.enunopcion.equalsIgnoreCase(""))
		{
			FacesContext context = FacesContext.getCurrentInstance();
	        context.addMessage("successMsg", new FacesMessage(FacesMessage.SEVERITY_INFO, "El campo enunciado no puede estar vacio", ""));
		}
		else
		{
			if(this.stopciones.size() == 0)
			{
				this.stopciones.add(enunopcion);
				FacesContext context = FacesContext.getCurrentInstance();
		        context.addMessage("successMsg", new FacesMessage(FacesMessage.SEVERITY_INFO, "Opcion Agregada", ""));
			}
			else
			{
				for(int i=0;i<this.stopciones.size();i++)
				{
					if(this.stopciones.get(i).equalsIgnoreCase(enunopcion))
					{
						esta = true;
						i = this.stopciones.size();
					}
				}
				if(esta)
				{
					FacesContext context = FacesContext.getCurrentInstance();
			        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Opcion ya existente", ""));
				}
				else
				{
					this.stopciones.add(enunopcion);
					FacesContext context = FacesContext.getCurrentInstance();
			        context.addMessage("successMsg", new FacesMessage(FacesMessage.SEVERITY_INFO, "Opcion Agregada", ""));
				}
			}
		}
		this.enunopcion = "";
		this.ruta = "/Encuesta/CreacionPregunta.xhtml";
	}
	public void DeleteOpcion(String opcion)
	{
		System.out.println("Deleteopcion Poll delete opcioness "+opcion);
		if(opcion.equalsIgnoreCase("")||opcion==null)
		{
			FacesContext context = FacesContext.getCurrentInstance();
	        context.addMessage("successMsg", new FacesMessage(FacesMessage.SEVERITY_INFO, "Ha ocurrido un error", ""));
		}
		else
		{
			this.stopciones.remove(opcion);
			FacesContext context = FacesContext.getCurrentInstance();
	        context.addMessage("successMsg", new FacesMessage(FacesMessage.SEVERITY_INFO, "Opción eliminada con éxito", ""));
			this.ruta = "/Encuesta/CreacionPregunta.xhtml";
		}
		
		//FacesContext context = FacesContext.getCurrentInstance();
        //context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Item Selected", event.getObject().toString()));
	}
	public void eliminarPregunta()
	{	
		egresadoslogica.EliminarPregunta(this.preEdit.getPregunta_Id());
		FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage("successMsg", new FacesMessage(FacesMessage.SEVERITY_INFO, "Pregunta eliminada con exito", ""));
		this.ruta = "/Encuesta/Preguntas.xhtml";
	}
	public void cambiarOpcionSeleccionada(String opcion)
	{
		this.opcionseleccionada = opcion;
	}
	
	public void cambiarOpcionTemporal(String opcion)
	{
		this.opciontemp = opcion;
	}
	public void editarOpcion()
	{
		if(this.opciontemp.equalsIgnoreCase(""))
		{
			FacesContext context = FacesContext.getCurrentInstance();
	        context.addMessage("successMsg", new FacesMessage(FacesMessage.SEVERITY_INFO, "El enunciado de la opcion no puede estar vacio", ""));
		}
		else
		{
			if(!this.opciontemp.equalsIgnoreCase(this.opcionseleccionada))
			{
				int pos  = -1;
				for(int i=0;i<this.stopciones.size();i++)
				{
					if(this.stopciones.get(i).equalsIgnoreCase(this.opcionseleccionada))
					{
						pos = i;
						i = this.stopciones.size();
					}
				}
				if(pos==-1)
				{
					FacesContext context = FacesContext.getCurrentInstance();
			        context.addMessage("successMsg", new FacesMessage(FacesMessage.SEVERITY_INFO, "Ha ocurrido un error", ""));
			        this.opciontemp = "";
				}
				else
				{
					boolean esta = false;
					for(int i=0;i<this.stopciones.size();i++)
					{
						if(this.stopciones.get(i).equalsIgnoreCase(this.opciontemp))
						{
							esta = true;
							i = this.stopciones.size();
						}
					}
					if(esta)
					{
						FacesContext context = FacesContext.getCurrentInstance();
				        context.addMessage("successMsg", new FacesMessage(FacesMessage.SEVERITY_INFO, "La opcion ya existe", ""));
				        this.opciontemp = "";
					}
					else
					{
						this.stopciones.set(pos, this.opciontemp);
						FacesContext context = FacesContext.getCurrentInstance();
				        context.addMessage("successMsg", new FacesMessage(FacesMessage.SEVERITY_INFO, "Opcion editada", ""));
				        this.opciontemp = "";
					}
				}
			}
			else
			{
				FacesContext context = FacesContext.getCurrentInstance();
		        context.addMessage("successMsg", new FacesMessage(FacesMessage.SEVERITY_INFO, "Opcion editada", ""));
		        this.opciontemp = "";
			}
		}
	}
	public int numPreguntas(int enc_id)
	{
		int numPreg = 0;
		numPreg = egresadoslogica.numPreguntas(enc_id);
		return numPreg;
	}
	public void clickHome(){
		this.limpiarVariablesPregunta();
		this.VolverEncuestas();
	}
	
	
	//taborda
	
	public ArrayList<Opcion> ObtenerOpcionesPol(int pregunta_id)
	{
		
		ArrayList<Opcion> opciones = new ArrayList<Opcion>();
		opciones = egresadoslogica.obtenerOpcionesPol(pregunta_id);
		return opciones;
	}



public void editarOpcionPol(String opcTmp)
{
	
	
	if(opcTmp.equals(""))
	{
		FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage("successMsg", new FacesMessage(FacesMessage.SEVERITY_ERROR, "    Error: No pueden existir campos vacios", ""));

	}
	else
	{
		boolean result;
		result = egresadoslogica.editaOpcion(oldOpc, opcTmp);
		if(result){
			FacesContext context = FacesContext.getCurrentInstance();
	        context.addMessage("successMsg", new FacesMessage(FacesMessage.SEVERITY_INFO, "Opcion modificada correctamente", ""));
		}
	
	}
	}		


public void DeleteOpcionPol()
{
	
		boolean result;
		result = egresadoslogica.eliminaOpcion(oldOpc);
		if(result){
			FacesContext context = FacesContext.getCurrentInstance();
	        context.addMessage("successMsg", new FacesMessage(FacesMessage.SEVERITY_INFO, "Opcion Eliminada correctamente", ""));
		}
	
}
}