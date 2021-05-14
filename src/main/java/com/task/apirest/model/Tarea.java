package com.task.apirest.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="TAREA")
public class Tarea {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="IDENTIFICADOR")
	private Long id;	

	@Column(name="DESCRIPCION")
	private String descripcion;
	
	@Column(name="FECHA_CREACION")
	@Temporal(TemporalType.TIMESTAMP)
	private Date fechaCreacion;
	
	
	@Column(name="VIGENTE")
	private Boolean vigente;

	

	@PrePersist 
	public void prePersist(){
		this.fechaCreacion=new Date();
	}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getDescripcion() {
		return descripcion;
	}


	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}


	public Date getFechaCreacion() {
		return fechaCreacion;
	}


	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}


	public Boolean getVigente() {
		return vigente;
	}


	public void setVigente(Boolean vigente) {
		this.vigente = vigente;
	}


	@Override
	public String toString() {
		return "Tarea [id=" + id + ", descripcion=" + descripcion + ", fechaCreacion=" + fechaCreacion + ", vigente="
				+ vigente + "]";
	}
	
	

 
	

}
