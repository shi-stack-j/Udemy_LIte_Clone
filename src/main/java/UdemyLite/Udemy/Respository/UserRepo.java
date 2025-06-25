package UdemyLite.Udemy.Respository;

import UdemyLite.Udemy.Entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface UserRepo extends JpaRepository<UserEntity,Long> {
    @Query(value="SELECT * FROM user_entity WHERE email=:email",nativeQuery = true)
    public Optional<UserEntity> findByEmail(@Param("email") String email);
    @Query(value = "select * from user_entity where username=:username",nativeQuery = true)
    public Optional<UserEntity> findByUsername(@Param("username")String username);
}
