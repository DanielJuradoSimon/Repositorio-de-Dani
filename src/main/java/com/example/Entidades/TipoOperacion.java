package com.example.Entidades;

import java.io.Serializable;

import javax.persistence.*;

@Entity
@Table(name = "Tipo_Operacion")
public class TipoOperacion implements Serializable{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String nombreOperacion;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombreOperacion() {
		return nombreOperacion;
	}

	public void setNombreOperacion(String nombreOperacion) {
		this.nombreOperacion = nombreOperacion;
	}

	private static final long serialVersionUID = 1L;
	
	
}
