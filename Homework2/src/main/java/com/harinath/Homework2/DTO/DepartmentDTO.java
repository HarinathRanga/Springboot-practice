package com.harinath.Homework2.DTO;


import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DepartmentDTO {
    private Long id;

    @NotBlank(message = "Title is required")
    @Size(min = 2,max = 20,message = "title size should be between [2 - 20]")
    private String title;

    @AssertTrue(message = "Department must be active")
    private Boolean isActive;
    @JsonFormat(pattern = "hh:mm:ss dd-MM-yyyy")
    private LocalDateTime createdAt;
}
