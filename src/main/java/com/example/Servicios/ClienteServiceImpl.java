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
	public void aniadirCliente(Cliente Cliente) {

		clienteRepositorio.save(Cliente);
		
	}

	@Override
	public void actualizarCliente(Cliente Cliente) {

		clienteRepositorio.save(Cliente);
		
	}


	@Override
	public void eliminarClientePorId(long ClienteId) {
		clienteRepositorio.deleteById(ClienteId);
		
	}


	@Override
	public Cliente findClienteByID(long id) {
		
		Cliente c = clienteRepositorio.findClienteByID(id);
		return c;
		
	}


	@Override
	public List<Integer> findClienteByCuentaID(long idCuenta) {
		List<Integer> listaClientes = clienteRepositorio.findClienteByCuentaID(idCuenta);
		return listaClientes;
	}


	@Override
	public void insercionClienteCuenta(long id_cliente, long id_cuenta) {
		
		clienteRepositorio.insercion(id_cliente, id_cuenta);
		
	}


	@Override
	public long countId() {

		return (long)clienteRepositorio.countId();
		
	}

}
