package UdemyLite.Udemy.Controllers;
import UdemyLite.Udemy.Entities.RoleEntity;
import UdemyLite.Udemy.Services.RoleService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
@RestController
@RequestMapping("/Role")
public class RoleController{
    @Autowired
    RoleService roleService;
    @PostMapping("/Create_Admin_Role")
    public ResponseEntity<?> CreateAdmin(@Valid @RequestBody RoleEntity role){
        return roleService.CreateRoleAdmin(role);
    }
    @PostMapping("/Create_Student_Role")
    public ResponseEntity<?> CreateStudentRole(@Valid@RequestBody RoleEntity role){
        return roleService.CreateRoleStudent(role);
    }
    @PostMapping("/Create_Tutor_Role")
    public ResponseEntity<?> CreateTutorRole(@Valid @RequestBody RoleEntity role){
        return roleService.CreateRoleInstructor(role);
    }
    @GetMapping("/Get_Role/{role_id}")
    public ResponseEntity<?> GetRole(@PathVariable Long role_id){
        return roleService.GetRoleById(role_id);
    }
}
