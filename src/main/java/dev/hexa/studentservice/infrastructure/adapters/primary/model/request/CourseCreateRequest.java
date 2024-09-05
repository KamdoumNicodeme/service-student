package dev.hexa.studentservice.infrastructure.adapters.primary.model.request;

import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CourseCreateRequest {

    private Long id;
    private String title;
    private String description;
    private String instructor;
    private String department;
    private String level;
    private Integer duration;
    private Integer credits;
    private String language;
    private String format;
    private Double price;
}
