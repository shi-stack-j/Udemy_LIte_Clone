package UdemyLite.Udemy.Mappers;

import UdemyLite.Udemy.DTOs.CourseDto;
import UdemyLite.Udemy.Entities.CourseEntity;
import UdemyLite.Udemy.Entities.EnrollmentEntity;
import UdemyLite.Udemy.Entities.UserEntity;
import java.util.List;
import java.util.stream.Collectors;

public class CourseMapper {
    public static CourseDto EntityToDto(CourseEntity courseEntity){
        CourseDto courseDto=new CourseDto();
        courseDto.setId(courseEntity.getId());
        courseDto.setName(courseEntity.getCourse_name());
        courseDto.setDuration(courseEntity.getDuration());
        courseDto.setCeatedBy(courseEntity.getCreatedBy().getUsername());
        courseDto.setCretedOn(courseEntity.getCreatedAt());
        courseDto.setDescription(courseEntity.getCourse_description());
        List<String> enrollments=courseEntity.getEnrollments().stream().map(EnrollmentEntity::getUserEntity).map(UserEntity::getUsername).collect(Collectors.toList());
        courseDto.setEnrollments(enrollments);
        return courseDto;

    }

}
