package dev.hexa.studentservice.application.ports.secondary;

import dev.hexa.studentservice.application.annotation.SecondaryPort;
import dev.hexa.studentservice.domain.model.Course;

import java.util.List;

@SecondaryPort
public interface CoursePersistencePort {
    List<Course> getSpecificCourse(List<String> criteria);

}
