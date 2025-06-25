package UdemyLite.Udemy.Services;

import UdemyLite.Udemy.Entities.LoginModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
public class LoginService {
    @Autowired
    JWTService jwtService;
    @Autowired
    AuthenticationManager authenticationManager;
    public ResponseEntity<?> UserLogin(LoginModel loginModel){
        UsernamePasswordAuthenticationToken authenticationToken=new UsernamePasswordAuthenticationToken(loginModel.getUsername(),loginModel.getPassword());
        try{
            Authentication authentication=authenticationManager.authenticate(authenticationToken);
            if(authentication.isAuthenticated()){
                return new ResponseEntity<>(jwtService.generateToken(loginModel.getUsername()),HttpStatus.OK);
            }
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
        return null;
    }
}
