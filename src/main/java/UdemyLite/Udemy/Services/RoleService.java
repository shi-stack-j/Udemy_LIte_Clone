package UdemyLite.Udemy.Services;

import UdemyLite.Udemy.DTOs.RoleDto;
import UdemyLite.Udemy.Entities.PermissionEnum;
import UdemyLite.Udemy.Entities.RoleEntity;
import UdemyLite.Udemy.Entities.RolesAvailable;
import UdemyLite.Udemy.Mappers.RoleMapper;
import UdemyLite.Udemy.Respository.RoleRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class RoleService {
    @Autowired
    RoleRepo roleRepo;
    public ResponseEntity<?> CreateRoleAdmin(RoleEntity roleEntity){
        try{
            if(roleEntity.getRolesAvailable()!=RolesAvailable.ADMIN) return new ResponseEntity<>("Enter Not Allowed",HttpStatus.BAD_REQUEST);
            Set<PermissionEnum> permissionEnums=new HashSet<>();
            permissionEnums.add(PermissionEnum.CREATE_COURSE);
            permissionEnums.add(PermissionEnum.CHANGE_ROLES);
            permissionEnums.add(PermissionEnum.DELETE_COURSE);
            permissionEnums.add(PermissionEnum.DELETE_STUDENT);
            permissionEnums.add(PermissionEnum.DELETE_TUTOR);
            permissionEnums.add(PermissionEnum.DISABLE_TUTOR);
            permissionEnums.add(PermissionEnum.READ_ALL_COURSES);
            permissionEnums.add(PermissionEnum.READ_ALL_TUTORS);
            permissionEnums.add(PermissionEnum.READ_ALL_STUDENTS);
            roleEntity.setAuthoritiesEntities(permissionEnums);
            RoleEntity role=roleRepo.save(roleEntity);
            return  new ResponseEntity<>(role, HttpStatus.CREATED);
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }
    public ResponseEntity<?> CreateRoleStudent(RoleEntity roleEntity){
        try{
            if(roleEntity.getRolesAvailable()!=RolesAvailable.STUDENT) return new ResponseEntity<>("Not Allowed",HttpStatus.BAD_REQUEST);
            Set<PermissionEnum> permissionEnums=new HashSet<>();
            permissionEnums.add(PermissionEnum.READ_ALL_COURSES);
            permissionEnums.add(PermissionEnum.UNENROLL_IN_COURSE);
            permissionEnums.add(PermissionEnum.ENROLL_IN_COURSE);
            permissionEnums.add(PermissionEnum.DELETE_STUDENT);
            roleEntity.setAuthoritiesEntities(permissionEnums);
            RoleEntity role=roleRepo.save(roleEntity);
            return  new ResponseEntity<>(role, HttpStatus.CREATED);
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }
    public ResponseEntity<?> CreateRoleInstructor(RoleEntity roleEntity){
        try{
            if(roleEntity.getRolesAvailable()!=RolesAvailable.INSTRUCTOR) return new ResponseEntity<>("Not Allowed",HttpStatus.BAD_REQUEST);
            Set<PermissionEnum> permissionEnums=new HashSet<>();
            permissionEnums.add(PermissionEnum.READ_ALL_COURSES);
            permissionEnums.add(PermissionEnum.UNENROLL_IN_COURSE);
            permissionEnums.add(PermissionEnum.ENROLL_IN_COURSE);
            permissionEnums.add(PermissionEnum.CREATE_COURSE);
            permissionEnums.add(PermissionEnum.DELETE_COURSE);
            permissionEnums.add(PermissionEnum.DELETE_TUTOR);
            permissionEnums.add(PermissionEnum.SEE_ENROLLED_STUDENTS);
            roleEntity.setAuthoritiesEntities(permissionEnums);
            RoleEntity role=roleRepo.save(roleEntity);
            return  new ResponseEntity<>(role, HttpStatus.CREATED);
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }
    public ResponseEntity<?> GetRoleById(Long id){
        try{
            Optional<RoleEntity> roleEntity=roleRepo.findById(id);
            if(roleEntity.isPresent()){
                RoleDto roleDto= RoleMapper.EntityToDto(roleEntity.get());
                return new ResponseEntity<>(roleDto,HttpStatus.OK);
            }else{
                return new ResponseEntity<>("Role not found ",HttpStatus.NOT_FOUND);
            }
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_GATEWAY);
        }
    }
}
