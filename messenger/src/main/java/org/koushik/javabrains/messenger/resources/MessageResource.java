package org.koushik.javabrains.messenger.resources;

import java.net.URI;
import java.util.List;

import javax.ws.rs.BeanParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.core.UriInfo;

import org.koushik.javabrains.messenger.model.Message;
import org.koushik.javabrains.messenger.resources.beans.MessageFilterBean;
import org.koushik.javabrains.messenger.service.MessageService;

@Path("/messages")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(value = {MediaType.APPLICATION_JSON, MediaType.TEXT_XML})
public class MessageResource {
	
	MessageService mService = new MessageService();
	
	/*@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Message> getMessages(@QueryParam("year") int year,
			                         @QueryParam("start") int start,
			                         @QueryParam("size") int size
			                         ){
		if(year > 0)
		{
			return mService.getAllMessagesForYear(year);
		}	
		
		if(start>=0 && size>0)
		{
			return mService.getAllMessagesPaginated(start, size);
		}	
		
		return mService.getAllMessages();
	}*/
	
	// or
	
	// If  you dont want to use all the Annotations like QueryParam n all or the list is getting unweildy, so go for this
	// @BeanParam, Create a Bean like MesssageFilterBean & create the variables with @QueryParam &
	// remove from the resource, the @QueryParam... 
	@GET
	public List<Message> getMessages(@BeanParam MessageFilterBean filterBean){
		if(filterBean.getYear() > 0)
		{
			return mService.getAllMessagesForYear(filterBean.getYear());
		}	
		
		if(filterBean.getStart()>=0 && filterBean.getSize()>0)
		{
			return mService.getAllMessagesPaginated(filterBean.getStart(), filterBean.getSize());
		}	
		
		return mService.getAllMessages();
	}
	
	@GET
	@Path("/{messageId}")
	public Message getMessage(@PathParam("messageId") long messageId, @Context UriInfo uriInfo)
	{
		 Message message = mService.getMessage(messageId);
		 		 
		 message.addLinks(getUriForSelf(uriInfo, message),"self");
		 message.addLinks(getUriForProfile(uriInfo, message), "profile");
		 message.addLinks(getUriForComments(uriInfo, message), "comment");
		 
		 
		 
		 return message;
	}

	private String getUriForSelf(UriInfo uriInfo, Message message) {
		String uri = uriInfo.getBaseUriBuilder()               // http://localhost:8080/messenger/webapi
		        .path(MessageResource.class)                    // based on @Path annotation returns --> /messages/
		        .path(Long.toString(message.getId()))           // messageId
		        .build()
                .toString();
		return uri;
	}
	
	private String getUriForProfile(UriInfo uriInfo, Message message) {
		String uri = uriInfo.getBaseUriBuilder()               // http://localhost:8080/messenger/webapi
		        .path(ProfileResource.class)                    // based on @Path annotation returns --> /profiles/
		        .path(message.getAuthor())                                // /{author}
		        .build()
                .toString();
		return uri;
	}
	
	private String getUriForComments(UriInfo uriInfo, Message message) {
		String uri = uriInfo.getBaseUriBuilder()               // http://localhost:8080/messenger/webapi
		        .path(MessageResource.class)                    // based on @Path annotation returns --> /messages/
		        .path(MessageResource.class, "getCommentResource")     // get hold of the uri that is mapped to method
		        .path(CommentResource.class)                           // i.e. /{messageId}/comments/
		        .resolveTemplate("messageId", message.getId())
		        .build()                                               
                .toString();
		return uri;
	}
	
	@POST
	public Response addMessage(Message message, @Context UriInfo uriInfo)
	{
		Message newMessage = mService.addMessage(message);
		String newId = String.valueOf(newMessage.getId());
		URI uri = uriInfo.getAbsolutePathBuilder().path(newId).build();
		return  Response.created(uri)
				.entity(newMessage)
				.build();
		//return mService.addMessage(message);
	}
	
	@DELETE
	@Path("/{messageId}")
	public Message deleteMessage(@PathParam("messageId") Long id)
	{
		return mService.deleteMessage(id);
	}
	
	@PUT
	@Path("/{messageId}")
	public Message updateMessage(@PathParam("messageId") Long id, Message message)
	{
		message.setId(id);
		return mService.updateMessage(message);
	}
	
	
	@Path("/{messageId}/comments")
	public CommentResource getCommentResource()
	{
		return new CommentResource();
	}
	
	

}
