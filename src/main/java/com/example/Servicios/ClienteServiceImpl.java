package com.example.Servicios;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Entidades.Cliente;
import com.example.Repositorios.ClienteRepositorioI;

@Service
public class ClienteServiceImpl implements ClienteServiceI{

	@Autowired
	private ClienteRepositorioI clienteRepositorio;
	
	@Override
	public List<Cliente> obtenerTodosClientes() {

		return clienteRepositorio.findAll();
		
	}

	@Override
	public void eliminarCliente(Cliente Cliente) {

		clienteRepositorio.delete(Cliente);
		
	}

	@Override
	public void aniadirCliente(Cliente Cliente) {

		clienteRepositorio.save(Cliente);
		
	}

	@Override
	public void actualizarCliente(Cliente Cliente) {

		clienteRepositorio.save(Cliente);
		
	}

}
