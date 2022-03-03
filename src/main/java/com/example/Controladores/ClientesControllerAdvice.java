package com.example.Controladores;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ClientesControllerAdvice {
	
	 //Capta cualquier excepción en los métodos de cualquier controlador.

	@ExceptionHandler(Exception.class)
	public String handleException(HttpServletRequest req, Exception e, Model model) {

		// Respuesta.
		model.addAttribute("errorMsg", e.getMessage());

		return "error";
	}
	
	

}
