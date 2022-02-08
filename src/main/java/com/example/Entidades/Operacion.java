package com.example.Entidades;

import java.io.Serializable;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

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
	
	@NotNull(message = "debe de existir al menos una operación")
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "tipoOperacion_id")
	@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
	private TipoOperacion tipoOperacion;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public TipoOperacion getTipoOperacion() {
		return tipoOperacion;
	}

	public void setTipoOperacion(TipoOperacion tipoOperacion) {
		this.tipoOperacion = tipoOperacion;
	}
	
	private static final long serialVersionUID = 1L;
}
