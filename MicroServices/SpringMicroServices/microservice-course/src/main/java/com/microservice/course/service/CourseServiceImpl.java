package com.microservice.course.service;

import com.microservice.course.entities.Course;
import com.microservice.course.persistence.ICoursesRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseServiceImpl implements ICourseService{

    private ICoursesRepository coursesRepository;
    public CourseServiceImpl(ICoursesRepository coursesRepository){
        this.coursesRepository = coursesRepository;
    }

    @Override
    public List<Course> findAll() {
        return (List<Course>) coursesRepository.findAll();
    }

    @Override
    public Course findById(Long id) {
        return coursesRepository.findById(id).orElseThrow();
    }

    @Override
    public void save(Course course) {
        coursesRepository.save(course);
    }
}
