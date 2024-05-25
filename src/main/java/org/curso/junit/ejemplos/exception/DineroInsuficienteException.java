package org.curso.junit.ejemplos.exception;

public class DineroInsuficienteException extends RuntimeException{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 205779381955225062L;

	public DineroInsuficienteException(String message) {
		super(message);
	}

}
