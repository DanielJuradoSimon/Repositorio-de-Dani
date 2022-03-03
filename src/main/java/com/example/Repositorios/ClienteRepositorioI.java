package com.example.Repositorios;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.Entidades.Cliente;

//@Repository
public interface ClienteRepositorioI extends JpaRepository<Cliente, Long>{

	@Query(value = "SELECT * FROM clientes WHERE id = ?", nativeQuery = true)
	Cliente findClienteByID(long idCliente);
	
	@Query(value = "SELECT cliente_id FROM cliente_cuenta JOIN cuentas ON cliente_cuenta.cuenta_id = cuentas.id WHERE cuentas.id = ?", nativeQuery = true)
	List<Integer> findClienteByCuentaID(long idCuenta);
	
}
