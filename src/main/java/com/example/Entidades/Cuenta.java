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
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Value;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "cuentas")
public class Cuenta implements Serializable{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotNull(message = "no puede estar vacío")
	@Column(nullable = false, name="Numero_de_cuenta")
	private String numCuenta; 
	
	@NotNull(message = "no puede estar vacío")
	@Column(name = "Fecha_de_creacion")
	@Temporal(TemporalType.DATE)
	private Date fecha_nac;
	
	@Value("0")
	@NotNull(message = "no puede estar vacío")
	@Column(nullable = false)
	private long saldo;
	
	//@JsonIgnoreProperties(value={"cuenta", "hibernateLazyInitializer", "handler"}, allowSetters=true)
	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy="misCuentas")
	private List<Cliente> clientes;
	
	//@JsonIgnoreProperties(value={"cuenta", "hibernateLazyInitializer", "handler"}, allowSetters=true)
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name="cuenta_id")
	private List<Operacion> Operaciones;

	public List<Operacion> getOperaciones() {
		return Operaciones;
	}

	public void setOperaciones(List<Operacion> operaciones) {
		Operaciones = operaciones;
	}
	
	public List<Cliente> getClientes() {
		return clientes;
	}

	public void setClientes(List<Cliente> clientes) {
		this.clientes = clientes;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNumCuenta() {
		return numCuenta;
	}

	public void setNumCuenta(String numCuenta) {
		this.numCuenta = numCuenta;
	}

	public Date getFecha_nac() {
		return fecha_nac;
	}

	public void setFecha_nac(Date fecha_nac) {
		this.fecha_nac = fecha_nac;
	}

	public long getSaldo() {
		return saldo;
	}

	public void setSaldo(long saldo) {
		this.saldo = saldo;
	}
	
	private static final long serialVersionUID = 1L;

}
