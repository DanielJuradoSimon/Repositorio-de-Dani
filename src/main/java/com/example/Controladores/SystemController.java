package com.example.Controladores;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("*")
public class SystemController {
	
	
	//Capta cualquier solicitud
	@GetMapping
	public String showIndex() {
		return "index";
	}
	
	//Redirecciona al controlador de gestión de coches
	@GetMapping("/verClientes")
	public String verClientes() {
		return "redirect:mostrarClientes";
	}
	
	@GetMapping("/verCuentas")
	public String verCuentas() {
		return "redirect:mostrarCuentas";
	}
	
	@GetMapping("/ListarOperacionesPorCuenta")
	public String listOperPorCuenta() {
		return "redirect:mostrarClientes2";
	}
	
	
	
	//Redirecciona a la plantilla de búsqueda
	/*@GetMapping("/searchCarByView")
	public String redirectToCarSearchByTemplate() {
		return "searchCarBy";
	}*/
	
	//Redirecciona a la plantilla de insercción
	@GetMapping("/aniadirCliente")
	public String redirectToNewCarTemplate() {
		return "nuevoCliente";
	}

}
