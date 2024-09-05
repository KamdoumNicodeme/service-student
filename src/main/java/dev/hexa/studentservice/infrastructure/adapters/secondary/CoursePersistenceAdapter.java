package dev.hexa.studentservice.infrastructure.adapters.secondary;

import dev.hexa.studentservice.application.annotation.SecondaryAdapter;
import dev.hexa.studentservice.application.ports.secondary.CoursePersistencePort;
import dev.hexa.studentservice.domain.model.Course;
import dev.hexa.studentservice.infrastructure.adapters.secondary.persitance.entity.CourseEntity;
import dev.hexa.studentservice.infrastructure.adapters.secondary.persitance.mapper.CoursePersistenceMapper;
import dev.hexa.studentservice.infrastructure.adapters.secondary.persitance.repository.CourseRepository;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
@SecondaryAdapter
public class CoursePersistenceAdapter implements CoursePersistencePort {
    private final CourseRepository courseRepository;
    private final CoursePersistenceMapper mapper;

    @Override
    public List<Course> getSpecificCourse(List<String> criteria) {
        Specification<CourseEntity> specification = filterByAnyCriteria(criteria);
        return mapper.toCourseList(courseRepository.findAll(specification));
    }

    @Override
    public Course save(Course course) {
        return mapper.toCourse(courseRepository.save(mapper.toCourseEntity(course)));
    }


    @Override
    public Optional<Course> findById(Long id) {
        return courseRepository.findById(id).map(mapper::toCourse);
    }

    private Specification<CourseEntity> filterByAnyCriteria(List<String> criteria) {
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = criteria.stream()
                    .map(searchTerm -> createOrPredicates(criteriaBuilder, root, searchTerm))
                    .toList();

            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }

    private Predicate createOrPredicates(CriteriaBuilder criteriaBuilder, Root<CourseEntity> root, String searchTerm) {
        List<Predicate> orPredicates = List.of(
                criteriaBuilder.like(root.get("title"), "%" + searchTerm + "%"),
                criteriaBuilder.like(root.get("description"), "%" + searchTerm + "%"),
                criteriaBuilder.like(root.get("instructor"), "%" + searchTerm + "%"),
                criteriaBuilder.like(root.get("department"), "%" + searchTerm + "%"),
                criteriaBuilder.like(root.get("level"), "%" + searchTerm + "%"),
                criteriaBuilder.equal(root.get("duration"), parseInteger(searchTerm)),
                criteriaBuilder.equal(root.get("credits"), parseInteger(searchTerm)),
                criteriaBuilder.like(root.get("language"), "%" + searchTerm + "%"),
                criteriaBuilder.like(root.get("format"), "%" + searchTerm + "%"),
                criteriaBuilder.equal(root.get("price"), parseDouble(searchTerm))
        );

        return criteriaBuilder.or(orPredicates.toArray(new Predicate[0]));
    }

    private static Integer parseInteger(String searchTerm) {
        try {
            return Integer.parseInt(searchTerm);
        } catch (NumberFormatException e) {
            return null;
        }
    }

    private static Double parseDouble(String searchTerm) {
        try {
            return Double.parseDouble(searchTerm);
        } catch (NumberFormatException e) {
            return null;
        }
    }
}
