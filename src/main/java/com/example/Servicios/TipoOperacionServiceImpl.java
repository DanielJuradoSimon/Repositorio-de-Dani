package com.example.Servicios;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.Entidades.TipoOperacion;
import com.example.Repositorios.TipoOperacionRepositorioI;

public class TipoOperacionServiceImpl implements TipoOperacionServiceI{

	@Autowired
	private TipoOperacionRepositorioI tipoOperacionRepositorio;
	
	@Override
	public List<TipoOperacion> obtenerTodosTipoOperacion() {

		return tipoOperacionRepositorio.findAll();
	}

	@Override
	public void eliminarTipoOperacion(TipoOperacion TipoOperacion) {
		
		tipoOperacionRepositorio.delete(TipoOperacion);
		
	}

	@Override
	public void aniadirTipoOperacion(TipoOperacion TipoOperacion) {
		
		tipoOperacionRepositorio.save(TipoOperacion);
		
	}

	@Override
	public void actualizarTipoOperacion(TipoOperacion TipoOperacion) {

		tipoOperacionRepositorio.save(TipoOperacion);
		
	}

}
