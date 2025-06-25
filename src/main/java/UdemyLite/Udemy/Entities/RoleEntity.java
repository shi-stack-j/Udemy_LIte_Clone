package UdemyLite.Udemy.Entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class RoleEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Enumerated(EnumType.STRING)
    @Column(unique = true,nullable = false)
    private RolesAvailable rolesAvailable;
    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(
            name = "authorities",
            joinColumns = @JoinColumn(name="role_id")
    )
    @Column(name = "permission")
    private Set<PermissionEnum> authoritiesEntities=new HashSet<>();

    @OneToMany(mappedBy = "roleEntity",cascade = CascadeType.ALL)
    private Set<UserEntity> userEntities=new HashSet<>();
}
