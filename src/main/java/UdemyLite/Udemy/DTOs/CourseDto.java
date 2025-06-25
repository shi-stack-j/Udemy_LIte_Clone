package UdemyLite.Udemy.DTOs;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CourseDto {
    private Long id;
    private int duration;
    private String description;
    private String name;
    private String ceatedBy;
    private Timestamp cretedOn;
    private List<String> enrollments=new ArrayList<>();
}
