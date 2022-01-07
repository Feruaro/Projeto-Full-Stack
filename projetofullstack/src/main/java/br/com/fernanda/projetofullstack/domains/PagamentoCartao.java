package br.com.fernanda.projetofullstack.domains;

import javax.persistence.Entity;

import com.fasterxml.jackson.annotation.JsonTypeName;

import br.com.fernanda.projetofullstack.domains.enums.EstadoPagamento;

@Entity
@JsonTypeName("pagamentoCartao")
public class PagamentoCartao extends Pagamento {

	private static final long serialVersionUID = 1L;
	
	private Integer numParcelas;
	
	public PagamentoCartao() {
	}

	public PagamentoCartao(Integer id, EstadoPagamento tipo, Pedido pedido, Integer numParcelas) {
		super(id, tipo, pedido);
		this.numParcelas = numParcelas;
	}

	public Integer getNumParcelas() {
		return numParcelas;
	}

	public void setNumParcelas(Integer numParcelas) {
		this.numParcelas = numParcelas;
	}

	
	
	
}
