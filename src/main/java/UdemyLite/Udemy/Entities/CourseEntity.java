package UdemyLite.Udemy.Entities;
import jakarta.persistence.*;
import lombok.*;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.HashSet;
import  java.util.Set;
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CourseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false,unique = true)
    private String course_name;

    @Column(nullable = false)
    private String course_description;

    @Column(nullable = false)
    private int duration;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity createdBy;

    private Timestamp createdAt=Timestamp.from(Instant.now());

    @OneToMany(mappedBy = "course_id",cascade = CascadeType.ALL)
    private Set<EnrollmentEntity> enrollments=new HashSet<>();
}
