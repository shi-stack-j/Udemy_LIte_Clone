package UdemyLite.Udemy.Services;

import UdemyLite.Udemy.Entities.UserEntity;
import UdemyLite.Udemy.Entities.UserPrincipal;
import UdemyLite.Udemy.Respository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    UserRepo userRepo;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<UserEntity> user=userRepo.findByUsername(username);
        if(user.isPresent()){
            return new UserPrincipal(user.get());
        }else{
            throw  new UsernameNotFoundException("User not found");
        }

    }
}
