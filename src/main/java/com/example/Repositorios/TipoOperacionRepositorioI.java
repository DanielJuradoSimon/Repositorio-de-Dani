package com.example.Repositorios;


import org.springframework.data.jpa.repository.JpaRepository;

import com.example.Entidades.TipoOperacion;


public interface TipoOperacionRepositorioI extends JpaRepository<TipoOperacion, Long>{

}
