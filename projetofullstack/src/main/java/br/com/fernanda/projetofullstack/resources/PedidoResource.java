package br.com.fernanda.projetofullstack.resources;

import java.net.URI;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.fernanda.projetofullstack.domains.Pedido;
import br.com.fernanda.projetofullstack.services.PedidoService;

@RestController
@RequestMapping(path = "/pedidos")
public class PedidoResource {

	@Autowired
	private PedidoService service;

	@GetMapping(path = "/{id}")
	public ResponseEntity<Pedido> buscar(@PathVariable Integer id) {
		Pedido pedido = service.buscar(id);
		return ResponseEntity.ok().body(pedido);
	}
	
	@PostMapping
	public ResponseEntity<Void> Insert(@Valid @RequestBody Pedido pedido) {
		pedido = service.insert(pedido);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(pedido.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
}
