package br.com.fernanda.projetofullstack.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.fernanda.projetofullstack.domains.Cliente;
import br.com.fernanda.projetofullstack.services.ClienteService;

@RestController
@RequestMapping(path="/clientes")
public class ClienteResource {
	
	@Autowired
	private ClienteService service;
	
	@GetMapping(path="/{id}")
	public ResponseEntity<Cliente> Buscar(@PathVariable Integer id) {
		Cliente cliente = service.Buscar(id);
		return ResponseEntity.ok().body(cliente);
	}
}
