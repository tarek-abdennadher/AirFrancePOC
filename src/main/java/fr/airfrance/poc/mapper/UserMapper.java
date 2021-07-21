package fr.airfrance.poc.mapper;

import fr.airfrance.poc.dto.UserDto;
import fr.airfrance.poc.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {})
public interface UserMapper extends EntityMapper<UserDto, User> {

    @Mapping(source = "userPk.userName", target = "userName")
    @Mapping(source = "userPk.birthdate", target = "birthdate")
    @Mapping(source = "userPk.country", target = "country")
    UserDto toDto(User user);

    @Mapping(source = "userName", target = "userPk.userName")
    @Mapping(source = "birthdate", target = "userPk.birthdate")
    @Mapping(source = "country", target = "userPk.country")
    User toEntity(UserDto userDto);
}
