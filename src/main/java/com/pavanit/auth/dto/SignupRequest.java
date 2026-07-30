package com.pavanit.auth.dto;

import jakarta.validation.constraints.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SignupRequest {

    @NotBlank(message = "First Name is required")
    private String firstName;

    @NotBlank(message = "Last Name is required")
    private String lastName;

    @Pattern(regexp = "^[0-9]{10}$",
            message = "Mobile number must contain exactly 10 digits")
    private String mobile;

    @Email(message = "Invalid Email")
    @NotBlank(message = "Email is required")
    private String email;

    @Size(min = 6, message = "Password must be at least 6 characters")
    private String password;

    @Size(min = 6, message = "Confirm Password must be at least 6 characters")
    private String confirmPassword;

    @Min(value = 18, message = "Age should be at least 18")
    private Integer age;

    @NotBlank(message = "Gender is required")
    private String gender;
}