package UdemyLite.Udemy.Services;

import UdemyLite.Udemy.DTOs.EnrolledStudentDto;
import UdemyLite.Udemy.DTOs.EnrollmentDto;
import UdemyLite.Udemy.Entities.CourseEntity;
import UdemyLite.Udemy.Entities.EnrollmentEntity;
import UdemyLite.Udemy.Entities.UserEntity;
import UdemyLite.Udemy.Mappers.EnrolledSudentsMapper;
import UdemyLite.Udemy.Mappers.EnrollmentMapper;
import UdemyLite.Udemy.Respository.CourseRepo;
import UdemyLite.Udemy.Respository.EnrollmentRepo;
import UdemyLite.Udemy.Respository.UserRepo;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import java.util.Optional;
import java.util.Set;

@Service
public class EnrollmentService {
    @Autowired
    CourseRepo courseRepo;
    @Autowired
    UserRepo userRepo;
    @Autowired
    EnrollmentRepo enrollmentRepo;
    @Transactional
    //To enroll in new course
    @PreAuthorize("hasAnyRole('ADMIN','INSTRUCTOR')")
    public ResponseEntity<?> EnrollInCourse(Long course_id,Long student_id){
        try{
            Optional<CourseEntity> courseEntity=courseRepo.findById(course_id);
            if(courseEntity.isPresent()){
                Optional<UserEntity> userEntity=userRepo.findById(student_id);
                if(userEntity.isPresent() && !checkForContains(courseEntity.get().getEnrollments(),userEntity.get())){
                    EnrollmentEntity enrollmentEntity=new EnrollmentEntity();
                    enrollmentEntity.setCourse_id(courseEntity.get());
                    enrollmentEntity.setUserEntity(userEntity.get());
                    EnrollmentEntity enrollmentEntity1=enrollmentRepo.save(enrollmentEntity);
                    EnrollmentDto enrollmentDto=EnrollmentMapper.EntityToDto(enrollmentEntity1);
                    return new ResponseEntity<>(enrollmentDto, HttpStatus.CREATED);
                }else{
                    return new ResponseEntity<>("User Not Present  or Already Enrolled",HttpStatus.NOT_FOUND);
                }
            }else{
                return new ResponseEntity<>("Course Not Found",HttpStatus.NOT_FOUND);
            }
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_GATEWAY);
        }
    }
    //To Get The enrollment by id
    @PreAuthorize("hasAnyRole('ADMIN','INSTRUCTOR') or @checks.isEligibleToSeeEnrollment(#enroll_id)")
    public ResponseEntity<?> GetEnrollment(Long enroll_id){
        try{
            Optional<EnrollmentEntity> enrollment=enrollmentRepo.findById(enroll_id);
            if(enrollment.isPresent()){
                EnrollmentDto enrollmentDto= EnrollmentMapper.EntityToDto(enrollment.get());
                return new ResponseEntity<>(enrollmentDto,HttpStatus.OK);
            }else{
                return new ResponseEntity<>("Enrollment not found with id :- "+enroll_id,HttpStatus.NOT_FOUND);
            }
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }
    //To get the enrolled students of particular course
    @PreAuthorize("hasRole('ADMIN') or @checks.isAuthorizeToDeleteUpdateCourse(#course_id)")
    public ResponseEntity<?> SeeEnrolledStudents(Long course_id){
        try{
            Optional<CourseEntity> courseEntity=courseRepo.findById(course_id);
            if(courseEntity.isPresent()){
                EnrolledStudentDto enrollmentDto= EnrolledSudentsMapper.EntityToDto(courseEntity.get());
                return new ResponseEntity<>(enrollmentDto,HttpStatus.OK);
            }else{
                return new ResponseEntity<>("Course Not found ",HttpStatus.NOT_FOUND);
            }
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }
    //This is to unenroll from from the course
    @Transactional
    @PreAuthorize("hasRole('ADMIN') or @checks.isAuthorizeToGetCourse(#course_id)")
    public ResponseEntity<?> UnEnrollFromCourse(Long course_id,Long student_id){
        try{
            Optional<CourseEntity> courseEntity=courseRepo.findById(course_id);
            if(courseEntity.isPresent()){
                Optional<UserEntity> user=userRepo.findById(student_id);
                if(user.isPresent()){
                    if(checkForContains(courseEntity.get().getEnrollments(),user.get())){
                        for(var i:courseEntity.get().getEnrollments()){
                            if(i.getUserEntity()==user.get()){
                                courseEntity.get().getEnrollments().remove(i);
                                courseRepo.save(courseEntity.get());
                                user.get().getEnrollments().remove(i);
                                userRepo.save(user.get());
                                enrollmentRepo.delete(i);
                            }
                        }
                        return new ResponseEntity<>("Student UnEnrolled Successfully",HttpStatus.ACCEPTED);
                    }else{
                        return new ResponseEntity<>("Student not enrolled in course ",HttpStatus.NOT_FOUND);
                    }
                }else{
                    return new ResponseEntity<>("User Not Found ",HttpStatus.NOT_FOUND);
                }
            }else{
                return new ResponseEntity<>("Course not found ",HttpStatus.NOT_FOUND);
            }
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }
    //This is utility method used to check for contains of user in enrollments
    public boolean checkForContains(Set<EnrollmentEntity> entity,UserEntity user){
        if(entity.size()==0)return false;
        for(var i: entity){
            if(i.getUserEntity()==user){
                return true;
            }
        }
        return false;
    }
}
