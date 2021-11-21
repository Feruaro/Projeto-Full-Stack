package br.com.fernanda.projetofullstack.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import br.com.fernanda.projetofullstack.domains.Categoria;
import br.com.fernanda.projetofullstack.dto.CategoriaDTO;
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
		Categoria cat = Find(categoria.getId()); 
		updateData(cat, categoria);
		
		return repo.save(cat);
	}

	private void updateData(Categoria cat, Categoria categoria) {
		cat.setNome(categoria.getNome());
	}

	public void Delete(Integer id) {
		Categoria cat = Find(id); // caso não tiver ele retorna o tratamento de excessão

		try {
			repo.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException(id, "Categoria", cat.getNome());
		}
	}
	
	public Page<Categoria> findPage(Integer page, Integer linesPerPages, String orderBy, String direction) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPages, Direction.valueOf(direction), orderBy);
		return repo.findAll(pageRequest);
	}
	
	public Categoria FromDTO(CategoriaDTO catDTO) {
		return new Categoria(catDTO.getId(), catDTO.getNome());
	}
}
