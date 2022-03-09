package com.example.Controladores;

import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.Entidades.Cliente;
import com.example.Entidades.Cuenta;
import com.example.Entidades.ModeloAux;
import com.example.Entidades.ModeloAux2;
import com.example.Entidades.Operacion;
import com.example.Servicios.ClienteServiceI;
import com.example.Servicios.CuentaServiceI;
import com.example.Servicios.OperacionServiceI;

@Controller
public class CuentasControlador {

	private long idCuentaAux = 0L;
	
	@Autowired
	private CuentaServiceI cuentaServiceI;
	
	@Autowired
	private ClienteServiceI clienteServiceI;
	
	@Autowired
	private OperacionServiceI operacionServiceI;
	
	@PostMapping("/mostrarClientes2")
	public String mostrarClientes(Model model) {
		
		System.out.println("HOLAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA");
		List<Cliente> listaClientes = clienteServiceI.obtenerTodosClientes();
		List<Cuenta> listaCuentas = new ArrayList<>();

		// Carga de datos al modelo
		model.addAttribute("clientesListView", listaClientes);
		model.addAttribute("cuentasListView", listaCuentas);
		model.addAttribute("btnBorrarCuentaEnabled", Boolean.FALSE);

		return "CuentasPorCliente";
	}
	
	@PostMapping("/mostrarCuentasPorCliente")
	public String mostrarCuentasPorCliente(@RequestParam String id, Model model) {
		
		System.out.println("-----------"+id);
		List<Cliente> listaClientes = clienteServiceI.obtenerTodosClientes();
		List<Long> listaIds = cuentaServiceI.findCuentaByClienteID(Long.parseLong(id));

		
		// Obtención de clientes
		List<Cuenta> listaCuentas = new ArrayList<>();
		
		for(long cuenta : listaIds) {
			Cuenta c = cuentaServiceI.findCuentaByID(cuenta);//Uso este objeto cuenta porque no lo coge directamente
			listaCuentas.add(c);
		}
		
		
		model.addAttribute("cuentasListView", listaCuentas);
		model.addAttribute("clientesListView", listaClientes);
		
		return "CuentasPorCliente";
	}
	
	@GetMapping("/mostrarCuentas")
	public String mostrarCuentas(Model model) {

		// Obtención de clientes
		List<Cuenta> listaCuentas = cuentaServiceI.obtenerTodasCuentas();

		// Carga de datos al modelo
		model.addAttribute("cuentasListView", listaCuentas);
		model.addAttribute("btnBorrarCuentaEnabled", Boolean.FALSE);

		return "MostrarCuentas";
	}
	
	@PostMapping("/eliminarCuenta")
	public String eliminaCuenta(@RequestParam String cuentaId, Model model) {

		// Eliminación de vehículo
		cuentaServiceI.eliminarCuentaPorId(Long.parseLong(cuentaId));

		return "redirect:mostrarCuentas";

	}
	
	@PostMapping("/eliminarCuentaPorCliente")
	public String eliminaCuentaPorCliente(@RequestParam String cuentaId, Model model) {

		// Eliminación de vehículo
		cuentaServiceI.eliminarCuentaPorId(Long.parseLong(cuentaId));

		return "redirect:/mostrarClientes2";

	}
	
	@GetMapping("/actualizarCuenta")
	public String recogerCuenta(String cuentaId, Model model) {
		
	  
		idCuentaAux = Long.valueOf(cuentaId);
	  
	  Cuenta c = cuentaServiceI.findCuentaByID(Long.valueOf(cuentaId));

	//Carga de datos al modelo
	  model.addAttribute("cuentaId", c.getId());
	  model.addAttribute("numeroCuenta", c.getNumCuenta());
	  model.addAttribute("fecha_crea", c.getFecha_crea());
	  model.addAttribute("saldo", c.getSaldo());
	  
	  System.out.println("RECOGER DATOS --- "+c.toString());
				
		return "ActualizarCuentas";
	}
	

	@GetMapping("/actEditCuenta")
	public String actualizarCuenta(@Valid @ModelAttribute Cuenta Cuenta, BindingResult result) throws Exception {

		if (result.hasErrors()) {
			//throw new Exception("Parámetros de id erróneos");
			System.out.println(result.getAllErrors());
		}
		else {
			Cuenta c = cuentaServiceI.findCuentaByID(Long.valueOf(Cuenta.getId()));

			c.setNumCuenta(Cuenta.getNumCuenta());
			c.setFecha_crea(Cuenta.getFecha_crea());
			c.setSaldo(Cuenta.getSaldo());
			
			System.out.println("setear datos ---- "+c.toString());
			
			cuentaServiceI.actualizarCuenta(c);
			
		}
		
		
		

		return "redirect:mostrarCuentas";
	}
	

	@PostMapping("/nuevaCuenta")
	private String aniadirCliente(@Valid @ModelAttribute Cuenta nuevaCuenta, Model model, BindingResult result) throws Exception {

		nuevaCuenta.setId(cuentaServiceI.countId()+1);
		
		if (result.hasErrors()) {
			System.out.println(nuevaCuenta.toString());
			throw new Exception("Parámetros erróneos");
		} else {

			cuentaServiceI.aniadirCuenta(nuevaCuenta);
		}

		return "redirect:mostrarCuentas";
	}
	
