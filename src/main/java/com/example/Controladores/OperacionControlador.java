package com.example.Controladores;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.Entidades.Cuenta;
import com.example.Entidades.Operacion;
import com.example.Servicios.CuentaServiceI;
import com.example.Servicios.OperacionServiceI;

@Controller
public class OperacionControlador {

	@Autowired
	private OperacionServiceI operacionServiceI;
	@Autowired
	private CuentaServiceI cuentaServiceI;
	
	@GetMapping("/cuentas1")
	public String mostrarOperaciones(Model model) {
		
		final List<Cuenta> cuentas = cuentaServiceI.obtenerTodasCuentas();
		final List<Operacion> listaOperaciones = new ArrayList<Operacion>();
		
		model.addAttribute("listaDatos",listaOperaciones);
		model.addAttribute("cuentas", cuentas);
		return "OperacionesPorCuenta";
	}
	
	
	
	@PostMapping("/cuentas2")
	public String mostrarCuentasPorId(@RequestParam String id, Model model) {
		
		List<Operacion> listaOperaciones = cuentaServiceI.findCuentaByID(Long.parseLong(id)).getOperaciones();
		
		model.addAttribute("listaDatos",listaOperaciones);
		model.addAttribute("cuentas", cuentaServiceI.obtenerTodasCuentas());
		
		return "OperacionesPorCuenta";
		
	}

	
}
