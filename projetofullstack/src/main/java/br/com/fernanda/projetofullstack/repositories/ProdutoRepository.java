package br.com.fernanda.projetofullstack.repositories;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.fernanda.projetofullstack.domains.Categoria;
import br.com.fernanda.projetofullstack.domains.Produto;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Integer> {
	
//	@Query("SELECT DISTINCT obj FROM Produto obj INNER JOIN obj.categorias cat WHERE obj.nome LIKE %:nome% AND cat IN :categorias")
//	public Page<Produto> search(@Param("nome") String nome, @Param("categorias") List<Categoria> categorias, Pageable pageRequest);
	
	@Transactional(readOnly=true)
	public Page<Produto> findDistinctByNomeContainingAndCategoriasIn(String nome, List<Categoria> categorias, Pageable pageRequest);
}
