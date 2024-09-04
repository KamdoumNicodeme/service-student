package dev.hexa.studentservice.infrastructure.adapters.in.restapi.mapper;

import dev.hexa.studentservice.domain.model.Student;
import dev.hexa.studentservice.infrastructure.adapters.in.restapi.model.request.StudentCreateRequest;
import dev.hexa.studentservice.infrastructure.adapters.in.restapi.model.response.StudentResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface StudentRestMapper {
    @Mapping(target = "id", ignore = true)
    Student toStudent(StudentCreateRequest request);

    StudentResponse toStudentResponse(Student student);

    List<StudentResponse> toStudentResponseList(List<Student> students);
}
