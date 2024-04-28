package entity;

import java.sql.Date;
import java.sql.Timestamp;



public class Revista {

	private int idRevista;
	private String nombre;
	private String frecuencia;
	private Date fechaCreacion;
	private Timestamp  fechaRegistro;
	private int estado;
	private Modalidad modalidad;
	
	//Atributo para la fecha con un formato "yyyy-MM-dd
	private String fechaFormateada;
	
	public int getIdRevista() {
		return idRevista;
	}
	public void setIdRevista(int idRevista) {
		this.idRevista = idRevista;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getFrecuencia() {
		return frecuencia;
	}
	public void setFrecuencia(String frecuencia) {
		this.frecuencia = frecuencia;
	}
	public Date getFechaCreacion() {
		return fechaCreacion;
	}
	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}
	public Timestamp  getFechaRegistro() {
		return fechaRegistro;
	}
	public void setFechaRegistro(Timestamp  fechaRegistro) {
		this.fechaRegistro = fechaRegistro;
	}
	public int getEstado() {
		return estado;
	}
	public void setEstado(int estado) {
		this.estado = estado;
	}
	public Modalidad getModalidad() {
		return modalidad;
	}
	public void setModalidad(Modalidad modalidad) {
		this.modalidad = modalidad;
	}
	public String getFechaFormateada() {
		return fechaFormateada;
	}
	public void setFechaFormateada(String fechaFormateada) {
		this.fechaFormateada = fechaFormateada;
	}

	
	
	

}
