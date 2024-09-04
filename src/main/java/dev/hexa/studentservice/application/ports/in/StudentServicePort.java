package dev.hexa.studentservice.application.ports.in;

import dev.hexa.studentservice.domain.model.Student;

import java.util.List;

public interface StudentServicePort {

    Student findById(Long id);

    List<Student> findAll();

    Student save(Student student);

    Student update(Long id, Student student);

    void deleteById(Long id);
}
