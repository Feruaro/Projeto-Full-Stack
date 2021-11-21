package br.com.fernanda.projetofullstack.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.fernanda.projetofullstack.domains.Pedido;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Integer> {

}
