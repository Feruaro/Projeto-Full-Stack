package br.com.fernanda.projetofullstack.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import br.com.fernanda.projetofullstack.domains.Categoria;
import br.com.fernanda.projetofullstack.repositories.CategoriaRepository;
import br.com.fernanda.projetofullstack.services.exceptions.DataIntegrityException;
import br.com.fernanda.projetofullstack.services.exceptions.ObjectNotFoundException;

@Service
public class CategoriaService {

	@Autowired
	private CategoriaRepository repo;

	public Categoria Find(Integer id) {
		Optional<Categoria> categoria = repo.findById(id);
		return categoria.orElseThrow(() -> new ObjectNotFoundException(id, "Categoria"));
	}

	public List<Categoria> FindAll() {
		return repo.findAll();
	}

	public Categoria Insert(Categoria categoria) {
		categoria.setId(null);
		return repo.save(categoria);
	}

	public Categoria Update(Categoria categoria) {
		Find(categoria.getId()); // caso não tiver ele retorna o tratamento de excessão
		return repo.save(categoria);
	}

	public void Delete(Integer id) {
		Categoria cat = Find(id); // caso não tiver ele retorna o tratamento de excessão

		try {
			repo.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException(id, cat.getNome());
		}
	}
}
