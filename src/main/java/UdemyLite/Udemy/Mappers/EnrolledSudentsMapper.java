package UdemyLite.Udemy.Mappers;

import UdemyLite.Udemy.DTOs.EnrolledStudentDto;
import UdemyLite.Udemy.Entities.CourseEntity;
import UdemyLite.Udemy.Entities.EnrollmentEntity;
import UdemyLite.Udemy.Entities.UserEntity;
import org.w3c.dom.stylesheets.LinkStyle;

import java.util.List;
import java.util.stream.Collectors;

public class EnrolledSudentsMapper {
    public static EnrolledStudentDto EntityToDto(CourseEntity courseEntity){
        try{
            EnrolledStudentDto enrolledStudentDto=new EnrolledStudentDto();
            enrolledStudentDto.setName(courseEntity.getCourse_name());
            List<String> enrollments=courseEntity.getEnrollments().stream().map(EnrollmentEntity::getUserEntity).map(UserEntity::getUsername).collect(Collectors.toList());
            enrolledStudentDto.setEnrollments(enrollments);
            return enrolledStudentDto;
        }catch (Exception e){
            return null;
        }
    }
}
