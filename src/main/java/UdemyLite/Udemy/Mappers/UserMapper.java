package UdemyLite.Udemy.Mappers;

import UdemyLite.Udemy.DTOs.UserDto;
import UdemyLite.Udemy.Entities.CourseEntity;
import UdemyLite.Udemy.Entities.EnrollmentEntity;
import UdemyLite.Udemy.Entities.UserEntity;

import java.util.List;
import java.util.stream.Collectors;

public class UserMapper {
    public static UserDto EntityToDto(UserEntity user){
        UserDto userDto=new UserDto();
        userDto.setId(user.getId());
        userDto.setRole(user.getRoleEntity().getRolesAvailable().toString());
        userDto.setName(user.getUsername());
        userDto.setEmail(user.getEmail());
        List<String> courseCreated=user.getCourseCreated().stream().map(CourseEntity::getCourse_name).collect(Collectors.toList());
        userDto.setCourseCreated(courseCreated);
        List<String> courseEnrolledIn=user.getEnrollments().stream().map(EnrollmentEntity::getCourse_id).map(CourseEntity::getCourse_name).collect(Collectors.toList());
        userDto.setCourseseEnrolledIn(courseEnrolledIn);
        return userDto;
    }
}
