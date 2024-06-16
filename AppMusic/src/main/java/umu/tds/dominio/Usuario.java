package umu.tds.dominio;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import umu.tds.dominio.descuentos.IDescuento;

public class Usuario {
	private int codigo;
	private String username;
	private String nombre;
	private String apellidos;
	private String email;
	private Date fecha;
	private String contrasena;
	private boolean premium;
	private IDescuento descuento;
	private List<PlayList> playLists;
	private List<Cancion> cancionesRecientes;

	public Usuario(String nombre, String apellido, String correo, Date fecha, String contra) {
		this.codigo = 0;
		this.nombre = nombre;
		this.apellido = apellido;
		this.email = correo;
		this.fecha = fecha;
		this.contrasena = contra;
		this.premium = false;
		this.playLists = new LinkedList<PlayList>();
		this.cancionesRecientes = new LinkedList<Cancion>();
		
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public boolean isPremium() {
		return premium;
	}

	public void setPremium(boolean premium) {
		this.premium = premium;
	}

	public List<PlayList> getPlayLists() {
		return playLists;
	}

	public void setPlayLists(List<PlayList> playLists) {
		this.playLists = playLists;
	}

	public List<Cancion> getCancionesRecientes() {
		return cancionesRecientes;
	}

	public void setCancionesRecientes(List<Cancion> cancionesRecientes) {
		this.cancionesRecientes = cancionesRecientes;
	}

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public String getContrasena() {
		return contrasena;
	}

	public void setContrasena(String contrasena) {
		this.contrasena = contrasena;
	}
	
	
	

}
