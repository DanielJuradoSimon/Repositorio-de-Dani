package com.example.Controladores;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("*")
public class SystemController {
	
	
	@GetMapping
	public String showIndex() {
		return "index";
	}
	
	@GetMapping("/verClientes")
	public String verClientes() {
		return "redirect:mostrarClientes";
	}
	
	@GetMapping("/verCuentas")
	public String verCuentas() {
		return "redirect:mostrarCuentas";
	}
	
	@GetMapping("/ListarCuentasPorCliente")
	public String listCuentPorClient() {		
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
	
	@GetMapping("/AnadirCliente")
	public String anadirCliente() {
		return "NuevoCliente";
	}
	
	@GetMapping("/AnadirCuenta")
	public String anadirCuenta() {
		return "NuevaCuenta";
	}
	
	@GetMapping("/RetirarIngresar")
	public String RetirarIngresar() {
		return "redirect:ListasCuentasClientes";
	}
	
	@GetMapping("/HacerTransferencia")
	public String HacerTransferencia() {
		return "redirect:SacarClientesTransaccion";
	}
	
	@GetMapping("/CuentaACliente")
	public String CuentaACliente() {
		return "redirect:SacarClientes";
	}
	
	
}
