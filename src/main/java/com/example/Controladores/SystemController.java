package com.example.Controladores;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
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
	
	@GetMapping("/ListarOperacionesPorCuenta2")
	public String listOperacionesPorCuenta() {
		return "redirect:cuentas1";
	}
	
	@GetMapping("/AnadirClienteACuenta")
	public String ClienteACuenta() {
		return "redirect:anadirClienteACuenta";
	}
	
	
	
	//Redirecciona a la plantilla de búsqueda
	/*@GetMapping("/searchCarByView")
	public String redirectToCarSearchByTemplate() {
		return "searchCarBy";
	}*/
	
	//Redirecciona a la plantilla de insercción
	@GetMapping("/AnadirCliente")
	public String anadirCliente() {
		return "NuevoCliente";
	}
	
	@GetMapping("/AnadirCuenta")
	public String anadirCuenta() {
		return "NuevaCuenta";
	}
	

}
