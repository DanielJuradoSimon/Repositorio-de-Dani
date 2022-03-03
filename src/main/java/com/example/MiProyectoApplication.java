package com.example;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.Entidades.Cliente;
import com.example.Entidades.Operacion;
import com.example.Servicios.ClienteServiceI;
import com.example.Servicios.OperacionServiceI;

@SpringBootApplication
public class MiProyectoApplication implements CommandLineRunner{

	@Autowired
	private OperacionServiceI operacionServiceI;
	@Autowired
	private ClienteServiceI clienteServiceI;
	
	public static void main(String[] args) {
		SpringApplication.run(MiProyectoApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		if(operacionServiceI.obtenerTodosOperaciones().isEmpty()) {
			System.out.println("No hay datos disponibles");
		}else {
			for(Operacion operacion : operacionServiceI.obtenerTodosOperaciones()){
				System.out.println(operacion.toString());
			}
		}
		
		System.out.println("---------------");
		
		if(clienteServiceI.obtenerTodosClientes().isEmpty()) {
			System.out.println("No hay datos disponibles");
		}else {
			for(Cliente cliente : clienteServiceI.obtenerTodosClientes()){
				System.out.println(cliente.toString());
			}
		}
		System.out.println("---------------");
		
		Cliente cliente1 = new Cliente();
		cliente1.setDNI("88888888H");
		cliente1.setId((long) 8);
		cliente1.setNombre("Enric");
		cliente1.setApellidos("Benavente");
		cliente1.setFecha_nac("1990-10-02");
		cliente1.setDireccion("Calle 8");
		cliente1.setEmail("8@gmail.com");
		cliente1.setTelefono("678546342");
		
		for(Cliente cliente : clienteServiceI.obtenerTodosClientes()){
			if (cliente.getId()==cliente1.getId()) {
				return;
			}else {
				clienteServiceI.aniadirCliente(cliente1);
			}
		}
		
	 System.out.println("-------------------------------------------------------------------");
	 System.out.println("-------------------------------------------------------------------");
	 
	 
	 if(clienteServiceI.obtenerTodosClientes().isEmpty()) {
			System.out.println("No hay datos disponibles");
		}else {
			for(Cliente cliente : clienteServiceI.obtenerTodosClientes()){
				System.out.println(cliente.toString());
			}
		}
		
	}

}
