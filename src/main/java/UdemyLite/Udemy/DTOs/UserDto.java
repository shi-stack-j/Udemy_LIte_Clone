package UdemyLite.Udemy.DTOs;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.ArrayList;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
    private String name;
    private Long id;
    private String email;
    private String role;
    private List<String> courseCreated=new ArrayList<>();
    private List<String> CourseseEnrolledIn=new ArrayList<>();
}
