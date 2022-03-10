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
	
	/**
	 * Adjunta a la vista todos los registros de clientes en la base de datos
	 * 
	 * @param model
	 * @return vista MostrarClientes
	 */
	@GetMapping("/mostrarClientes")
	public String mostrarClientes(Model model) {

		// Obtención de clientes
		final List<Cliente> listaClientes = clienteServiceI.obtenerTodosClientes();

		// Carga de datos al modelo
		model.addAttribute("clientesListView", listaClientes);
		model.addAttribute("btnBorrarClienteEnabled", Boolean.FALSE);

		return "MostrarClientes";
	}
	
	/**
	 * Método que elimina de la base de datos el registro con la id proporcionada
	 * 
	 * @param clienteId
	 * @param model
	 * @return redirecciona al método mostrarClientes
	 */
	@PostMapping("/eliminarCliente")
	public String eliminarCliente(@RequestParam String clienteId, Model model) {

		// Eliminación de vehículo
		clienteServiceI.eliminarClientePorId(Long.parseLong(clienteId));

		return "redirect:mostrarClientes";

	}
	
	/**
	 * Este método recoge un registro y manda sus campos a la vista para que se muestren y se puedan editar
	 * 
	 * @param clienteId
	 * @param model
	 * @return redirecciona a la vista ActualizarClientes
	 */
	@GetMapping("/actualizarCliente")
	public String recogerCliente(String id, Model model) {
		
	  
		idClienteAux = Long.valueOf(id);
	  
	  Cliente c = clienteServiceI.findClienteByID(Long.valueOf(id));

	//Carga de datos al modelo
	  model.addAttribute("id", c.getId());
	  model.addAttribute("DNI", c.getDNI());
	  model.addAttribute("nombre", c.getNombre());
	  model.addAttribute("apellidos", c.getApellidos());
	  model.addAttribute("fecha_nac", c.getFecha_nac());
	  model.addAttribute("direccion", c.getDireccion());
	  model.addAttribute("email", c.getEmail());
	  model.addAttribute("telefono", c.getTelefono());
	  
				
		return "ActualizarClientes";
	}
	

	/**
	 * Este método recoge los campos modificados y los guarda en base a un objeto dado, sobreescribiéndolo
	 * 
	 * @param Cliente
	 * @param result
	 * @return redirecciona al método mostrarClientes 
	 * @throws Exception
	 */
	@GetMapping("/actEditCliente")
	public String actualizarCliente(@Valid @ModelAttribute Cliente Cliente, BindingResult result) throws Exception {

		Cliente c = new Cliente();

		System.out.println(Cliente.toString());
		c.setId(Cliente.getId());
		c.setDNI(Cliente.getDNI());
		c.setNombre(Cliente.getNombre());
		c.setApellidos(Cliente.getApellidos());
		c.setFecha_nac(Cliente.getFecha_nac());
		c.setDireccion(Cliente.getDireccion());
		c.setEmail(Cliente.getEmail());
		c.setTelefono(Cliente.getTelefono());
		
		System.out.println(c.toString());
		
		if (result.hasErrors()) {
			System.out.println(result.getAllErrors());
		}
		else {
			
			clienteServiceI.actualizarCliente(c);
			
		}
		

		return "redirect:mostrarClientes";
	}
	
	/**
	 * Este método recoge un objeto Cliente por parámetro y lo introduce en la base de datos
	 * 
	 * @param nuevoCliente
	 * @param result
	 * @return redirecciona al método mostrarClientes
	 * @throws Exception
	 */
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
	
	/**
	 * Este método recoge la id de una cuenta y busca los clientes en base a esa id.
	 * 
	 * @param cuentaId
	 * @param model
	 * @return redirecciona a la vista ListarCuentasPorCliente
	 */
	@GetMapping("/mostrarClientesPorCuenta")
	public String mostrarClientesPorCuenta(String cuentaId, Model model) {

		idCuentaAux = Long.valueOf(cuentaId);
		
		// Obtención de clientes
		final List<Integer> listaClientes = clienteServiceI.findClienteByCuentaID(idCuentaAux);

		// Carga de datos al modelo
		model.addAttribute("clientesPorCuentaListView", listaClientes);

		return "ListarCuentasPorCliente";
	}
	
	/**
	 * Este método añade clientes y cuentas a la vista
	 * 
	 * @param model
	 * @return redirecciona a la vista ClienteACuenta
	 */
	
	@GetMapping("/anadirClienteACuenta")
	private String anadirClienteLista(Model model) {

		final List<Cliente> clientesList = clienteServiceI.obtenerTodosClientes();
		final List<Cuenta> cuentasList = cuentaServiceI.obtenerTodasCuentas();

		// Carga de datos al modelo
		model.addAttribute("ClientesListView", clientesList);
		model.addAttribute("CuentasListView", cuentasList);

		return "ClienteACuenta";

	}
	
	/**
	 * Este método inserta en la tabla intermedia un nuevo registro basado en las id de un cliente y una cuenta
	 * 
	 * @param Clientecuenta
	 * @param result
	 * @return redirecciona al índice
	 * @throws Exception
	 */
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
