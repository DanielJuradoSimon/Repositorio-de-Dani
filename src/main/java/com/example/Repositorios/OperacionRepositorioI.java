package com.example.Repositorios;


import org.springframework.data.jpa.repository.JpaRepository;

import com.example.Entidades.Operacion;


public interface OperacionRepositorioI extends JpaRepository<Operacion, Long>{

}
