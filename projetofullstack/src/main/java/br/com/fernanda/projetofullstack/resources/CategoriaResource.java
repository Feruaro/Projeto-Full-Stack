package br.com.fernanda.projetofullstack.resources;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.fernanda.projetofullstack.domains.Categoria;
import br.com.fernanda.projetofullstack.dto.CategoriaDTO;
import br.com.fernanda.projetofullstack.services.CategoriaService;

@RestController
@RequestMapping(path = "/categorias")
public class CategoriaResource {

	@Autowired
	private CategoriaService service;

	@GetMapping(path = "/{id}")
	public ResponseEntity<Categoria> Find(@PathVariable Integer id) {
		Categoria cat = service.Find(id);
		return ResponseEntity.ok().body(cat);
	}

	@GetMapping(path = "/page")
	public ResponseEntity<Page<CategoriaDTO>> FindPage(@RequestParam(value = "page", defaultValue = "0") Integer page,
			@RequestParam(value = "linesPerPages", defaultValue = "24") Integer linesPerPages,
			@RequestParam(value = "orderBy", defaultValue = "nome") String orderBy,
			@RequestParam(value = "direction", defaultValue = "ASC") String direction) {
		Page<Categoria> lista = service.findPage(page, linesPerPages, orderBy, direction);
		Page<CategoriaDTO> listaDTO = lista.map(obj -> new CategoriaDTO(obj));
		return ResponseEntity.ok().body(listaDTO);
	}

	@GetMapping
	public ResponseEntity<List<CategoriaDTO>> FindAll() {
		List<Categoria> lista = service.FindAll();
		List<CategoriaDTO> listaDTO = lista.stream().map(obj -> new CategoriaDTO(obj)).collect(Collectors.toList());
		return ResponseEntity.ok().body(listaDTO);
	}

	@PostMapping
	public ResponseEntity<Void> Insert(@Valid @RequestBody CategoriaDTO categoria) {
		Categoria cat = service.FromDTO(categoria);
		cat = service.Insert(cat);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(cat.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}

	@PutMapping(path = "/{id}")
	public ResponseEntity<Void> Update(@PathVariable Integer id, @Valid @RequestBody CategoriaDTO categoria) {
		Categoria cat = service.FromDTO(categoria);
		cat.setId(id);
		cat = service.Update(cat);
		return ResponseEntity.noContent().build();
	}

	@DeleteMapping(path = "/{id}")
	public ResponseEntity<Categoria> Delete(@PathVariable Integer id) {
		service.Delete(id);
		return ResponseEntity.noContent().build();
	}
}
