package dev.hexa.studentservice.infrastructure.adapters.secondary.persitance.repository;

import dev.hexa.studentservice.infrastructure.adapters.secondary.persitance.entity.StudentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<StudentEntity, Long> {

}
