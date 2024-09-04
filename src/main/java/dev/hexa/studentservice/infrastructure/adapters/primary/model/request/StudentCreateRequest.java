package dev.hexa.studentservice.infrastructure.adapters.primary.model.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class StudentCreateRequest {
    @NotBlank(message = "First name can't be empty or null")
    private String firstName;
    @NotBlank(message = "Last name can't be empty or null")
    private String lastName;
    @NotNull
    private Integer age;
    @NotBlank(message = "Address can't be empty or null")
    private String address;

}
