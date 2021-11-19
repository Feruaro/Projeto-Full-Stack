package br.com.fernanda.projetofullstack.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.fernanda.projetofullstack.domains.Cidade;

@Repository
public interface CidadeRepository extends JpaRepository<Cidade, Integer> {

}
