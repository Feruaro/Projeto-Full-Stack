package br.com.fernanda.projetofullstack.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.fernanda.projetofullstack.domains.Produto;
import br.com.fernanda.projetofullstack.dto.ProdutoDTO;
import br.com.fernanda.projetofullstack.resources.utils.URL;
import br.com.fernanda.projetofullstack.services.ProdutoService;

@RestController
@RequestMapping(path = "/produtos")
public class ProdutoResource {

	@Autowired
	private ProdutoService service;

	@GetMapping(path = "/{id}")
	public ResponseEntity<Produto> Buscar(@PathVariable Integer id) {
		Produto produto = service.Buscar(id);
		return ResponseEntity.ok().body(produto);
	}
	
	@GetMapping
	public ResponseEntity<Page<ProdutoDTO>> findPage(
			@RequestParam(value = "nome", defaultValue = "") String nome,
			@RequestParam(value = "categorias", defaultValue = "") String categorias,
			@RequestParam(value = "page", defaultValue = "0") Integer page,
			@RequestParam(value = "linesPerPages", defaultValue = "24") Integer linesPerPages,
			@RequestParam(value = "orderBy", defaultValue = "nome") String orderBy,
			@RequestParam(value = "direction", defaultValue = "ASC") String direction) {
		
		String nomeDecoder = URL.decodeParam(nome);
		List<Integer> ids = URL.decodeIntLis(categorias); 		
		Page<Produto> lista = service.search(nomeDecoder, ids, page, linesPerPages, orderBy, direction);
		Page<ProdutoDTO> listaDTO = lista.map(obj -> new ProdutoDTO(obj));
		return ResponseEntity.ok().body(listaDTO);
	}
}