	@PostMapping("/Operacion")
	private String operacion(@Valid @ModelAttribute ModeloAux modelo, Model model, BindingResult result) throws Exception {
		System.out.println("-------------------------------11111111111");
		Cuenta cuenta = cuentaServiceI.findCuentaByID(Long.parseLong(modelo.getId()));
		
		if (result.hasErrors()) {
			throw new Exception("Parámetros erróneos");
		} else {

			if(modelo.getOpcion().equalsIgnoreCase("INGRESAR")) {
				cuenta.setSaldo(cuenta.getSaldo()+Long.parseLong(modelo.getCantidad()));
			}else {
				cuenta.setSaldo(cuenta.getSaldo()-Long.parseLong(modelo.getCantidad()));	
			}
			
			cuentaServiceI.actualizarCuenta(cuenta);
			System.out.println("-------------------------------");
		}
		
		Date fechadate = new Date();
		String fecha = fechadate.toInstant()
				.atOffset(ZoneOffset.UTC)
				.format( DateTimeFormatter.ofPattern("dd-MM-yyyy"));
		
		Operacion operacion = new Operacion();
		operacion.setTipoOperacion(modelo.getOpcion());
		operacion.setFecha_de_realizacion(fecha);
		operacion.setCantidad(Long.parseLong(modelo.getCantidad()));
		operacion.setCuenta_id(modelo.getId());
		operacionServiceI.aniadirOperacion(operacion);

		return "redirect:mostrarCuentas";
	}
	
	@GetMapping("/mostrarCuentasPorCliente2")
	public String mostrarCuentasPorCliente2(@RequestParam String id, Model model) {
		
		System.out.println("-----------"+id);
		List<Cliente> listaClientes = clienteServiceI.obtenerTodosClientes();
		List<Long> listaIds = cuentaServiceI.findCuentaByClienteID(Long.parseLong(id));

		
		List<Cuenta> listaCuentas = new ArrayList<>();
		
		for(long cuenta : listaIds) {
			Cuenta c = cuentaServiceI.findCuentaByID(cuenta);
			listaCuentas.add(c);
		}
		
		
		model.addAttribute("cuentasListView", listaCuentas);
		model.addAttribute("clientesListView", listaClientes);
		
		return "Operaciones";
	}
	
	
	@PostMapping("/Transferencia")
	private String Transferencia(@Valid @ModelAttribute ModeloAux2 modelo, Model model, BindingResult result) throws Exception {
		System.out.println("-------------------------------11111111111");
		Cuenta cuenta = cuentaServiceI.findCuentaByID(Long.parseLong(modelo.getId()));
		Cuenta cuenta2 = cuentaServiceI.findCuentaByID(Long.parseLong(modelo.getId2()));
		
		if (result.hasErrors()) {
			throw new Exception("Parámetros erróneos");
		} else {

			cuenta.setSaldo(cuenta.getSaldo() - Long.parseLong(modelo.getCantidad()));
			cuenta2.setSaldo(cuenta2.getSaldo() + Long.parseLong(modelo.getCantidad()));
		}
		
		Date fechadate = new Date();
		String fecha = fechadate.toInstant()
				.atOffset(ZoneOffset.UTC)
				.format( DateTimeFormatter.ofPattern("dd-MM-yyyy"));
		
		Operacion operacion = new Operacion();
		operacion.setTipoOperacion("TRANSFERENCIA_SALIENTE");
		operacion.setFecha_de_realizacion(fecha);
		operacion.setCantidad(Long.parseLong(modelo.getCantidad()));
		operacion.setCuenta_id(modelo.getId());
		operacionServiceI.aniadirOperacion(operacion);
		
		Operacion operacion2 = new Operacion();
		operacion2.setTipoOperacion("TRANSFERENCIA_ENTRANTE");
		operacion2.setFecha_de_realizacion(fecha);
		operacion2.setCantidad(Long.parseLong(modelo.getCantidad()));
		operacion2.setCuenta_id(modelo.getId2());
		operacionServiceI.aniadirOperacion(operacion2);

		return "redirect:mostrarCuentas";
	}
	
	@GetMapping("/mostrarCuentasPorCliente3")
	public String mostrarCuentasPorCliente3(@RequestParam String id, Model model) {
		
		System.out.println("-----------"+id);
		List<Cliente> listaClientes = clienteServiceI.obtenerTodosClientes();
		List<Long> listaIds = cuentaServiceI.findCuentaByClienteID(Long.parseLong(id));

		
		List<Cuenta> listaCuentas = new ArrayList<>();
		
		for(long cuenta : listaIds) {
			Cuenta c = cuentaServiceI.findCuentaByID(cuenta);
			listaCuentas.add(c);
		}
		
		List<Cuenta> listaTodasCuentas = cuentaServiceI.obtenerTodasCuentas();
		
		
		model.addAttribute("cuentasListView", listaCuentas);
		model.addAttribute("clientesListView", listaClientes);
		model.addAttribute("todasCuentasListView", listaTodasCuentas);
		
		return "Transaccion";
	}

}
