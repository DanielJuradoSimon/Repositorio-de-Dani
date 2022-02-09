package com.example.Repositorios;


import org.springframework.data.jpa.repository.JpaRepository;

import com.example.Entidades.Cliente;


public interface ClienteRepositorioI extends JpaRepository<Cliente, Long>{

	
}
