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
	@Temporal(TemporalType.DATE)
	private Date fecha_nac;
	
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
	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinTable(name = "cliente_cuenta",
	joinColumns = @JoinColumn(name = "cliente_id"),
	inverseJoinColumns = @JoinColumn(name = "cuenta_id"))
	private List<Cuenta> misCuentas;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public Date getFecha_nac() {
		return fecha_nac;
	}

	public void setFecha_nac(Date fecha_nac) {
		this.fecha_nac = fecha_nac;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	
	public String getDNI() {
		return DNI;
	}

	public void setDNI(String dNI) {
		DNI = dNI;
	}

	public List<Cuenta> getMisCuentas() {
		return misCuentas;
	}

	public void setMisCuentas(List<Cuenta> misCuentas) {
		this.misCuentas = misCuentas;
	}




	private static final long serialVersionUID = 1L;
	
	
	
}
