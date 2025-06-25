package UdemyLite.Udemy.Entities;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.*;
import java.util.HashSet;
import java.util.Set;
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false,unique = true)
    private String username;
    @Column(nullable = false)
    private String name;
    @Email
    @Column(nullable = false,unique = true)
    private String email;
    @Column(nullable = false)
    private String password;
    @ManyToOne
    @JoinColumn(name = "role_id")
    private RoleEntity roleEntity;
    @OneToMany(mappedBy = "createdBy",cascade = CascadeType.ALL)
    private Set<CourseEntity> courseCreated=new HashSet<>();
    @OneToMany(mappedBy = "userEntity",cascade = CascadeType.ALL)
    private Set<EnrollmentEntity> enrollments=new HashSet<>();
}
