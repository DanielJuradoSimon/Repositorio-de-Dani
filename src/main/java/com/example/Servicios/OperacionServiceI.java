package com.example.Servicios;

import java.util.List;

import com.example.Entidades.Cuenta;
import com.example.Entidades.Operacion;

public interface OperacionServiceI {

	public List<Operacion> obtenerTodosOperaciones();

	public void eliminarOperacion(Operacion Operacion);

	public void aniadirOperacion(Operacion Operacion);

	public void actualizarOperacion(Operacion Operacion);
		
}
