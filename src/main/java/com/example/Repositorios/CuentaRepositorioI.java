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
	
	/**
	 * Este método con query nativa devolverá una lista con las id de las cuentas que estén relacionadas con los clientes cuya id le pasemos
	 * 
	 * @param idCliente
	 * @return List<Long>
	 */
	@Query(value = "SELECT cuenta_id FROM cliente_cuenta JOIN clientes ON cliente_cuenta.cliente_id = clientes.id WHERE clientes.id = ?", nativeQuery = true)
	List<Long> findCuentaByClienteID(long idCliente);
	
	/**
	 * Este método con query nativa devolverá un entero que indicará cuántas cuentas hay en la base de datos
	 * @return int
	 */
	@Query(value = "SELECT COUNT(id) FROM `cuentas`", nativeQuery = true)
	int countId();
	
	
}
