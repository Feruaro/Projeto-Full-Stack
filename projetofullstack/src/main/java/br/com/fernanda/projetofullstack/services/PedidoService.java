package br.com.fernanda.projetofullstack.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.fernanda.projetofullstack.domains.Pedido;
import br.com.fernanda.projetofullstack.repositories.PedidoRepository;
import br.com.fernanda.projetofullstack.services.exceptions.ObjectNotFoundException;

@Service
public class PedidoService {
	
	@Autowired
	private PedidoRepository repo;
	
	public Pedido Buscar(Integer id) {
		Optional<Pedido> pedido =  repo.findById(id);
		return pedido.orElseThrow(() -> new ObjectNotFoundException(id, "Pedido"));
	}
	
}
