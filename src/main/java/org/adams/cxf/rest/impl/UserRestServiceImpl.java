package org.adams.cxf.rest.impl;

import org.adams.cxf.rest.api.*;
import org.adams.cxf.rest.dto.UserDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import org.apache.cxf.jaxrs.model.wadl.*;

@Path(UserRestService.PATH_PREFIX)
@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
public class UserRestServiceImpl implements UserRestService {

	private static final Logger LOGGER = LoggerFactory.getLogger(UserRestServiceImpl.class);

	private static final List<UserDTO> users = new ArrayList<UserDTO>();

	static {
		UserDTO user1 = UserDTO.builder().id(1L).email("tadams1@enargit.org").enabled(true).credentialsExpired(false)
				.expired(false).locked(false).mobile("1234567890").password("password1").phone("001-123456789")
				.username("tadams1").version(1L).createdBy("tadams").created(new Date()).build();
		UserDTO user2 = UserDTO.builder().id(2L).email("tadams2@enargit.org").enabled(true).credentialsExpired(false)
				.expired(false).locked(false).mobile("1234567899").password("password2").phone("001-123456888")
				.username("tadams2").version(1L).createdBy("tadams").created(new Date()).build();
		users.add(user1);
		users.add(user2);
	}

	@Override
	public Long convert(String id) {
		LOGGER.info("Converting id to string: {0}", id);
		return Long.parseLong(id);
	}

	@GET
	@Path("")
	@Descriptions({ @Description(value = "Gets all users as a list", target = DocTarget.METHOD) })
	@Override
	public List<UserDTO> getAll() {
		return users;
	}

	@GET
	@Path("/{id}")
	@Descriptions({ 
		@Description(value = "Gets a single user by its ID", target = DocTarget.METHOD), 
		@Description(value = "Return a json object based on the UserDTO class", target = DocTarget.RETURN),
		@Description(value = "Requires a long/integer id path parameter", target = DocTarget.PARAM)
		})
	@Override
	public UserDTO getById(@PathParam("id") String id) {
		return users.stream().filter(u -> u.getId().equals(convert(id))).collect(Collectors.toList()).get(0);
	}

	@POST
	@Consumes({ MediaType.APPLICATION_JSON ,  MediaType.APPLICATION_XML })
	@Path("/{id}")
	@Descriptions({ 
		@Description(value = "Updates the user given by its ID, with the values in the user dto parameter", target = DocTarget.METHOD), 
		@Description(value = "Return a json object based on the UserDTO class", target = DocTarget.RETURN),
		@Description(value = "Requires a long/integer id path parameter", target = DocTarget.PARAM),
		@Description(value = "Requires a UserDTO based json object", target = DocTarget.PARAM),
		})
	@Override
	public UserDTO update(@PathParam("id") String id, UserDTO dto) {
		UserDTO user1 = UserDTO.builder().id(convert(id)).email(dto.getEmail()).enabled(dto.isEnabled())
				.credentialsExpired(dto.isCredentialsExpired()).expired(dto.isExpired()).locked(dto.isLocked())
				.mobile(dto.getMobile()).password(dto.getPassword()).phone(dto.getPhone()).username(dto.getUsername())
				.version(dto.getVersion()).createdBy(dto.getCreatedBy()).created(dto.getCreated()).build();
		return user1;
	}

	@POST
	@Consumes({ MediaType.APPLICATION_JSON ,  MediaType.APPLICATION_XML })
	@Path("")
	@Descriptions({ 
		@Description(value = "Create a new user given by  the values in the user dto parameter", target = DocTarget.METHOD), 
		@Description(value = "Return a json object based on the UserDTO class", target = DocTarget.RETURN),
		@Description(value = "Requires a UserDTO based json object, the id value of the user parameter must not be set", target = DocTarget.PARAM)
		})
	@Override
	public UserDTO create(UserDTO dto) {
		return null;
	}

	@DELETE
	@Path("/{id}")
	@Descriptions({ 
		@Description(value = "Delete a user given by its id parameter", target = DocTarget.METHOD), 
		@Description(value = "Return the deleted user as json object based on the UserDTO class", target = DocTarget.RETURN),
		@Description(value = "Requires a long/integer id path parameter", target = DocTarget.PARAM)
		})
	@Override
	public UserDTO deleteById(@PathParam("id") String id) {
		return users.get(0);
	}

}
