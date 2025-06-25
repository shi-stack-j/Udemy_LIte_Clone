package UdemyLite.Udemy.DTOs;
import UdemyLite.Udemy.Entities.PermissionEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.SecondaryRow;

import java.util.List;
import java.util.ArrayList;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RoleDto {
    private Long id;
    private String name;
    private List<String> users=new ArrayList<>();
    private List<PermissionEnum> permissionEnums=new ArrayList<>();
}
