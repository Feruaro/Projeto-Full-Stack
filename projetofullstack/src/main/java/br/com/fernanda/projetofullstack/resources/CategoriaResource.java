package br.com.fernanda.projetofullstack.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.fernanda.projetofullstack.domains.Categoria;
import br.com.fernanda.projetofullstack.services.CategoriaService;

@RestController
@RequestMapping(path="/categorias")
public class CategoriaResource {
	
	@Autowired
	private CategoriaService service;
	
	@GetMapping(path="/{id}")
	public ResponseEntity<Categoria> Buscar(@PathVariable Integer id) {
		Categoria cat = service.Buscar(id);
		return ResponseEntity.ok().body(cat);
	}
}
