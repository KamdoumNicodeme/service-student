package dev.hexa.studentservice.infrastructure.adapters.primary;

import dev.hexa.studentservice.application.annotation.PrimaryAdapter;
import dev.hexa.studentservice.application.ports.primary.StudentServicePort;
import dev.hexa.studentservice.infrastructure.adapters.primary.mapper.StudentRestMapper;
import dev.hexa.studentservice.infrastructure.adapters.primary.model.request.StudentCreateRequest;
import dev.hexa.studentservice.infrastructure.adapters.primary.model.response.StudentResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/students"  )
@RequiredArgsConstructor
@PrimaryAdapter
public class StudentRestAdapter {

    private final StudentServicePort studentService;
    private final StudentRestMapper studentMapper;

    @GetMapping("/")
    public List<StudentResponse> findAll() {
        return studentMapper.toStudentResponseList(studentService.findAll());
    }

    @GetMapping("/{id}")
    public StudentResponse findById(@PathVariable Long id) {
        return studentMapper.toStudentResponse(studentService.findById(id));
    }

    @PostMapping("/save")
    public ResponseEntity<StudentResponse>  save(@Valid @RequestBody StudentCreateRequest studentCreateRequest) {
        return ResponseEntity.status(HttpStatus.CREATED).body(studentMapper.toStudentResponse(studentService.save(studentMapper.toStudent(studentCreateRequest))));

    }

    @PutMapping("/{id}")
    public StudentResponse update(@PathVariable Long id, @Valid @RequestBody StudentCreateRequest studentCreateRequest) {
        return studentMapper.toStudentResponse(studentService.update(id, studentMapper.toStudent(studentCreateRequest)));
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id) {
        studentService.deleteById(id);
    }
}
