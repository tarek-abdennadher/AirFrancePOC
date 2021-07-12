package fr.airfrance.poc.mapper;

import fr.airfrance.poc.dto.UserDto;
import fr.airfrance.poc.entity.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {})
public interface UserMapper extends EntityMapper<UserDto, User> {
}
