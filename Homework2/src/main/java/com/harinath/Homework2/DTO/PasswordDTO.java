package com.harinath.Homework2.DTO;

import com.harinath.Homework2.annotations.PasswordValidation;
import com.harinath.Homework2.annotations.PrimeNumberValidation;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PasswordDTO {
    @NotBlank
    @PasswordValidation
    private String password;
    @PrimeNumberValidation
    private Integer primeNumber;
}
