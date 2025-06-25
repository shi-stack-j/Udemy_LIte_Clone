package UdemyLite.Udemy.Services;
import UdemyLite.Udemy.DTOs.CourseDto;
import UdemyLite.Udemy.Entities.CourseEntity;
import UdemyLite.Udemy.Entities.UserEntity;
import UdemyLite.Udemy.Mappers.CourseMapper;
import UdemyLite.Udemy.Respository.CourseRepo;
import UdemyLite.Udemy.Respository.UserRepo;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import java.util.Optional;
@Service
public class CourseService {
    @Autowired
    CourseRepo courseRepo;
    @Autowired
    UserRepo userRepo;
    @Transactional
    //Work is needed to be done here
    //Create New Course
    @PreAuthorize("hasAnyRole('ADMIN','INSTRUCTOR')")
    public ResponseEntity<?> createCourse(CourseEntity courseEntity,Long id){
        try{
            Optional<UserEntity> userEntity=userRepo.findById(id);
            courseEntity.setCreatedBy(userEntity.get());
            CourseEntity courseEntity1=courseRepo.save(courseEntity);
            return new ResponseEntity<>(courseEntity.getCourse_name()+" : Created Successfully",HttpStatus.CREATED);
        }catch (Exception e){
            return new ResponseEntity<>(courseEntity.getCourse_name()+": Cannot be created ",HttpStatus.BAD_REQUEST);
        }
    }
    //Get course By Id
    @PreAuthorize("hasRole('ADMIN') or @checks.isAuthorizeToGetCourse(#id)")
    public ResponseEntity<?> GetCourseByID(Long id){
        try{
            Optional<CourseEntity> courseEntity=courseRepo.findById(id);
            if(courseEntity.isPresent()){
                CourseDto courseDto= CourseMapper.EntityToDto(courseEntity.get());
                return new ResponseEntity<>(courseDto, HttpStatus.OK);
            }else{
                return new ResponseEntity<>("Course not found with the given id :- "+id,HttpStatus.NOT_FOUND);
            }
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }
    //Delete Course By Id
    @Transactional
    @PreAuthorize("hasRole('ADMIN') or @checks.isAuthorizeToDeleteUpdateCourse(#course_id)")
    public ResponseEntity<?> DeleteCourseById(Long course_id){
        try{
            Optional<CourseEntity> courseEntity=courseRepo.findById(course_id);
            if(courseEntity.isPresent()){
                String name=courseEntity.get().getCourse_name();
                courseRepo.delete(courseEntity.get());
                return new ResponseEntity<>("Course Deleted Successfully :- "+name,HttpStatus.ACCEPTED);
            }else{
                return new ResponseEntity<>("Course not available :- "+course_id,HttpStatus.BAD_REQUEST);
            }
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_GATEWAY);
        }
    }
    //Update Course Duration
    @Transactional
    @PreAuthorize("hasRole('ADMIN') or @checks.isAuthorizeToDeleteUpdateCourse(#course_id)")
    public ResponseEntity<?> UpdateDurationOFCourse(Long course_id,int new_duration){
        try{
            System.out.println("New Duration :- "+new_duration);
            Optional<CourseEntity> courseEntity=courseRepo.findById(course_id);
            if(courseEntity.isPresent()){
                int currentDuration=courseEntity.get().getDuration();
                courseEntity.get().setDuration(new_duration);
                courseRepo.save(courseEntity.get());
                return new ResponseEntity<>("Course Duration Changed from | Current :- "+currentDuration+" : | To :- "+new_duration,HttpStatus.ACCEPTED);
            }else{
                return new ResponseEntity<>("Course not available :- "+course_id,HttpStatus.BAD_REQUEST);
            }
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_GATEWAY);
        }
    }
}
