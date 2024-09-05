package dev.hexa.studentservice.application.ports.secondary;

import dev.hexa.studentservice.application.annotation.SecondaryPort;
import dev.hexa.studentservice.domain.model.Course;
import dev.hexa.studentservice.domain.model.Student;

import java.util.List;
import java.util.Optional;

@SecondaryPort
public interface CoursePersistencePort {
    List<Course> getSpecificCourse(List<String> criteria);
    Course save(Course course);
    Optional<Course> findById(Long id);

}
