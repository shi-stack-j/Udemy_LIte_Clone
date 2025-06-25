package UdemyLite.Udemy.Respository;

import UdemyLite.Udemy.Entities.RoleEntity;
import UdemyLite.Udemy.Entities.RolesAvailable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepo extends JpaRepository<RoleEntity,Long>{
    @Query(value="SELECT * FROM role_entity WHERE roles_available=:role",nativeQuery = true)
    public Optional<RoleEntity> findByRole(@Param("role")String  role);
}
