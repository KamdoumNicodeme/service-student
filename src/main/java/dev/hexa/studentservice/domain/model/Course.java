package dev.hexa.studentservice.domain.model;

import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Course {

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
