package UdemyLite.Udemy.Services;
import UdemyLite.Udemy.DTOs.UserDto;
import UdemyLite.Udemy.Entities.RoleEntity;
import UdemyLite.Udemy.Entities.UserEntity;
import UdemyLite.Udemy.Mappers.UserMapper;
import UdemyLite.Udemy.Respository.RoleRepo;
import UdemyLite.Udemy.Respository.UserRepo;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import java.util.Optional;
@Service
public class UserService {
    @Autowired
    UserRepo userRepo;
    @Autowired
    RoleRepo roleRepo;
    @Transactional
    public ResponseEntity<?> CreateStudent(UserEntity user){
        try{
            Optional<RoleEntity> roleEntity=roleRepo.findByRole("STUDENT");
            user.setRoleEntity(roleEntity.get());
            UserEntity userEntity=userRepo.save(user);
            UserDto userDto=UserMapper.EntityToDto(userEntity);
            return new ResponseEntity<>(userDto, HttpStatus.CREATED);
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_GATEWAY);
        }
    }
    @Transactional
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> CreateAdmin(UserEntity user){
        try{
            Optional<RoleEntity> roleEntity=roleRepo.findByRole("ADMIN");
            user.setRoleEntity(roleEntity.get());
            UserEntity userEntity=userRepo.save(user);
            UserDto userDto=UserMapper.EntityToDto(userEntity);
            return new ResponseEntity<>(userDto, HttpStatus.CREATED);
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }
    @Transactional
    public ResponseEntity<?> CreateInstructor(UserEntity user){
        try{
            Optional<RoleEntity> roleEntity=roleRepo.findByRole("INSTRUCTOR");
            user.setRoleEntity(roleEntity.get());
            UserEntity userEntity=userRepo.save(user);
            UserDto userDto=UserMapper.EntityToDto(userEntity);
            return new ResponseEntity<>(userDto, HttpStatus.CREATED);
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }
    @PreAuthorize("hasRole('ADMIN') or @checks.isEligibleToSeeDetailsById(#id)")
    public ResponseEntity<?> GetUserDetails(Long id){
        try{
            Optional<UserEntity> userEntity=userRepo.findById(id);
            if(userEntity.isPresent()){
                UserDto userDto= UserMapper.EntityToDto(userEntity.get());
                return new ResponseEntity<>(userDto,HttpStatus.OK);
            }else{
                return new ResponseEntity<>("User Not present",HttpStatus.NOT_FOUND);
            }
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }

    //To get user by email
    @PreAuthorize("hasRole('ADMIN' or @checks.isEligibleToSeeDetailsByEmail(#email)")
    public ResponseEntity<?> GetUserByEmail(String email){
        try{
            Optional<UserEntity> userEntity=userRepo.findByEmail(email);
            if(userEntity.isPresent()){
                UserDto userDto=UserMapper.EntityToDto(userEntity.get());
                return new ResponseEntity<>(userDto,HttpStatus.OK);
            }else{
                return new ResponseEntity<>("User not found :- ",HttpStatus.NOT_FOUND);
            }
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }

    //To delete User By id or email
    @Transactional
    @PreAuthorize("hasRole('ADMIN') or @checks.isEligibleToSeeDetailsById(#id)")
    public ResponseEntity<?> DeleteByID(Long id){
        try{
            Optional<UserEntity> userEntity=userRepo.findById(id);
            if(userEntity.isPresent()){
                userRepo.delete(userEntity.get());
                return new ResponseEntity<>(userEntity.get().getUsername()+" : IS Deleted ",HttpStatus.OK);
            }else{
                return new ResponseEntity<>("User not found :- ",HttpStatus.NOT_FOUND);
            }
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }
    //To delete By Email
    @Transactional
    @PreAuthorize("hasRole('ADMIN') or @checks.isEligibleToSeeDetailsByEmail(#email)")
    public ResponseEntity<?> DeleteByEmail(String email){
        try{
            Optional<UserEntity> userEntity=userRepo.findByEmail(email);
            if(userEntity.isPresent()){
                String user=userEntity.get().getUsername();
                String role=userEntity.get().getRoleEntity().getRolesAvailable().toString();
                userRepo.delete(userEntity.get());
                return new ResponseEntity<>("User Deleted Successfully Name :- "+user+" Role is :- "+role,HttpStatus.OK);
            }else{
                return new ResponseEntity<>("Uesr Not found :- ",HttpStatus.NOT_FOUND);
            }
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }
    //To update Role
    @Transactional
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> UpdateRoleOfUser(Long id,String new_role){
        try{
            if(new_role.equals("INSTRUCTOR") || new_role.equals("STUDENT")){
                Optional<UserEntity> userEntity=userRepo.findById(id);
                if(userEntity.isPresent()){
                    String role=userEntity.get().getRoleEntity().getRolesAvailable().toString();
                    if(role==new_role){
                        return new ResponseEntity<>("The Given Role is similar to old role ",HttpStatus.NOT_ACCEPTABLE);
                    }else{
                        Optional<RoleEntity> roleEntity=roleRepo.findByRole(new_role);
                        if(roleEntity.isPresent()){
                            userEntity.get().setRoleEntity(roleEntity.get());
                            return new ResponseEntity<>("Role Updated from :- "+role+" To :- "+new_role,HttpStatus.OK);
                        }else{
                            return new ResponseEntity<>("The given role is not available ",HttpStatus.NOT_FOUND);
                        }
                    }
                }else{
                    return new ResponseEntity<>("User not present ",HttpStatus.NOT_FOUND);
                }
            }else{
                return new ResponseEntity<>("Enter valid Role !!!!",HttpStatus.NOT_ACCEPTABLE);
            }
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_GATEWAY);
        }
    }
}
