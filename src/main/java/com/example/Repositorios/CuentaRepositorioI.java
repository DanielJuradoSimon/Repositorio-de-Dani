package com.example.Repositorios;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.Entidades.Cliente;
import com.example.Entidades.Cuenta;
import com.example.Entidades.Operacion;

//@Repository
public interface CuentaRepositorioI extends JpaRepository<Cuenta, Long>{
	
	@Query(value = "SELECT cuenta_id FROM cliente_cuenta JOIN clientes ON cliente_cuenta.cliente_id = clientes.id WHERE clientes.id = ?", nativeQuery = true)
	List<Long> findCuentaByClienteID(long idCliente);
	
}
