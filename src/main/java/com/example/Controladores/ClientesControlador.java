package com.example.Controladores;

import java.text.SimpleDateFormat;
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
import com.example.Entidades.Cliente_cuenta;
import com.example.Entidades.Cuenta;
import com.example.Servicios.ClienteServiceI;
import com.example.Servicios.CuentaServiceI;

@Controller
public class ClientesControlador {

	private long idClienteAux = 0L;
	private long idCuentaAux = 0L;
	
	@Autowired
	private ClienteServiceI clienteServiceI;
	
	@Autowired
	private CuentaServiceI cuentaServiceI;
	
	@GetMapping("/mostrarClientes")
	public String mostrarClientes(Model model) {

		// Obtención de clientes
		final List<Cliente> listaClientes = clienteServiceI.obtenerTodosClientes();

		// Carga de datos al modelo
		model.addAttribute("clientesListView", listaClientes);
		model.addAttribute("btnBorrarClienteEnabled", Boolean.FALSE);

		return "MostrarClientes";
	}
	
	@PostMapping("/eliminarCliente")
	public String eliminarCliente(@RequestParam String clienteId, Model model) {

		// Eliminación de vehículo
		clienteServiceI.eliminarClientePorId(Long.parseLong(clienteId));

		return "redirect:mostrarClientes";

	}
	
	@GetMapping("/actualizarCliente")
	public String recogerCliente(String clienteId, Model model) {
		
	  
		idClienteAux = Long.valueOf(clienteId);
	  
	  Cliente c = clienteServiceI.findClienteByID(Long.valueOf(clienteId));

	//Carga de datos al modelo
	  model.addAttribute("DNI", c.getDNI());
	  model.addAttribute("nombre", c.getNombre());
	  model.addAttribute("apellidos", c.getApellidos());
	  model.addAttribute("fecha_nac", c.getFecha_nac());
	  model.addAttribute("direccion", c.getDireccion());
	  model.addAttribute("email", c.getEmail());
	  model.addAttribute("telefono", c.getTelefono());
	  
				
		return "ActualizarClientes";
	}
	

	@GetMapping("/actEditCliente")
	public String actualizarCliente(@Valid @ModelAttribute Cliente Cliente, BindingResult result) throws Exception {

		Cliente c = new Cliente();

		c.setDNI(Cliente.getDNI());
		c.setNombre(Cliente.getNombre());
		c.setApellidos(Cliente.getApellidos());
		c.setFecha_nac(Cliente.getFecha_nac());
		c.setDireccion(Cliente.getDireccion());
		c.setEmail(Cliente.getEmail());
		c.setTelefono(Cliente.getTelefono());
		
		
		if (result.hasErrors()) {
			System.out.println(result.getAllErrors());
		}
		else {
			
			clienteServiceI.eliminarClientePorId(idClienteAux);
			clienteServiceI.actualizarCliente(c);
			
		}
		

		return "redirect:mostrarClientes";
	}
	

	@PostMapping("/nuevoCliente")
	private String aniadirCliente(@Valid @ModelAttribute Cliente nuevoCliente, BindingResult result) throws Exception {

		nuevoCliente.setId(clienteServiceI.countId()+1);
		
		if (result.hasErrors()) {
			System.out.println(nuevoCliente.toString());
			throw new Exception("Parámetros erróneos");
		} else {

			clienteServiceI.aniadirCliente(nuevoCliente);
			
		}

		return "redirect:mostrarClientes";
	}
	
	@GetMapping("/mostrarClientesPorCuenta")
	public String mostrarClientesPorCuenta(String cuentaId, Model model) {

		idCuentaAux = Long.valueOf(cuentaId);
		
		// Obtención de clientes
		final List<Integer> listaClientes = clienteServiceI.findClienteByCuentaID(idCuentaAux);

		// Carga de datos al modelo
		model.addAttribute("clientesPorCuentaListView", listaClientes);
		//model.addAttribute("btnBorrarClienteEnabled", Boolean.FALSE);

		return "ListarCuentasPorCliente";
	}
	
	@GetMapping("/anadirClienteACuenta")
	private String anadirClienteLista(Model model) {

		final List<Cliente> clientesList = clienteServiceI.obtenerTodosClientes();
		final List<Cuenta> cuentasList = cuentaServiceI.obtenerTodasCuentas();

		// Carga de datos al modelo
		model.addAttribute("ClientesListView", clientesList);
		model.addAttribute("CuentasListView", cuentasList);

		return "ClienteACuenta";

	}
	
	@PostMapping("/anadirClienteCuenta")
	public String anadirClienteLista2(@Valid @ModelAttribute Cliente_cuenta Clientecuenta, BindingResult result)
			throws Exception {
		System.out.println(Clientecuenta.toString());
		
		if (result.hasErrors()) {
			throw new Exception("Parámetros de matriculación erróneos");
		} else {
	
			Cliente cl = clienteServiceI.findClienteByID(Long.valueOf(Clientecuenta.getCliente_id()));
			Cuenta cu = cuentaServiceI.findCuentaByID(Long.valueOf(Long.valueOf(Clientecuenta.getCuenta_id())));

	
			Cliente_cuenta cc = new Cliente_cuenta();
			cc.setCliente_id(cl.getId());
			cc.setCuenta_id(cu.getId());
			
			clienteServiceI.insercionClienteCuenta(cc.getCliente_id(), cc.getCuenta_id());
		

		}

		return "redirect:index";
	}
	
	

}
