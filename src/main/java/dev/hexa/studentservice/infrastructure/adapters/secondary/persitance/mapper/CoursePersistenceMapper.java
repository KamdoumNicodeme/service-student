package dev.hexa.studentservice.infrastructure.adapters.secondary.persitance.mapper;

import dev.hexa.studentservice.domain.model.Course;
import dev.hexa.studentservice.infrastructure.adapters.secondary.persitance.entity.CourseEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CoursePersistenceMapper {
    CourseEntity toCourseEntity(Course course);
    Course toCourse(CourseEntity courseEntity);

    List<Course> toCourseList(List<CourseEntity> courseEntities);
}
