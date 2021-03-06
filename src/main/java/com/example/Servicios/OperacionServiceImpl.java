package com.example.Servicios;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Entidades.Cuenta;
import com.example.Entidades.Operacion;
import com.example.Repositorios.OperacionRepositorioI;

@Service
public class OperacionServiceImpl implements OperacionServiceI{

	@Autowired
	private OperacionRepositorioI operacionRepositorio;
	
	@Override
	public List<Operacion> obtenerTodosOperaciones() {

		return operacionRepositorio.findAll();
	}

	@Override
	public void eliminarOperacion(Operacion Operacion) {
		
		operacionRepositorio.delete(Operacion);
		
	}

	@Override
	public void aniadirOperacion(Operacion Operacion) {
		
		operacionRepositorio.save(Operacion);
		
	}

	@Override
	public void actualizarOperacion(Operacion Operacion) {
		
		operacionRepositorio.save(Operacion);
		
	}

	@Override
	public void borrarOperacionesPorCuenta(long cuentaId) {
		
		operacionRepositorio.borrarOperaciones(cuentaId);
		
	}

}
