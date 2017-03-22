package org.koushik.javabrains.messenger.exception;

public class DataNotFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6328286661536343936L;

	public DataNotFoundException(String message) {
		super(message);
	}

	// Now we have to map this exception to the response & that is done using the class ExceptionMapper.
	// Create this Mapper class. 
	
}
