package com.example.mvc.dto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EmplyeeDTO {
    private Long id;
    @NotBlank(message = "Name cannot be null")
    @Size(min = 2, max = 50, message = "Name must be between 2 and 50 characters")
    private String name;
    @NotBlank(message = "Email cannot be null")
    @Email(message = "Email should be valid")
    private String email;
    @Max(value = 65, message = "Age should not be greater than 65")
    @Min(value = 18, message = "Age should not be less than 18")
    private Integer age;
    @Pattern(regexp = "^(ADMIN|ADMIN)$",message = "Role must be either 'ADMIN' or 'USER'")
    private String role;
    @NotNull(message = "not null salary") @Positive(message = "Salary must be a positive number")
    private Integer Salary;

    private LocalDate dateOfJoining;
    private Boolean isActive;


}
