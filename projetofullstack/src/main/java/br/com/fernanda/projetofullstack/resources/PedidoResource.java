package br.com.fernanda.projetofullstack.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.fernanda.projetofullstack.domains.Pedido;
import br.com.fernanda.projetofullstack.services.PedidoService;

@RestController
@RequestMapping(path = "/pedidos")
public class PedidoResource {

	@Autowired
	private PedidoService service;

	@GetMapping(path = "/{id}")
	public ResponseEntity<Pedido> Buscar(@PathVariable Integer id) {
		Pedido pedido = service.Buscar(id);
		return ResponseEntity.ok().body(pedido);
	}
}
