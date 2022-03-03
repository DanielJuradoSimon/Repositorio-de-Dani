package com.example.Servicios;

import java.util.List;

import com.example.Entidades.Cliente;
import com.example.Entidades.Cuenta;
import com.example.Entidades.Operacion;

public interface CuentaServiceI {

	public List<Cuenta> obtenerTodasCuentas();
	
	//public List<Cuenta> obtenerCuentasPorCliente(final Cliente Cliente);

	//public List<Operacion> obtenerOperacionesPorCuenta(final Cuenta Cuenta);
	
	public void eliminarCuenta(Cuenta Cuenta);
	
	public void eliminarCuentaPorId(long ClienteId);

	public void aniadirCuenta(Cuenta Cuenta);

	public void actualizarCuenta(Cuenta Cuenta);
	
	public Cuenta findCuentaByID(long id);
	
	public List<Long> findCuentaByClienteID(long idCliente);
	
}
