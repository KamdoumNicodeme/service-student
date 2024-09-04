package dev.hexa.studentservice.infrastructure.adapters.out;

import dev.hexa.studentservice.application.ports.out.StudentPersistencePort;
import dev.hexa.studentservice.domain.exception.StudentNotFoundException;
import dev.hexa.studentservice.domain.model.Student;
import dev.hexa.studentservice.infrastructure.adapters.out.persitance.mapper.StudentPersistenceMapper;
import dev.hexa.studentservice.infrastructure.adapters.out.persitance.repository.StudentRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class StudentPersistenceAdapter implements StudentPersistencePort {
    private final StudentPersistenceMapper studentPersistenceMapper;
    private final StudentRepository studentRepository;
    @Override
    public Optional<Student> findById(Long id) {
        return studentRepository.findById(id).map(studentPersistenceMapper::toStudent);
    }

    @Override
    public List<Student> findAll() {
        return studentPersistenceMapper.toStudentList(studentRepository.findAll());
    }

    @Override
    public Student save(Student student) {
        return studentPersistenceMapper.toStudent(studentRepository.save(studentPersistenceMapper.toStudentEntity(student)));
    }



    @Override
    public void deleteById(Long id) {

        studentRepository.deleteById(id);
    }
}
