package br.com.fernanda.projetofullstack.resources;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.fernanda.projetofullstack.domains.Categoria;



@RestController
@RequestMapping(path="/categorias")
public class CategoriaResource {
	
	@GetMapping
	public List<Categoria> Listar() {
		Categoria cat1 = new Categoria(1, "Informática");
		Categoria cat2 = new Categoria(1, "Escritório");
		
		List<Categoria> listaCat = new ArrayList<>();
		listaCat.add(cat1);
		listaCat.add(cat2);
		
		return listaCat;
	}
}
