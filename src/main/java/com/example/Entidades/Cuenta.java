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

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @AllArgsConstructor @NoArgsConstructor
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
	//@Temporal(TemporalType.DATE)
	private String fecha_crea;
	
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

	
	@Override
	public String toString() {
		return "Cuenta [id=" + id + ", numCuenta=" + numCuenta + ", fecha_nac=" + fecha_crea + ", saldo=" + saldo
				+ ", clientes=" + clientes + ", Operaciones=" + Operaciones + "]";
	}

	private static final long serialVersionUID = 1L;

}
