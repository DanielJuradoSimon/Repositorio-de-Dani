package com.example.Repositorios;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.Entidades.Cliente;
import com.example.Entidades.Cuenta;
import com.example.Entidades.Operacion;

//@Repository
public interface CuentaRepositorioI extends JpaRepository<Cuenta, Long>{
	
	
}
