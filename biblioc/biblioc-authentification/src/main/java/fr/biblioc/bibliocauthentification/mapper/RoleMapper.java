package fr.biblioc.bibliocauthentification.mapper;

import fr.biblioc.bibliocauthentification.dto.RoleDto;
import fr.biblioc.bibliocauthentification.model.Role;
import org.mapstruct.Mapper;

/**
 * Interface de mapping entre entity et Dto
 */
@Mapper(componentModel = "spring")
public interface RoleMapper {
    RoleDto roleToRoleDto(Role role);
    Role roleDtoToRole(RoleDto roleDto);
}