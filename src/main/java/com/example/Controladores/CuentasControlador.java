package com.example.Controladores;

import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
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
	
	/**
	 * Este método manda a la vista las listas de clientes y cuentas
	 * 
	 * @param model
	 * @return redirecciona a la vista CuentasPorCliente
	 * 
	 */
	@GetMapping("/mostrarClientes2")
	public String mostrarClientes(Model model) {
		
		List<Cliente> listaClientes = clienteServiceI.obtenerTodosClientes();
		List<Cuenta> listaCuentas = new ArrayList<>();

		// Carga de datos al modelo
		model.addAttribute("clientesListView", listaClientes);
		model.addAttribute("cuentasListView", listaCuentas);
		model.addAttribute("btnBorrarCuentaEnabled", Boolean.FALSE);

		return "CuentasPorCliente";
	}
	
	/**
	 * Este método nos retorna las cuentas pertenecientes a un cliente basándose en la id del mismo
	 * 
	 * @param id
	 * @param model
	 * @return redirecciona a la vista CuentasPorCliente
	 * 
	 */
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
	
	/**
	 * Este método devuelve una lista con todas las cuentas
	 * 
	 * @param model
	 * @return redirecciona a la vista MostrarCuentas
	 * 
	 */
	@GetMapping("/mostrarCuentas")
	public String mostrarCuentas(Model model) {

		// Obtención de clientes
		List<Cuenta> listaCuentas = cuentaServiceI.obtenerTodasCuentas();

		// Carga de datos al modelo
		model.addAttribute("cuentasListView", listaCuentas);
		model.addAttribute("btnBorrarCuentaEnabled", Boolean.FALSE);

		return "MostrarCuentas";
	}
	
	/**
	 * Este método elimina un registro de Cuenta basándose en la id pasada por parámetro
	 * @param cuentaId
	 * @param model
	 * @return redirecciona al método mostrarCuentas
	 */
	@PostMapping("/eliminarCuenta")
	public String eliminaCuenta(@RequestParam String cuentaId, Model model) {


		clienteServiceI.borradoClienteCuenta(Long.parseLong(cuentaId));
		operacionServiceI.borrarOperacionesPorCuenta(Long.parseLong(cuentaId));
		cuentaServiceI.eliminarCuentaPorId(Long.parseLong(cuentaId));

		return "redirect:mostrarCuentas";

	}
	
	/**
	 * Este método elimina un registro de Cuenta basándose en la id pasada por parámetro
	 * 
	 * @param cuentaId
	 * @param model
	 * @return redirecciona al método mostrarClientes2
	 * 
	 */
	
	@PostMapping("/eliminarCuentaPorCliente")
	public String eliminaCuentaPorCliente(@RequestParam String cuentaId, Model model) {

		clienteServiceI.borradoClienteCuenta(Long.parseLong(cuentaId));
		operacionServiceI.borrarOperacionesPorCuenta(Long.parseLong(cuentaId));
		cuentaServiceI.eliminarCuentaPorId(Long.parseLong(cuentaId));

		return "redirect:/mostrarClientes2";

	}
	
	
	/**
	 * Este método, en base a una id de un registro Cuenta, saca los campos del mismo y los manda a la vista
	 * 
	 * @param cuentaId
	 * @param model
	 * @return redirecciona a la vista ActualizarCuentas
	 * 
	 */
	@GetMapping("/actualizarCuenta")
	public String recogerCuenta(String cuentaId, Model model) {
		
	  
		idCuentaAux = Long.valueOf(cuentaId);
	  
	  Cuenta c = cuentaServiceI.findCuentaByID(Long.valueOf(cuentaId));

	//Carga de datos al modelo
	  model.addAttribute("cuentaId", c.getId());
	  model.addAttribute("numeroCuenta", c.getNumCuenta());
	  model.addAttribute("fecha_crea", c.getFecha_crea());
	  model.addAttribute("saldo", c.getSaldo());
				
		return "ActualizarCuentas";
	}
	

	/**
	 * Modifica los datos ofrecidos de un registro cuenta y los sobreescribe en la base de datos
	 * 
	 * @param Cuenta
	 * @param result
	 * @return redirecciona al método mostrarCuentas
	 * @throws Exception
	 */
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
	

	/**
	 * Este método recoge un objeto Cuenta y lo guarda en la base de datos
	 * 
	 * @param nuevaCuenta
	 * @param model
	 * @param result
	 * @return redirecciona al método mostrarCuentas
	 * @throws Exception
	 */
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
	
	/**
	 * Este método devuelve a la vista una lista con todos los clientes para mostrarla en el "select"
	 * 
	 * @param model
	 * @return redirecciona a la vista Operaciones
	 */
	
	@GetMapping("/ListasCuentasClientes")
	public String listasCuentasClientes( Model model) {
		List<Cliente> listaClientes = clienteServiceI.obtenerTodosClientes();
		List<Cuenta> listaCuentas = new ArrayList<>();
		
		model.addAttribute("clientesListView", listaClientes);
		
		return "Operaciones";
	}
	
	/**
	 * Este método recibe un ModeloAux que contiene una opcion que será ingresar o retirar, una cantidad de dinero determinada y la id de la cuenta a la que se lo aplicaremos.
	 * En base a la opción, se buscará la cuenta con la id en cuestión y se le restará o sumará la cantidad correspondiente. Por último se registra la operación en la tabla correspondiente.
	 * 
	 * @param modelo
	 * @param model
	 * @param result
	 * @return redirecciona al método mostrarCuentas
	 * @throws Exception
	 */
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
	
	/**
	 * Este método devuelve a la vista una lista con todos los clientes y una lista con las cuentas asociadas a un cliente cuya id recibimos por parámetro
	 * 
	 * @param id
	 * @param model
	 * @return redirecciona a la vista Operaciones
	 */
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
	
	/**
	 * Este método recibe un modelo con las id de dos cuentas y una cantidad de dinero. Crea dos objetos cuenta en base a esas id y le resta a la 1ª la cantidad pasada en el modelo,
	 * a la vez que se la añade a la 2ª. Por último añade las dos operaciones a la tabla correspondiente.
	 * 
	 * @param modelo
	 * @param model
	 * @param result
	 * @return redirecciona al método mostrarCuentas
	 * @throws Exception
	 */
	
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
	
	/**
	 * Este método manda una lista con todos los clientes a la vista
	 * 
	 * @param model
	 * @return redirecciona a la vista Transaccion
	 */
	@GetMapping("/SacarClientesTransaccion")
	public String SacarClientesTransaccion (Model model) {
		List<Cliente> listaClientes = clienteServiceI.obtenerTodosClientes();
		model.addAttribute("clientesListView", listaClientes);
		
		return "Transaccion";
		
	}
	
	/**
	 * Este método manda a la vista una lista con todos los clientes, otra lista con todas las cuentas y una última lista con las cuentas asociadas a un cliente cuya id se da por parámetro
	 * 
	 * @param id
	 * @param model
	 * @return redirecciona a la vista Transaccion
	 */
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
	
	/**
	 * Este método pasa a la vista una lista con todos los clientes
	 * 
	 * @param model
	 * @return redirecciona a la vista AnadirCuentaPorCliente
	 */
	@GetMapping("/SacarClientes")
	public String SacarClientes (Model model) {
		List<Cliente> listaClientes = clienteServiceI.obtenerTodosClientes();
		model.addAttribute("clientesListView", listaClientes);
		
		return "AnadirCuentaPorCliente";
		
	}
	
	/**
	 * Este método recibe un objeto Cuenta y la id de un Cliente. Añade a la base de datos el registro de la Cuenta y añade en la tabla correspondiente la relación entre esta cuenta
	 * Y el cliente al cual pertenece la id aportada.
	 * 
	 * @param nuevaCuenta
	 * @param id
	 * @param model
	 * @param result
	 * @return redirecciona al método mostrarCuentas
	 * @throws Exception
	 */
	@PostMapping("/AnadirCuentaPorCliente")
	private String AnadirCuentaPorCliente(@Valid @ModelAttribute Cuenta nuevaCuenta, @RequestParam String id, Model model, BindingResult result) throws Exception {
		
		
		nuevaCuenta.setId(cuentaServiceI.countId()+1);
		
		if (result.hasErrors()) {
			throw new Exception("Parámetros erróneos");
		} else {

			cuentaServiceI.aniadirCuenta(nuevaCuenta);
		}
		
		
		clienteServiceI.insercionClienteCuenta(Long.parseLong(id), nuevaCuenta.getId());
		

		return "redirect:mostrarCuentas";
	}

}
