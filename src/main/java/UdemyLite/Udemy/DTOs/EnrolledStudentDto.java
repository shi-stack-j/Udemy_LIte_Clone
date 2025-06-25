package UdemyLite.Udemy.DTOs;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class EnrolledStudentDto {
    private String name;
    private List<String> enrollments=new ArrayList<>();
}
