package UdemyLite.Udemy.Respository;

import UdemyLite.Udemy.Entities.CourseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseRepo extends JpaRepository<CourseEntity,Long> {
}
