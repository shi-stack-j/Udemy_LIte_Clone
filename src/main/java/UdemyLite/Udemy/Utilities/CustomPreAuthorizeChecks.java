package UdemyLite.Udemy.Utilities;
import UdemyLite.Udemy.Entities.CourseEntity;
import UdemyLite.Udemy.Entities.EnrollmentEntity;
import UdemyLite.Udemy.Entities.UserEntity;
import UdemyLite.Udemy.Entities.UserPrincipal;
import UdemyLite.Udemy.Respository.CourseRepo;
import UdemyLite.Udemy.Respository.EnrollmentRepo;
import UdemyLite.Udemy.Respository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import java.util.Optional;
import java.util.Set;
@Component("checks")
public class CustomPreAuthorizeChecks {
    @Autowired
    UserRepo userRepo;
    @Autowired
    EnrollmentRepo enrollmentRepo;
    @Autowired
    CourseRepo courseRepo;
    public boolean isAuthorizeToGetCourse(Long course_id) {
        UserPrincipal userPrincipal = (UserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Optional<CourseEntity> courseEntity = courseRepo.findById(course_id);
        if (courseEntity.isPresent()) {
            if(courseEntity.get().getCreatedBy().getId()==userPrincipal.getId()){
                return true;
            }if(CheckForUserPresentOrNot(courseEntity.get().getEnrollments(), userPrincipal.getId())){
                return true;
            }
        }
        return false;
    }
    //To Check for is eligible to delete course or not
    public boolean isAuthorizeToDeleteUpdateCourse(Long course_id){
        Optional<CourseEntity> courseEntity=courseRepo.findById(course_id);
        UserPrincipal userPrincipal=(UserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if(courseEntity.isPresent()){
            if(courseEntity.get().getCreatedBy().getId()== userPrincipal.getId()){
                return true;
            }
            return false;
        }
        return false;
    }
    //To check for the enrollment details
    public boolean isEligibleToSeeEnrollment(Long enrollment_id){
        Optional<EnrollmentEntity> enrollment=enrollmentRepo.findById(enrollment_id);
        UserPrincipal userPrincipal=(UserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if(enrollment.isPresent()) {
            if (enrollment.get().getCourse_id().getCreatedBy().getId() == userPrincipal.getId()) {
                return true;
            }
            else if (enrollment.get().getUserEntity().getId()==userPrincipal.getId()){
                return true;
            }
        }
        return false;
    }
    //To check for the details user fetching is his/her own or not
    public boolean isEligibleToSeeDetailsById(Long user_id){
        UserPrincipal userPrincipal=(UserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Optional<UserEntity> user=userRepo.findById(user_id);
        if(user.isPresent()){
            if(user.get().getId()==userPrincipal.getId()){
                return true;
            }
        }
        return false;
    }
    //To check for the same user by email
    public boolean isEligibleToSeeDetailsByEmail(String email){
        UserPrincipal userPrincipal=(UserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Optional<UserEntity> user=userRepo.findByEmail(email);
        if (user.isPresent()){
            if(user.get().getEmail().equals(user.get().getEmail())){
                return true;
            }
        }
        return false;
    }
    //To Check for the user present or not
    public  boolean CheckForUserPresentOrNot(Set<EnrollmentEntity> enrollmentEntities,Long user_id){
        for(var i:enrollmentEntities){
            if (i.getUserEntity().getId()==user_id){
                return true;
            }
        }
        return false;
    }
}
