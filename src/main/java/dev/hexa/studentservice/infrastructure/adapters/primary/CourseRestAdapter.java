package dev.hexa.studentservice.infrastructure.adapters.primary;

import dev.hexa.studentservice.application.annotation.PrimaryAdapter;
import dev.hexa.studentservice.application.ports.primary.CourseServicePort;
import dev.hexa.studentservice.infrastructure.adapters.primary.mapper.CourseRestMapper;
import dev.hexa.studentservice.infrastructure.adapters.primary.model.response.CourseResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
}
