package dev.hexa.studentservice.infrastructure.adapters.primary.mapper;

import dev.hexa.studentservice.domain.model.Course;
import dev.hexa.studentservice.infrastructure.adapters.primary.model.response.CourseResponse;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CourseRestMapper {

    List<CourseResponse> toCourseResponse(List<Course> courses);
}
