package br.com.fernanda.projetofullstack.domains.enums;

public enum EstadoPagamento {
	
	PENDENTE(1, "Pendente"),
	QUITADO(2, "Quitado"),
	CANCELADO(3, "Cancelado");
	
	private int cod;
	private String descricao;
	
	private EstadoPagamento(int cod, String descricao) {
		this.cod = cod;
		this.descricao = descricao;
	}

	public int getCod() {
		return cod;
	}

	public String getDescricao() {
		return descricao;
	}
	
	public static EstadoPagamento toEnum(Integer cod) {
		if(cod == null) return null;
		
		for(EstadoPagamento i : EstadoPagamento.values()) {
			if(cod.equals(i.getCod())) return i;
		}
		
		throw new IllegalArgumentException("Estado de pagamento inválido!");
	}
	
}
