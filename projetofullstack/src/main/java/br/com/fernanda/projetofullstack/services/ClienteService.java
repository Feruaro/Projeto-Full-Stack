package br.com.fernanda.projetofullstack.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.fernanda.projetofullstack.domains.Cliente;
import br.com.fernanda.projetofullstack.repositories.ClienteRepository;
import br.com.fernanda.projetofullstack.services.exceptions.ObjectNotFoundException;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository repo;

	public Cliente Buscar(Integer id) {
		Optional<Cliente> cliente = repo.findById(id);
		return cliente.orElseThrow(() -> new ObjectNotFoundException(id, "Cliente"));
	}

}
