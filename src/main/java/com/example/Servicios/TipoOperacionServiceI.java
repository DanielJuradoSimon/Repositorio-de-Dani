package com.example.Servicios;

import java.util.List;

import com.example.Entidades.TipoOperacion;

public interface TipoOperacionServiceI {

	public List<TipoOperacion> obtenerTodosTipoOperacion();

	public void eliminarTipoOperacion(final TipoOperacion TipoOperacion);

	public void aniadirTipoOperacion(final TipoOperacion TipoOperacion);

	public void actualizarTipoOperacion(final TipoOperacion TipoOperacion);
	
}
