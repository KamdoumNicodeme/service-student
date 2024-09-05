package dev.hexa.studentservice.application.ports.primary;

import dev.hexa.studentservice.application.annotation.PrimaryPort;
import dev.hexa.studentservice.domain.model.Course;

import java.util.List;

@PrimaryPort
public interface CourseServicePort {
    List<Course> getSpecificCourse(List<String> criteria);
}
