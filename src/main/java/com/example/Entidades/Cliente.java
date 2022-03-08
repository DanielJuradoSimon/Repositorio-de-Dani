package com.example.Entidades;

import java.util.List;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @AllArgsConstructor @NoArgsConstructor
@Entity
@Table(name = "clientes")
public class Cliente implements Serializable{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotNull(message = "no puede estar vacío")
	@Size(min = 9, max = 9, message = "el tamaño tiene que ser de 9 caracteres")
	@Column(nullable = false)
	private String DNI;
	
	@NotNull(message = "no puede estar vacío")
	@Size(min = 4, max = 12, message = "el tamaño tiene que estar entre 4 y 12")
	@Column(nullable = false)
	private String nombre;
	
	@NotNull(message = "no puede estar vacío")
	@Size(min = 4, max = 30, message = "el tamaño tiene que estar entre 4 y 12")
	@Column(nullable = false)
	private String apellidos;
	
	@NotNull(message = "no puede estar vacío")
	@Column(name = "Fecha_de_nacimiento")
	private String fecha_nac;
	
	@NotNull(message = "no puede estar vacío")
	private String direccion;
	
	@NotNull(message = "no puede estar vacío")
	@Column(nullable = false, unique = true)
	private String email;
	
	@NotNull(message = "no puede estar vacío")
	@Size(min = 4, max = 12, message = "el tamaño tiene que estar entre 4 y 12")
	@Column(nullable = false, unique = true, name = "telefono")
	private String telefono;
	
	//@JsonIgnoreProperties(value={"cliente", "hibernateLazyInitializer", "handler"}, allowSetters=true)
	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.DETACH)
	@JoinTable(name = "cliente_cuenta",
	joinColumns = @JoinColumn(name = "cliente_id"),
	inverseJoinColumns = @JoinColumn(name = "cuenta_id"))
	private List<Cuenta> misCuentas;


	@Override
	public String toString() {
		return "Cliente [id=" + id + ", DNI=" + DNI + ", nombre=" + nombre + ", apellidos=" + apellidos + ", fecha_nac="
				+ fecha_nac + ", direccion=" + direccion + ", email=" + email + ", telefono=" + telefono + "]";
	}




	private static final long serialVersionUID = 1L;
	
	
	
}
