package br.com.fernanda.projetofullstack.dto;

import java.io.Serializable;

import br.com.fernanda.projetofullstack.domains.Produto;

public class ProdutoDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer id;
	private String nome;
	private Double valor;

	public ProdutoDTO() {
	}

	public ProdutoDTO(Produto obj) {
		this.id = obj.getId();
		this.nome = obj.getNome();
		this.valor = obj.getValor();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Double getValor() {
		return valor;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}

}
