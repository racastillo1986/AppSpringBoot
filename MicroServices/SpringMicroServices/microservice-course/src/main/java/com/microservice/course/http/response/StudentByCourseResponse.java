package com.microservice.course.http.response;

import com.microservice.course.dto.StudentDTO;
import lombok.*;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StudentByCourseResponse {

    private String courseName;
    private String teacher;
    private List<StudentDTO> studentList;

}
