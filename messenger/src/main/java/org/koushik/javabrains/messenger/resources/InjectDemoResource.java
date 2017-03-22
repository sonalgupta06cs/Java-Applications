package org.koushik.javabrains.messenger.resources;

import javax.ws.rs.Consumes;
import javax.ws.rs.CookieParam;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.MatrixParam;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;

@Path("/injectDemo")
@Consumes(MediaType.TEXT_PLAIN)
@Produces(MediaType.TEXT_PLAIN)
public class InjectDemoResource {
	
@GET	
@Path("/annotations")
public String getPathParamsUsingAnnotations(@MatrixParam("param") String matrix,
		                                    @HeaderParam("authSessionId") String header,
		                                    @CookieParam("name") String cookie // @FormParam
		                                   )
{
	return "Matrix Param: "+matrix + " Header Param: "+header +" Cookie Param: "+cookie;
}

@GET
@Path("/context")
public String getParamsUsingContext(@Context UriInfo uriInfo, @Context HttpHeaders httpHeaders)
{
	String path = uriInfo.getAbsolutePath().toString();
	String cookies = httpHeaders.getCookies().toString();
	return "Path: "+path+" Cookies: "+cookies;
}
	
}
