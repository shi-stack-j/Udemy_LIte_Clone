package UdemyLite.Udemy.Mappers;

import UdemyLite.Udemy.DTOs.RoleDto;
import UdemyLite.Udemy.Entities.RoleEntity;
import UdemyLite.Udemy.Entities.UserEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class RoleMapper {
    public static RoleDto EntityToDto(RoleEntity roleEntity){
        RoleDto roleDto=new RoleDto();
        roleDto.setId(roleEntity.getId());
        roleDto.setName(roleEntity.getRolesAvailable().toString());
        List<String> users=roleEntity.getUserEntities().stream().map(UserEntity::getUsername).collect(Collectors.toList());
        roleDto.setUsers(users);
        roleDto.setPermissionEnums(new ArrayList<>(roleEntity.getAuthoritiesEntities()));
        return roleDto;
    }
}

