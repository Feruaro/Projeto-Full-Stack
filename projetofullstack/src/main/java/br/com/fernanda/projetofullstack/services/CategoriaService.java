package br.com.fernanda.projetofullstack.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.fernanda.projetofullstack.domains.Categoria;
import br.com.fernanda.projetofullstack.repositories.CategoriaRepository;
import br.com.fernanda.projetofullstack.services.exceptions.ObjectNotFoundException;

@Service
public class CategoriaService {
	
	@Autowired
	private CategoriaRepository repo;
	
	public Categoria Buscar(Integer id) {
		Optional<Categoria> categoria =  repo.findById(id);
		return categoria.orElseThrow(() -> new ObjectNotFoundException(id, "Categoria"));
	}
	
}
