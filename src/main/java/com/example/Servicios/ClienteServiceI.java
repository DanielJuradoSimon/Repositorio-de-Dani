package com.example.Servicios;

import java.util.List;

import com.example.Entidades.Cliente;



public interface ClienteServiceI {
	
	public List<Cliente> obtenerTodosClientes();

	public void eliminarCliente(final Cliente Cliente);

	public void aniadirCliente(final Cliente Cliente);

	public void actualizarCliente(final Cliente Cliente);
	
}
