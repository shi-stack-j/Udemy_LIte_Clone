package UdemyLite.Udemy.Controllers;
import UdemyLite.Udemy.Entities.UserEntity;
import UdemyLite.Udemy.Services.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
@RestController
@RequestMapping("/User")
public class UserController {
    @Autowired
    UserService userService;
    @PostMapping("/Create_Student")
    public ResponseEntity<?> CreateStudent(@Valid @RequestBody UserEntity user){
        return userService.CreateStudent(user);
    }
    @PostMapping("/Create_Admin")
    public ResponseEntity<?> CreateAdmin(@Valid @RequestBody UserEntity user){
        return userService.CreateAdmin(user);
    }
    @PostMapping("/Create_Instructor")
    public ResponseEntity<?> CreateInstructor(@Valid @RequestBody UserEntity user){
        return userService.CreateInstructor(user);
    }
    @GetMapping("/Get_By_id/{user_id}")
    public ResponseEntity<?> GetUserByID(@PathVariable Long user_id){
        return userService.GetUserDetails(user_id);
    }
    @GetMapping("/Get_By_email/{email}")
    public ResponseEntity<?> GetUserByEmail(@PathVariable String email){
        return userService.GetUserByEmail(email);
    }
    @DeleteMapping("/Delete_By_Id/{user_id}")
    public ResponseEntity<?> DeleteUserByID(@PathVariable Long user_id){
        return userService.DeleteByID(user_id);
    }
    @DeleteMapping("/Delete_By_Email/{email}")
    public ResponseEntity<?> DeleteByEmail(@PathVariable String email){
        return userService.DeleteByEmail(email);
    }
    @PutMapping("/Update_Role/{user_id}/{new_role}")
    public ResponseEntity<?> UpdateUserRole(@PathVariable Long user_id,@PathVariable String new_role){
        return userService.UpdateRoleOfUser(user_id,new_role);
    }
}
