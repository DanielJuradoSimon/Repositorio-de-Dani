package com.example.Servicios;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery.FetchableFluentQuery;
import org.springframework.stereotype.Service;

import com.example.Entidades.Cliente;
import com.example.Entidades.Cuenta;
import com.example.Entidades.Operacion;
import com.example.Repositorios.CuentaRepositorioI;

@Service
public class CuentaServiceImpl implements CuentaServiceI{

	@Autowired
	private CuentaRepositorioI cuentaRepositorio;

	@Override
	public List<Cuenta> obtenerTodasCuentas() {

		return cuentaRepositorio.findAll();
		
	}
	
	@Override
	public void eliminarCuentaPorId(long ClienteId) {
		cuentaRepositorio.deleteById(ClienteId);
		
	}
	
	@Override
	public Cuenta findCuentaByID(long id) {
		
		Cuenta c = cuentaRepositorio.getById(id);
		return c;
		
	}
	
	public List<Long> findCuentaByClienteID(long id){
		
		return cuentaRepositorio.findCuentaByClienteID(id);
	}

	/*@Override
	public List<Cuenta> obtenerCuentasPorCliente(Cliente Cliente) {
		
		return cuentaRepositorio.findByCliente(Cliente);
		
	}

	@Override
	public List<Operacion> obtenerOperacionesPorCuenta(Cuenta Cuenta) {

		return cuentaRepositorio.findOperacionByCuenta(Cuenta);
		
	}*/

	@Override
	public void eliminarCuenta(Cuenta Cuenta) {
		
		cuentaRepositorio.delete(Cuenta);
		
	}

	@Override
	public void aniadirCuenta(Cuenta Cuenta) {
		
		cuentaRepositorio.save(null);
		
	}

	@Override
	public void actualizarCuenta(Cuenta Cuenta) {
		
		cuentaRepositorio.save(null);
		
	}
	
	
}
