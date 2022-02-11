package com.example.Servicios;

import java.util.List;

import com.example.Entidades.Cliente;
import com.example.Entidades.Cuenta;
import com.example.Entidades.Operacion;

public interface CuentaServiceI {

	public List<Cuenta> obtenerTodasCuentas();
	
	//public List<Cuenta> obtenerCuentasPorCliente(final Cliente Cliente);

	//public List<Operacion> obtenerOperacionesPorCuenta(final Cuenta Cuenta);
	
	public void eliminarCuenta(final Cuenta Cuenta);

	public void aniadirCuenta(final Cuenta Cuenta);

	public void actualizarCuenta(final Cuenta Cuenta);
	
}
