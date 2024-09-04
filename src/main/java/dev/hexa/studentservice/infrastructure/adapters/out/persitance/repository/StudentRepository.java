package dev.hexa.studentservice.infrastructure.adapters.out.persitance.repository;

import dev.hexa.studentservice.infrastructure.adapters.out.persitance.entity.StudentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<StudentEntity, Long> {

}
