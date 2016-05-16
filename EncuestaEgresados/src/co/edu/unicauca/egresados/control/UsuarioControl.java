package co.edu.unicauca.egresados.control;

import java.util.ArrayList;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import co.edu.unicauca.egresados.logica.UserBO;
import co.edu.unicauca.egresados.logica.encuestaegresados.EncuestaEgresadosLogica;
import co.edu.unicauca.egresados.modelo.Pregunta;
import co.edu.unicauca.egresados.modelo.Usuario;

@ManagedBean(name= "user")
@SessionScoped
public class UsuarioControl {
	
	private static final long serialVersionUID = 7124876753490038788L;
	private UserBO userBO;
	private boolean login=false;
	private String usuarioId;
	//Variables para los permisos por usuario
	private boolean admin,estudiante;
	private ArrayList<Usuario> usuarios;
	private ArrayList<Pregunta> preguntas;
	private Usuario us;
	EncuestaEgresadosLogica encuestaBO;
	public UsuarioControl() 
	{
		encuestaBO = new EncuestaEgresadosLogica();
		
		usuarios = new ArrayList<Usuario>();
		preguntas = new ArrayList<Pregunta>();
		
		//Carga BD LOGIN
		setUsuarios(this.encuestaBO.cargarInicioSesion());
		//Carga BD PREGUNTAS
		//setPreguntas(this.encuestaBO.);
		
		us = new Usuario();
		admin=false;
		estudiante=false;
	}
	
	
	public String getUsuarioId() {
		return usuarioId;
	}



	public void setUsuarioId(String usuarioId) {
		this.usuarioId = usuarioId;
	}



	public ArrayList<Usuario> getUsuarios() {
		return usuarios;
	}

	public void setUsuarios(ArrayList<Usuario> usuarios) {
		this.usuarios = usuarios;
	}
	
	public ArrayList<Pregunta> getPreguntas() {
		return preguntas;
	}

	public void setPreguntas(ArrayList<Pregunta> preguntas) {
		this.preguntas = preguntas;
	}
	
	public Usuario getUs() {
		return us;
	}

	public void setUs(Usuario us) {
		this.us = us;
	}
	
	public boolean esAdmin() {
		return admin;
	}

	public void setAdmin(boolean admin) {
		this.admin = admin;
	}
	
	public boolean esEstudiante() {
		return estudiante;
	}

	public void setEstudiante(boolean estudiante) {
		this.estudiante = estudiante;
	}
	public String acceder() {
		String ruta = "";
		userBO = new UserBO();
		
		FacesContext context = FacesContext.getCurrentInstance();        
		int validationResult = userBO.validateUser();		
		switch (validationResult)
		{
			case 1: //Si retorna 1, se valida el acceso correctamente.
				this.setLogin(true);
				ruta = "main?faces-redirect=true";				
				break;
			case 2: //Si retorna 2, hay problemas con la información de acceso.
				context.addMessage(null, new FacesMessage("Error", "Nombre de usuario o contraseña erróneos!"));
				break;
			default: //Valores diferentes, no se puede acceder por otras razones.
				System.out.println("UserControl.java - Error de acceso - No se puede acceder al sistema, verifique su información!");
				//context.addMessage(null, new FacesMessage("Error", "Error del servidor, intente más tarde..."));
				break;
		}
		return ruta;
	}
	
	public String iniciarSesion()throws NumberFormatException {
		
		String ruta = "index";
		
		int id = 0;
		System.out.println("Entro al metodo inicio sesion");
		System.out.println("usuario"+us.getNombreUsuario());
		System.out.println("pas"+us.getContrasenia());
		System.out.println("*******************************************");
	
			
		boolean user = false, pass = false;
		for(int i=0 ; i<usuarios.size() ; i++)
		{
			System.out.println("usuario"+usuarios.get(i).getNombreUsuario());
			System.out.println("pasword"+usuarios.get(i).getContrasenia());
			if(usuarios.get(i).getNombreUsuario().equals(us.getNombreUsuario())){
				user = true;
		
			
				if(usuarios.get(i).getContrasenia().equals(us.getContrasenia())){
					pass = true;
					
				
					id = usuarios.get(i).getId();
					this.usuarioId = Integer.toString(id);
					break;
				}else{
					pass = false;
					break;
				}
			}else{					
				user = false;
				pass = false;
			}
		}
			
		if(user==true && pass==true)
			ruta = "main?faces-redirect=true";
		else{
			if(user==false && pass==false && us.getNombreUsuario()!="" && us.getContrasenia()!="")
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Advertencia!", "Nombre de usuario o contraseña erroneos."));
			if(user==true && pass==false && us.getNombreUsuario()!="" && us.getContrasenia()!="")
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Advertencia!", "Contraseña erronea."));		
		}
			
		//Metodo para proporcionar privilegios al usuario
		permisos(id);
		System.out.println(us.getId());
		return ruta;
			
	}
	public void permisos(int i)
	{
		//Permisos para admin
		if(i == 1)
		{
			admin=true;
			estudiante=false;
			System.out.println("Administrador");
		}
		//Permisos para estudiante
		if(i == 2)
		{
			admin=false;
			estudiante=true;
			System.out.println("Estudiante");
		}
	}
	public void validacion() {
		if(us.getNombreUsuario() == "" && us.getContrasenia() == "")
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Advertencia!", "Usuario y Contraseña requerida."));
		if(us.getNombreUsuario() == "" && us.getContrasenia() != "")
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Advertencia!", "Usuario requerido."));
		if(us.getNombreUsuario() != "" && us.getContrasenia() == "")
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Advertencia!", "Contraseña requerida."));
    }
	public boolean isLogin() {
		return login;
	}
	
	public void setLogin(boolean login) {
		this.login = login;
	}
	
	//Al momento de cerrar sesion regreso al inicio
	public String cerrarSesion(int sucess)
	{
		return "index";
	}
}
