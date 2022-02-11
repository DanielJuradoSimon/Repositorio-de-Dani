package com.example;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.Entidades.Operacion;
import com.example.Servicios.OperacionServiceI;

@SpringBootApplication
public class MiProyectoApplication /*implements CommandLineRunner*/{

	//@Autowired
	//private OperacionServiceI operacionServiceI;
	
	public static void main(String[] args) {
		SpringApplication.run(MiProyectoApplication.class, args);
	}

	/*@Override
	public void run(String... args) throws Exception {
		
		/*if(operacionServiceI.obtenerTodosOperaciones().isEmpty()) {
			System.out.println("No hay datos disponibles");
		}else {
			for(Operacion operacion : operacionServiceI.obtenerTodosOperaciones()){
				System.out.println(operacion.toString());
			}
		}
		System.out.println("---------------");
	}*/

}
