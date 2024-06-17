package umu.tds.dominio;

import java.time.LocalDate;
import java.time.Period;
import java.util.LinkedList;
import java.util.List;

import umu.tds.dominio.descuentos.Descuento65;
import umu.tds.dominio.descuentos.DescuentoNulo;
import umu.tds.dominio.descuentos.DescuentoPremium;
import umu.tds.dominio.descuentos.IDescuento;

public class Usuario {
	private int codigo;
	private String username;
	private String nombre;
	private String apellidos;
	private String email;
	private LocalDate fecha;
	private String contrasena;
	private boolean premium;
	private IDescuento descuento;
	private List<PlayList> playLists;
	private List<Cancion> cancionesRecientes;

	public Usuario(String username, String nombre, String apellidos, String email, LocalDate fecha, String contrasena) {
		this.codigo = 0;
		this.username = username;
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.email = email;
		this.fecha = fecha;
		this.contrasena = contrasena;
		this.premium = false;
		this.descuento = calcDescuento();
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
		this.actualizaDescuento();
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}


	public String getContrasena() {
		return contrasena;
	}

	public void setContrasena(String contrasena) {
		this.contrasena = contrasena;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public LocalDate getFecha() {
		return fecha;
	}

	public void setFecha(LocalDate fecha) {
		this.fecha = fecha;
	}

	public IDescuento getDescuento() {
		return descuento;
	}

	public void setDescuento(IDescuento descuento) {
		this.descuento = descuento;
	}
	
	public void actualizaDescuento() {
		this.descuento = this.calcDescuento();
	}
	
	private IDescuento calcDescuento() { 
		LocalDate fechaActual = LocalDate.now();
		if (Period.between(fecha, fechaActual).getYears() >= 65)
			return new Descuento65();
		else if (this.premium)
			return new DescuentoPremium();
		else 
			return new DescuentoNulo();		
	}
	

}
