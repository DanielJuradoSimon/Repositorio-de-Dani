package com.example.Entidades;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Value;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "operacion")
public class Operacion implements Serializable{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	/*@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "cuenta_id")
	private Cuenta cuenta;*/
	
	@Column(name = "Operacion")
	@Enumerated(value = EnumType.STRING)
	private TipoOperacion tipoOperacion;
	
	@Value("0")
	@NotNull(message = "no puede estar vacío")
	@Column(nullable = false)
	private long cantidad;
	
	@NotNull(message = "no puede estar vacío")
	@Column(name = "Fecha_de_realizacion")
	@Temporal(TemporalType.DATE)
	private Date fecha_de_realizacion;
		

	public long getCantidad() {
		return cantidad;
	}

	public void setCantidad(long cantidad) {
		this.cantidad = cantidad;
	}

	public Date getFecha_alta() {
		return fecha_de_realizacion;
	}

	public void setFecha_alta(Date fecha_alta) {
		this.fecha_de_realizacion = fecha_alta;
	}

	public Long getId() {
		return id;
	}

	public TipoOperacion getTipoOperacion() {
		return tipoOperacion;
	}

	public void setTipoOperacion(TipoOperacion tipoOperacion) {
		this.tipoOperacion = tipoOperacion;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	@Override
	public String toString() {
		return "Operacion [id=" + id + ", tipoOperacion=" + tipoOperacion + ", cantidad=" + cantidad
				+ ", fecha_de_realizacion=" + fecha_de_realizacion + "]";
	}

	private static final long serialVersionUID = 1L;
}
