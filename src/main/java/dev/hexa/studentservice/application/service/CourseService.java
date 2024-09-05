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
        List<Course> courses = coursePersistencePort.getSpecificCourse(criteria);
        if (courses.isEmpty()) {
            throw new CourseNotFoundException();
        }
        return courses;
    }

    @Override
    public Course saveCourse(Course course) {
        return coursePersistencePort.save(course);
    }

    @Override
    public Course updateCourse(Course course) {
        return coursePersistencePort.findById(course.getId()).map(updatedCourse -> {
            updatedCourse.setTitle(course.getTitle());
            updatedCourse.setDescription(course.getDescription());
            updatedCourse.setInstructor(course.getInstructor());
            updatedCourse.setDepartment(course.getDepartment());
            updatedCourse.setLevel(course.getLevel());
            updatedCourse.setDuration(course.getDuration());
            updatedCourse.setCredits(course.getCredits());
            updatedCourse.setLanguage(course.getLanguage());
            updatedCourse.setFormat(course.getFormat());
            updatedCourse.setPrice(course.getPrice());
            return coursePersistencePort.update(updatedCourse.getId(), updatedCourse);
        }).orElseThrow(CourseNotFoundException::new);
    }

    @Override
    public Course findOneCourse(Long id) {
        return coursePersistencePort.findById(id).orElseThrow(CourseNotFoundException::new);
    }
}
