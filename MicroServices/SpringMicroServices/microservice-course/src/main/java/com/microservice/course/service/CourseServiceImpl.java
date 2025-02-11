package com.microservice.course.service;

import com.microservice.course.client.StudentClient;
import com.microservice.course.dto.StudentDTO;
import com.microservice.course.entities.Course;
import com.microservice.course.http.response.StudentByCourseResponse;
import com.microservice.course.persistence.ICoursesRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseServiceImpl implements ICourseService {

    private ICoursesRepository coursesRepository;
    private StudentClient studentClient;

    public CourseServiceImpl(ICoursesRepository coursesRepository, StudentClient studentClient) {
        this.coursesRepository = coursesRepository;
        this.studentClient = studentClient;
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

    @Override
    public StudentByCourseResponse findByIdCourse(Long idCourse) {
        // consultar el curso
        Course course = coursesRepository.findById(idCourse).orElse(new Course());

        // Obtener los estudiantes
        List<StudentDTO> studentDTOList = studentClient.findAllStudentByCourse(idCourse);

        return StudentByCourseResponse.builder()
                .courseName(course.getName())
                .teacher(course.getTeacher())
                .studentList(studentDTOList)
                .build();
    }
}
