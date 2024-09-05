package dev.hexa.studentservice.application.ports.primary;

import dev.hexa.studentservice.application.annotation.PrimaryPort;
import dev.hexa.studentservice.domain.model.Course;
import dev.hexa.studentservice.domain.model.Student;

import java.util.List;
import java.util.Optional;

@PrimaryPort
public interface CourseServicePort {
    List<Course> getSpecificCourse(List<String> criteria);
    Course saveCourse(Course course);
    Course updateCourse(Course course);
    Course findOneCourse(Long id);
}
