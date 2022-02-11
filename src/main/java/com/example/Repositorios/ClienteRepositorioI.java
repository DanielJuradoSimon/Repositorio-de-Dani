package com.example.Repositorios;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.Entidades.Cliente;

//@Repository
public interface ClienteRepositorioI extends JpaRepository<Cliente, Long>{

	
}
