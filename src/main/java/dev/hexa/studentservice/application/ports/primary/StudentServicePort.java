package dev.hexa.studentservice.application.ports.primary;

import dev.hexa.studentservice.application.annotation.PrimaryPort;
import dev.hexa.studentservice.domain.model.Student;

import java.util.List;
@PrimaryPort
public interface StudentServicePort {

    Student findById(Long id);

    List<Student> findAll();

    Student save(Student student);

    Student update(Long id, Student student);

    void deleteById(Long id);
}
