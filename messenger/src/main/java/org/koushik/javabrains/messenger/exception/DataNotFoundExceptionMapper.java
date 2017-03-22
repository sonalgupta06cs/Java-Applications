package org.koushik.javabrains.messenger.exception;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import org.koushik.javabrains.messenger.model.ErrorMessage;

@Provider
// This annotation registers this class with JAX-RS so that JAX-RS knows that this class is there.
// & it can map this exception..
public class DataNotFoundExceptionMapper implements ExceptionMapper<DataNotFoundException> {
	// ExceptionMapper is a raw type, make it a generic type.
	// Now the generic type is gonna be the exception that you want this ExceptionMapper to map.	
    // Exception is DataNotFoundException.
	
	
	// This method takes in exception as argument & returns a response.
	// Whenever an exception is thrown in the application, we can have JAX-RS call this method.
	// It will pass the exception that was thrown to  you, & in brackets, you can write code to create your custom
	// response & return it.
	// Then JAX-RS is gonna take that resonse & return it back to user, so that exeption doesnt get bubble
	// all the way up.....This is one way to catch the exception & then prepare the response & then send it back...
	@Override
	public Response toResponse(DataNotFoundException ex) {
		
		ErrorMessage errorMessage = new ErrorMessage(ex.getMessage(), 	404	, "http://google.com");
		
		return Response.status(Status.NOT_FOUND)
				.entity(errorMessage)
				.build();
	}

}
