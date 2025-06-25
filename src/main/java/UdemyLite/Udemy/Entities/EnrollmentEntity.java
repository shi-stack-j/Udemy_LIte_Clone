package UdemyLite.Udemy.Entities;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.sql.Timestamp;
import java.time.Instant;
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class EnrollmentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Timestamp enrolledOn =Timestamp.from(Instant.now());

    @ManyToOne
    @JoinColumn(name = "student_id")
    private UserEntity userEntity;

    @ManyToOne
    @JoinColumn(name = "course_id")
    private CourseEntity course_id;

    private boolean completed=false;
}
