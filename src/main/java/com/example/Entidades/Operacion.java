package com.example.Entidades;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Value;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @AllArgsConstructor @NoArgsConstructor
@Entity
@Table(name = "operacion")
public class Operacion implements Serializable{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "Operacion")
	@NotNull(message = "no puede estar vacío")
	private String tipoOperacion;
	
	@Value("0")
	@NotNull(message = "no puede estar vacío")
	@Column(nullable = false)
	private long cantidad;
	
	@NotNull(message = "no puede estar vacío")
	@Column(name = "Fecha_de_realizacion")
	private String fecha_de_realizacion;
	
	@NotNull(message = "no puede estar vacío")
	@Column(name = "Cuenta_id")
	private String cuenta_id;
		
	
	@Override
	public String toString() {
		return "Operacion [id=" + id + ", tipoOperacion=" + tipoOperacion + ", cantidad=" + cantidad
				+ ", fecha_de_realizacion=" + fecha_de_realizacion + "]";
	}

	private static final long serialVersionUID = 1L;
}
