package dev.hexa.studentservice.application.ports.secondary;

import dev.hexa.studentservice.application.annotation.SecondaryPort;
import dev.hexa.studentservice.domain.model.Student;

import java.util.List;
import java.util.Optional;

@SecondaryPort
public interface StudentPersistencePort {

    Optional<Student> findById(Long id);

    List<Student> findAll();

    Student save(Student student);

    void deleteById(Long id);
}
