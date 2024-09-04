package dev.hexa.studentservice.infrastructure.adapters.secondary;

import dev.hexa.studentservice.application.annotation.SecondaryAdapter;
import dev.hexa.studentservice.application.ports.secondary.StudentPersistencePort;
import dev.hexa.studentservice.domain.model.Student;
import dev.hexa.studentservice.infrastructure.adapters.secondary.persitance.mapper.StudentPersistenceMapper;
import dev.hexa.studentservice.infrastructure.adapters.secondary.persitance.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
@SecondaryAdapter
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
