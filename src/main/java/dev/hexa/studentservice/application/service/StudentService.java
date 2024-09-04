package dev.hexa.studentservice.application.service;

import dev.hexa.studentservice.application.annotation.Service;
import dev.hexa.studentservice.application.ports.in.StudentServicePort;
import dev.hexa.studentservice.application.ports.out.StudentPersistencePort;
import dev.hexa.studentservice.domain.exception.StudentNotFoundException;
import dev.hexa.studentservice.domain.model.Student;
import lombok.RequiredArgsConstructor;


import java.util.Collections;
import java.util.List;

@RequiredArgsConstructor
@Service
public class StudentService implements StudentServicePort {

    private final StudentPersistencePort studentPersistencePort;

    @Override
    public Student findById(Long id) {
        return studentPersistencePort.findById(id).orElseThrow(StudentNotFoundException::new);
    }

    @Override
    public List<Student> findAll() {
        return studentPersistencePort.findAll() != null ? studentPersistencePort.findAll() : Collections.emptyList();
    }

    @Override
    public Student save(Student student) {
        return studentPersistencePort.save(student);
    }

    @Override
    public Student update(Long id, Student student) {
        return studentPersistencePort.findById(id).map(s -> {
            s.setFirstName(student.getFirstName());
            s.setLastName(student.getLastName());
            s.setAge(student.getAge());
            s.setAddress(student.getAddress());
            return studentPersistencePort.save(s);
        }).orElseThrow(StudentNotFoundException::new);
    }

    @Override
    public void deleteById(Long id) {
        if (studentPersistencePort.findById(id).isEmpty()) {
            throw new StudentNotFoundException();
        }
        studentPersistencePort.deleteById(id);
    }
}
