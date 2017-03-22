package org.koushik.javabrains.messenger.resources;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.koushik.javabrains.messenger.model.Profile;
import org.koushik.javabrains.messenger.service.ProfileService;

@Path("/profiles")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ProfileResource {

	private ProfileService pService = new ProfileService();
	
	@GET
	public List<Profile> getAllProfiles()
	{
		return pService.getAllProfiles();
	}
	
	@GET
	@Path("/{profileName}")
	public Profile getProfile(@PathParam("profileName") String profileName)
	{
		return pService.getProfiles(profileName);
	}
	
	@POST
	public Profile addProfile(Profile profile)
	{
		return pService.addProfile(profile);
	}
	
	@DELETE
	@Path("/{profileName}")
	public void deleteProfiles(@PathParam("profileName") String profileName)
	{
		pService.deleteProfiles(profileName);
	}
	
	@PUT
	@Path("/{profileName}")
	public Profile updateProfile(@PathParam("profileName") String profileName, Profile profile)
	{
		profile.setProfileName(profileName);
		return pService.updateProfile(profile);
	}
	
}
