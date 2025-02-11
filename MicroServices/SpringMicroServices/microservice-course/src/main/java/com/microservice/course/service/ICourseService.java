package com.microservice.course.service;

import com.microservice.course.entities.Course;
import com.microservice.course.http.response.StudentByCourseResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ICourseService {
    List<Course> findAll();
    Course findById(Long id);
    void save(Course course);

    StudentByCourseResponse findByIdCourse(Long idCourse);

}
