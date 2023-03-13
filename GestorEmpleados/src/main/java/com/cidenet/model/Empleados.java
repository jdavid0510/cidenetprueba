package com.cidenet.model;

import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "empleados")
public class Empleados {

	@Id
	@Column(name = "numero_identificacion")
	private Long numeroIdentificacion;

	@Column(name = "primer_apellido")
	private String primerApellido;

	@Column(name = "segundo_apellido")
	private String segundoApellido;

	@Column(name = "primer_nombre")
	private String primerNombre;

	@Column(name = "otros_nombres")
	private String otrosNombres;

	@Column(name = "pais_empleo")
	private String paisEmpleo;

	@Column(name = "tipo_identificacion")
	private String tipoIdentificacion;

	@Column(name = "correo_electronico")
	private String correoElectronico;

	@Column(name = "fecha_ingreso")
	private Date fechaIngreso;

	@Column(name = "area")
	private String area;

	@Column(name = "estado")
	private String estado;

	@Column(name = "fecha_registro")
	private LocalDateTime fechaRegistro;

	public Empleados() {
	}

	public Empleados(Long numeroIdentificacion, String primerApellido, String segundoApellido, String primerNombre,
			String otrosNombres, String paisEmpleo, String tipoIdentificacion, String correoElectronico,
			Date fechaIngreso, String area, String estado, LocalDateTime fechaRegistro) {
		this.numeroIdentificacion = numeroIdentificacion;
		this.primerApellido = primerApellido;
		this.segundoApellido = segundoApellido;
		this.primerNombre = primerNombre;
		this.otrosNombres = otrosNombres;
		this.paisEmpleo = paisEmpleo;
		this.tipoIdentificacion = tipoIdentificacion;
		this.correoElectronico = correoElectronico;
		this.fechaIngreso = fechaIngreso;
		this.area = area;
		this.estado = estado;
		this.fechaRegistro = fechaRegistro;
	}

	public Empleados(String primerApellido2, String segundoApellido2, String primerNombre2, String otrosNombres2,
			String paisEmpleo2, String tipoIdentificacion2, String numeroIdentificacion2, String correoElectronico2,
			LocalDate fechaIngreso2, String area2, String string, LocalDateTime now) {
		// TODO Auto-generated constructor stub
	}

	public Long getNumeroIdentificacion() {
		return numeroIdentificacion;
	}

	public void setNumeroIdentificacion(Long numeroIdentificacion) {
		this.numeroIdentificacion = numeroIdentificacion;
	}

	public String getPrimerApellido() {
		return primerApellido;
	}

	public void setPrimerApellido(String primerApellido) {
		this.primerApellido = primerApellido;
	}

	public String getSegundoApellido() {
		return segundoApellido;
	}

	public void setSegundoApellido(String segundoApellido) {
		this.segundoApellido = segundoApellido;
	}

	public String getPrimerNombre() {
		return primerNombre;
	}

	public void setPrimerNombre(String primerNombre) {
		this.primerNombre = primerNombre;
	}

	public String getOtrosNombres() {
		return otrosNombres;
	}

	public void setOtrosNombres(String otrosNombres) {
		this.otrosNombres = otrosNombres;
	}

	public String getPaisEmpleo() {
		return paisEmpleo;
	}

	public void setPaisEmpleo(String paisEmpleo) {
		this.paisEmpleo = paisEmpleo;
	}

	public String getTipoIdentificacion() {
		return tipoIdentificacion;
	}

	public void setTipoIdentificacion(String tipoIdentificacion) {
		this.tipoIdentificacion = tipoIdentificacion;
	}

	public String getCorreoElectronico() {
		return correoElectronico;
	}

	public void setCorreoElectronico(String correoElectronico) {
		this.correoElectronico = correoElectronico;
	}

	public Date getFechaIngreso() {
		return fechaIngreso;
	}

	public void setFechaIngreso(Date fechaIngreso) {
		this.fechaIngreso = fechaIngreso;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public LocalDateTime getFechaRegistro() {
		return fechaRegistro;
	}

	public void setFechaRegistro(LocalDateTime localDateTime) {
		this.fechaRegistro = localDateTime;
	}

	public boolean isPresent() {
		return false;
	}

}


