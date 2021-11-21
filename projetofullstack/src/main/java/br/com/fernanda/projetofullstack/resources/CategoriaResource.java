package br.com.fernanda.projetofullstack.resources;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.fernanda.projetofullstack.domains.Categoria;
import br.com.fernanda.projetofullstack.services.CategoriaService;

@RestController
@RequestMapping(path="/categorias")
public class CategoriaResource {
	
	@Autowired
	private CategoriaService service;
	
	@GetMapping(path="/{id}")
	public ResponseEntity<Categoria> Find(@PathVariable Integer id) {
		Categoria cat = service.Find(id);
		return ResponseEntity.ok().body(cat);
	}
	
	@PostMapping
	public ResponseEntity<Void> Insert(@RequestBody Categoria cat) {
		cat = service.Insert(cat);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(cat.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@PutMapping(path = "/{id}")
	public ResponseEntity<Void> Update(@PathVariable Integer id, @RequestBody Categoria cat){
		cat.setId(id);
		cat = service.Update(cat);
		return ResponseEntity.noContent().build();
	}
	
	@DeleteMapping(path="/{id}")
	public ResponseEntity<Categoria> Delete(@PathVariable Integer id) {
		service.Delete(id);
		return ResponseEntity.noContent().build();
	}
}
