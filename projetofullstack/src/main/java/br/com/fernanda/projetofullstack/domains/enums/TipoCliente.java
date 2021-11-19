package br.com.fernanda.projetofullstack.domains.enums;

public enum TipoCliente {
//pode salvar a referência como String ou Integer automaticamente (pode gerar problemas futuros - não é o mais indicado a se fazer), 
//OU você pode fazer manualmente
	
	PESSOAFISICA(1, "Pessoa Física"),
	PESSOAJURIDICA(2, "Pessoa Jurídica");
	
	private int cod;
	private String descricao;
	
	//construtor de ENUM é PRIVATE
	private TipoCliente(int cod, String descricao) {
		this.cod = cod;
		this.descricao = descricao;
	}

	public int getCod() {
		return cod;
	}

	public String getDescricao() {
		return descricao;
	}
	
	//método ser utilizado mesmo sem a instanciação de objetos
	public static TipoCliente toEnum(Integer cod) {
		if(cod == null) return null;
		
		for(TipoCliente i : TipoCliente.values()) {
			if(cod.equals(i.getCod())) return i; 
		}
		
		//se não cair no if ou for:
		throw new IllegalArgumentException("Código inválido!");
	}
	
	
}
