package UdemyLite.Udemy.DTOs;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class EnrollmentDto {
    private Long id;
    private String course;
    private String student;
    private Timestamp enrolledOn;
    private boolean completion;
}
