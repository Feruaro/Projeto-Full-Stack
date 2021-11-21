package br.com.fernanda.projetofullstack.services.exceptions;

public class DataIntegrityException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public DataIntegrityException(Integer id, String obj) {
		super("Não é possível excluir a categoria:" + obj + "! Id: " + id +
			"\nMotivo: Categoria possui produtos associados");
	}
	
	public DataIntegrityException(String message) {
		super(message);
	}

	public DataIntegrityException(String message, Throwable cause) {
		super(message, cause);
	}

	
	
	
}
