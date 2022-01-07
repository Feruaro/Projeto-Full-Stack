package br.com.fernanda.projetofullstack.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import br.com.fernanda.projetofullstack.domains.Categoria;
import br.com.fernanda.projetofullstack.domains.Produto;
import br.com.fernanda.projetofullstack.repositories.CategoriaRepository;
import br.com.fernanda.projetofullstack.repositories.ProdutoRepository;
import br.com.fernanda.projetofullstack.services.exceptions.ObjectNotFoundException;

@Service
public class ProdutoService {
	
	@Autowired
	private ProdutoRepository repo;
	
	@Autowired
	private CategoriaRepository repo_cat;
	
	public Produto Buscar(Integer id) {
		Optional<Produto> produto =  repo.findById(id);
		return produto.orElseThrow(() -> new ObjectNotFoundException(id, "Produto"));
	}
	
	public Page<Produto> search(String nome, List<Integer> ids, Integer page, Integer linesPerPages, String orderBy, String direction) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPages, Direction.valueOf(direction), orderBy);
		List<Categoria> categorias = repo_cat.findAllById(ids);
		return repo.findDistinctByNomeContainingAndCategoriasIn(nome, categorias, pageRequest);
	}
	
	
	
	
}
