package com.wsda.CreditCard.dto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
    private Long id;
    @NotEmpty(message = "Name field cannot be empty")
    @Size(min = 4, max = 45, message = "Cannot exceed 45 caretters")
    @Pattern(regexp = "^[a-zA-Z][a-zA-Z ']{3,45}", message = "Must be a string of letters and spaces")
    private String firstName;
    @NotEmpty(message = "Surname field cannot be empty")
    @Pattern(regexp = "^[a-zA-Z][a-zA-Z ']{3,45}", message = "Must be a string of letters and spaces")
    @Size(min = 4, max = 45, message = "Cannot exceed 45 caretters")
    private String lastName;
    @Pattern(regexp = "Active|Blocked")
    private String state;
    @NotEmpty(message = "Email field cannot be empty")
    @Email
    private String email;
    @Pattern(regexp = "[a-zA-Z0-9!@$%^&:;<>,.?/~_+-=]{4,20}", message = "Must contain letters, numbers and special characters")
    @NotEmpty(message = "Password field cannot be empty")
    private String password;
}
