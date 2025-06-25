package UdemyLite.Udemy.Controllers;
import UdemyLite.Udemy.Entities.LoginModel;
import UdemyLite.Udemy.Entities.UserPrincipal;
import UdemyLite.Udemy.Services.LoginService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
@RestController
@RequestMapping("/Home")
public class HomeCon {
    @Autowired
    LoginService loginService;
    @GetMapping("/Welcome/{name}")
    public String hello(@PathVariable String name){
        return "Welcome to our our application :- "+name;
    }
    @GetMapping("/Welcome")
    public String welcome(@RequestParam String name){
        return "Welcome to our application :- "+name;
    }
    @GetMapping("/Logged_In")
    public ResponseEntity<?> userPrincipal(){
        return new ResponseEntity<>(SecurityContextHolder.getContext().getAuthentication(), HttpStatus.OK);
    }
    @GetMapping("/Login")
    public ResponseEntity<?> Login(@Valid @RequestBody LoginModel loginModel){
        return loginService.UserLogin(loginModel);
    }
}
