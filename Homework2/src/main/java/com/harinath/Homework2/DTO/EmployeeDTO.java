package com.harinath.Homework2.DTO;

import com.harinath.Homework2.annotations.EmployeeRoleValidation;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeDTO {

    private Long id;

    @Max(value = 80,message = "Age must be lesser or equal to 80")
    @Min(value = 18,message = "Age must be grater or equal to 18")
    private Integer age;

    @NotBlank(message = "Role cannot be Empty")
    @EmployeeRoleValidation
    private String role;

    @NotBlank(message = "Employee Name is required")
    @Size(min = 3,max = 20,message = "Name must be in range of [3 - 20] characters")
    private String name;

    @NotBlank(message = "Email is required")
    @Email
    private String email;

    private LocalDateTime dateOfJoining;

    @AssertTrue(message = "User Must be active")
    private Boolean isActive;
}
