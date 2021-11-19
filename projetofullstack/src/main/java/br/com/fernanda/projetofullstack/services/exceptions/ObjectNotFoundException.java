package br.com.fernanda.projetofullstack.services.exceptions;

public class ObjectNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public ObjectNotFoundException(Integer id, String obj) {
		super(obj + " não encontrado! Id: " + id);
	}
	
	public ObjectNotFoundException(String message) {
		super(message);
	}

	public ObjectNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}

	
	
	
}
