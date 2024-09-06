package dev.hexa.studentservice.infrastructure.adapters.primary;

import dev.hexa.studentservice.application.annotation.PrimaryAdapter;
import dev.hexa.studentservice.application.ports.primary.CourseServicePort;
import dev.hexa.studentservice.infrastructure.adapters.primary.mapper.CourseRestMapper;
import dev.hexa.studentservice.infrastructure.adapters.primary.model.request.CourseCreateRequest;
import dev.hexa.studentservice.infrastructure.adapters.primary.model.response.CourseResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@PrimaryAdapter
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/courses")
public class CourseRestAdapter {

    private final CourseServicePort courseService;
    private final CourseRestMapper courseRestMapper;

    @GetMapping("/search")
    public List<CourseResponse> getSpecificCourse(@RequestParam List<String> criteria) {
        return courseRestMapper.toCourseResponse(courseService.getSpecificCourse(criteria));
    }

    public ResponseEntity<CourseResponse> saveNewCourse(@RequestBody CourseCreateRequest request){
        return ResponseEntity.status(HttpStatus.CREATED).body(courseRestMapper.toCourseResponse(courseRestMapper.toCourse(request)));
    }

    @DeleteMapping("/delete/{id}")
    public void deleteCourse(@PathVariable Long id){
        courseService.deleteCourse(id);
    }

    @PutMapping("/update/{id}")
    public CourseResponse updateCourse(@PathVariable Long id, @RequestBody CourseCreateRequest request){
        return courseRestMapper.toCourseResponse(courseService.updateCourse(id,courseRestMapper.toCourse(request)));
    }

}
