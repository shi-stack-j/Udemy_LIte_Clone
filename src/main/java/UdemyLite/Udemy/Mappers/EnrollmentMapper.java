package UdemyLite.Udemy.Mappers;

import UdemyLite.Udemy.DTOs.EnrollmentDto;
import UdemyLite.Udemy.Entities.EnrollmentEntity;

public class EnrollmentMapper {
    public static EnrollmentDto EntityToDto(EnrollmentEntity enrollment){
        EnrollmentDto enrollmentDto=new EnrollmentDto();
        enrollmentDto.setEnrolledOn(enrollment.getEnrolledOn());
        enrollmentDto.setId(enrollment.getId());
        enrollmentDto.setCompletion(enrollment.isCompleted());
        enrollmentDto.setStudent(enrollment.getUserEntity().getUsername());
        enrollmentDto.setCourse(enrollment.getCourse_id().getCourse_name());
        return enrollmentDto;
    }
}
