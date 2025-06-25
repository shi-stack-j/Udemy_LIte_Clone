package UdemyLite.Udemy.Controllers;
import UdemyLite.Udemy.Entities.CourseEntity;
import UdemyLite.Udemy.Services.EnrollmentService;
import com.fasterxml.jackson.annotation.OptBoolean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Optional;
@RestController
@RequestMapping("/Enrollment")
public class EnrollmentController {
    @Autowired
    EnrollmentService enrollmentService;
    @PostMapping("/EnrollInCourse/{course_id}/{user_id}")
    public ResponseEntity<?> EnrollInCourse(@PathVariable Long course_id,@PathVariable  Long user_id ){
        return enrollmentService.EnrollInCourse(course_id,user_id);
    }
    @GetMapping("/Get_Enrollment/{enroll_id}")
    public ResponseEntity<?> GetEnrollmentById(@PathVariable Long enroll_id){
        return enrollmentService.GetEnrollment(enroll_id);
    }
    @GetMapping("/Get_Enrolled_students/{course_id}")
    public ResponseEntity<?> SeeEnrolledStudentOfCourse(@PathVariable Long course_id){
        return enrollmentService.SeeEnrolledStudents(course_id);
    }
    @PutMapping("/UnEnroll_Student/{course_id}/{student_id}")
    public ResponseEntity<?> UnEnrollStudentFromCourse(@PathVariable Long student_id,@PathVariable Long course_id){
        return enrollmentService.UnEnrollFromCourse(course_id,student_id);
    }
}
