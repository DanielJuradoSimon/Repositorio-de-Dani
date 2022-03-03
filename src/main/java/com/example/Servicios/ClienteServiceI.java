package com.example.Servicios;

import java.util.List;

import com.example.Entidades.Cliente;



public interface ClienteServiceI {
	
	public List<Cliente> obtenerTodosClientes();

	public void eliminarClientePorId(final long ClienteId);

	public void aniadirCliente(final Cliente Cliente);

	public void actualizarCliente(final Cliente Cliente);
	
	public Cliente findClienteByID(final long id);
	
	public List<Integer> findClienteByCuentaID(long idCuenta);
	
}
