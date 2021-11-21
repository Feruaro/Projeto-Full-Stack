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

import br.com.fernanda.projetofullstack.domains.Cliente;
import br.com.fernanda.projetofullstack.dto.ClienteDTO;
import br.com.fernanda.projetofullstack.dto.ClienteNewDTO;
import br.com.fernanda.projetofullstack.services.ClienteService;

@RestController
@RequestMapping(path = "/clientes")
public class ClienteResource {

	@Autowired
	private ClienteService service;

	@GetMapping(path = "/{id}")
	public ResponseEntity<Cliente> Buscar(@PathVariable Integer id) {
		Cliente cliente = service.Find(id);
		return ResponseEntity.ok().body(cliente);
	}

	@GetMapping(path = "/page")
	public ResponseEntity<Page<ClienteDTO>> FindPage(@RequestParam(value = "page", defaultValue = "0") Integer page,
			@RequestParam(value = "linesPerPages", defaultValue = "24") Integer linesPerPages,
			@RequestParam(value = "orderBy", defaultValue = "nome") String orderBy,
			@RequestParam(value = "direction", defaultValue = "ASC") String direction) {
		Page<Cliente> lista = service.findPage(page, linesPerPages, orderBy, direction);
		Page<ClienteDTO> listaDTO = lista.map(obj -> new ClienteDTO(obj));
		return ResponseEntity.ok().body(listaDTO);
	}

	@GetMapping
	public ResponseEntity<List<ClienteDTO>> FindAll() {
		List<Cliente> lista = service.FindAll();
		List<ClienteDTO> listaDTO = lista.stream().map(obj -> new ClienteDTO(obj)).collect(Collectors.toList());
		return ResponseEntity.ok().body(listaDTO);
	}
	
	@PostMapping
	public ResponseEntity<Void> Insert(@Valid @RequestBody ClienteNewDTO cliente) {
		Cliente cli = service.FromDTO(cliente);
		cli = service.Insert(cli);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(cli.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}

	@PutMapping(path = "/{id}")
	public ResponseEntity<Void> Update(@PathVariable Integer id, @Valid @RequestBody ClienteDTO cliente) {
		Cliente clie = service.FromDTO(cliente);
		clie.setId(id);
		clie = service.Update(clie);
		return ResponseEntity.noContent().build();
	}
	
	@DeleteMapping(path = "/{id}")
	public ResponseEntity<Cliente> Delete(@PathVariable Integer id) {
		service.Delete(id);
		return ResponseEntity.noContent().build();
	}

}
