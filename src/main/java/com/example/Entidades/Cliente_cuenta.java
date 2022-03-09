package com.example.Entidades;

import java.util.List;

import javax.persistence.Column;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @AllArgsConstructor @NoArgsConstructor

public class Cliente_cuenta {
	
	@NotNull(message = "no puede estar vacío")
	@Column(nullable = true)
	private long Cliente_id;
	
	@NotNull(message = "no puede estar vacío")
	@Column(nullable = true)
	private long Cuenta_id;
	
}
