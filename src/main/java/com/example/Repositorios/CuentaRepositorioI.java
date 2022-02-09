package com.example.Repositorios;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.Entidades.Cliente;
import com.example.Entidades.Cuenta;
import com.example.Entidades.Operacion;


public interface CuentaRepositorioI extends JpaRepository<Cuenta, Long>{
	
	List <Cuenta> findByCliente(final Cliente Cliente);
	List <Operacion> findOperacionByCuenta(final Cuenta Cuenta);
}
