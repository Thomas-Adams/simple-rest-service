package org.adams.cxf.rest.api;



import java.util.List;

import org.adams.cxf.rest.dto.UserDTO;

public interface UserRestService {

    public static String PATH_PREFIX = "/user";

    Long convert(String id);

    List<UserDTO> getAll();

    UserDTO getById(String id);

    UserDTO update(String id, UserDTO entity);

    UserDTO create(UserDTO entity);

    UserDTO deleteById(String id);
}
