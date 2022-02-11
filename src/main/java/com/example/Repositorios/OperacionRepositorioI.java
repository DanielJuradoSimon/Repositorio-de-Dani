package com.example.Repositorios;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.Entidades.Operacion;

//@Repository
public interface OperacionRepositorioI extends JpaRepository<Operacion, Long>{

}
