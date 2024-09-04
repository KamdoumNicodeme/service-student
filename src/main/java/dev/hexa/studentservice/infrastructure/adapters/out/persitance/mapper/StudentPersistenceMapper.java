package dev.hexa.studentservice.infrastructure.adapters.out.persitance.mapper;

import dev.hexa.studentservice.domain.model.Student;
import dev.hexa.studentservice.infrastructure.adapters.out.persitance.entity.StudentEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface StudentPersistenceMapper {

    StudentEntity toStudentEntity(Student student);

    Student toStudent(StudentEntity studentEntity);

    List<Student> toStudentList(List<StudentEntity> studentEntities);
}
