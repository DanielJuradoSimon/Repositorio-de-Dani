package com.example.Controladores;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.Entidades.Cliente;
import com.example.Servicios.ClienteServiceI;

@Controller
public class ClientesControlador {

	@Autowired
	private ClienteServiceI clienteServiceI;
	
	@GetMapping("/mostrarClientes")
	public String mostrarCoches(Model model) {

		// Obtención de clientes
		final List<Cliente> listaClientes = clienteServiceI.obtenerTodosClientes();

		// Carga de datos al modelo
		model.addAttribute("clientesListView", listaClientes);
		model.addAttribute("btnBorrarClienteEnabled", Boolean.FALSE);

		return "MostrarClientes";
	}
}
