package dev.hexa.studentservice.application.service;

import dev.hexa.studentservice.application.annotation.DomainService;
import dev.hexa.studentservice.application.ports.primary.CourseServicePort;
import dev.hexa.studentservice.application.ports.secondary.CoursePersistencePort;
import dev.hexa.studentservice.domain.exception.CourseNotFoundException;
import dev.hexa.studentservice.domain.model.Course;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
@DomainService
public class CourseService implements CourseServicePort {

    private final CoursePersistencePort coursePersistencePort;
    @Override
    public List<Course> getSpecificCourse(List<String> criteria) {
        List<Course> courses =  coursePersistencePort.getSpecificCourse(criteria);
        if (courses.isEmpty()) {
            throw new CourseNotFoundException();
        }
        return courses;
    }
}
