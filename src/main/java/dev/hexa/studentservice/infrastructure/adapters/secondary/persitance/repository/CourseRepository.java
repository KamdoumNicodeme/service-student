package dev.hexa.studentservice.infrastructure.adapters.secondary.persitance.repository;

import dev.hexa.studentservice.infrastructure.adapters.secondary.persitance.entity.CourseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface CourseRepository extends JpaRepository<CourseEntity,Long>, JpaSpecificationExecutor<CourseEntity> {
}
