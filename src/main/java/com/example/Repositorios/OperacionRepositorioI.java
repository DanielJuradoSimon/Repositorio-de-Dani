package com.example.Repositorios;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.example.Entidades.Operacion;

//@Repository
public interface OperacionRepositorioI extends JpaRepository<Operacion, Long>{

	@Transactional
	@Modifying
	@Query(value = "DELETE FROM `operacion` WHERE `cuenta_id` = ?", nativeQuery = true)
	void borrarOperaciones(long id_cuenta);
}
