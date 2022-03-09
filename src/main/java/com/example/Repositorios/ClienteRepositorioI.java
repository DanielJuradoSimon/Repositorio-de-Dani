package com.example.Repositorios;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.Entidades.Cliente;

//@Repository
public interface ClienteRepositorioI extends JpaRepository<Cliente, Long>{

	/**
	 * Este método con query nativa devolverá el cliente correspondiente a la id que le pasemos
	 * @param idCliente
	 * @return Cliente
	 */
	@Query(value = "SELECT * FROM clientes WHERE id = ?", nativeQuery = true)
	Cliente findClienteByID(long idCliente);
	
	/**
	 * Este método con query nativa devolverá una lista con las id de los clientes que estén relacionados con las cuentas cuya id le pasemos
	 * 
	 * @param idCuenta
	 * @return List<Integer>
	 */
	@Query(value = "SELECT cliente_id FROM cliente_cuenta JOIN cuentas ON cliente_cuenta.cuenta_id = cuentas.id WHERE cuentas.id = ?", nativeQuery = true)
	List<Integer> findClienteByCuentaID(long idCuenta);
	
	/**
	 * Este método con query nativa devolverá un entero que indicará cuántos clientes hay en la base de datos
	 * 
	 * @return int
	 */
	@Query(value = "SELECT COUNT(id) FROM `clientes`", nativeQuery = true)
	int countId();
		
	
	/**
	 * Este método nos servirá para insertar en la tabla intermedia de la relación n:m un registro pasando las id correspondientes
	 * 
	 * @param id_cliente
	 * @param id_cuenta
	 */
	
	@Transactional
	@Modifying
	@Query(
			  value = "INSERT INTO cliente_cuenta (cliente_id, cuenta_id) VALUES (?1, ?2)", 
			  nativeQuery = true)
	void insercion(long id_cliente, long id_cuenta);
	
}
