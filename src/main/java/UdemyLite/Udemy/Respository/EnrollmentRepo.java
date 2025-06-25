package UdemyLite.Udemy.Respository;

import UdemyLite.Udemy.Entities.EnrollmentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EnrollmentRepo extends JpaRepository<EnrollmentEntity,Long> {
}
