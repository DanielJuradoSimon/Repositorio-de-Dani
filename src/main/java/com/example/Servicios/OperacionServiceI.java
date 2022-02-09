package com.example.Servicios;

import java.util.List;

import com.example.Entidades.Operacion;

public interface OperacionServiceI {

	public List<Operacion> obtenerTodosOperaciones();

	public void eliminarOperacion(final Operacion Operacion);

	public void aniadirOperacion(final Operacion Operacion);

	public void actualizarOperacion(final Operacion Operacion);
	
}
