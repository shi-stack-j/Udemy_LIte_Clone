package UdemyLite.Udemy.Controllers;
import UdemyLite.Udemy.Entities.CourseEntity;
import UdemyLite.Udemy.Services.CourseService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
@RestController
@RequestMapping("/Course")
public class CourseController {
    @Autowired
    CourseService courseService;
    @PostMapping("/Create_Course/{user_id}")
    public ResponseEntity<?> CreateCourse(@Valid @RequestBody CourseEntity courseEntity, @PathVariable Long user_id){
        return courseService.createCourse(courseEntity,user_id);
    }
    @GetMapping("/Get_Course/{course_id}")
    public ResponseEntity<?> GetCourseByID(@PathVariable Long course_id){
        return courseService.GetCourseByID(course_id);
    }
    @DeleteMapping("/DeleteByID/{course_id}")
    public ResponseEntity<?> DeleteCourseByID(@PathVariable Long course_id){
        return courseService.DeleteCourseById(course_id);
    }
    @PutMapping("/Update_Duration/{course_id}/{new_duration}")
    public ResponseEntity<?> UpdateDurationByID(@PathVariable Long course_id,@PathVariable  int new_duration){
        return courseService.UpdateDurationOFCourse(course_id,new_duration);
    }
}
