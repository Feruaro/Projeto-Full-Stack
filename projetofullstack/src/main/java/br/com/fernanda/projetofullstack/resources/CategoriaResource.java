package br.com.fernanda.projetofullstack.resources;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController
@RequestMapping(path="/categorias")
public class CategoriaResource {
	
	@GetMapping
	public String Listar() {
		return "REST está funcionando";
	}
}
