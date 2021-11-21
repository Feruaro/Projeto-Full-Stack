package br.com.fernanda.projetofullstack.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.fernanda.projetofullstack.domains.Pagamento;

//só criar da super classe, pois já abrange as sub classes tb
@Repository
public interface PagamentoRepository extends JpaRepository<Pagamento, Integer> {

}
