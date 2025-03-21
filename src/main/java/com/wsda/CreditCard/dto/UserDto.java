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
    @NotEmpty(message = "Il campo nome non può essere vuoto")
    @Size(min = 4, max = 45, message = "Non può superare i 45 caretteri")
    @Pattern(regexp = "^[a-zA-Z][a-zA-Z ']{3,45}", message = "Deve essere una stringa di lettere e spazi")
    private String firstName;
    @NotEmpty(message = "Il campo cognome non può essere vuoto")
    @Pattern(regexp = "^[a-zA-Z][a-zA-Z ']{3,45}", message = "Deve essere una stringa di lettere e spazi")
    @Size(min = 4, max = 45, message = "Non può superare i 45 caretteri")
    private String lastName;
    @Pattern(regexp = "Attivo|Bloccato")
    private String state;
    @NotEmpty(message = "Il campo email non può essere vuoto")
    @Email
    private String email;
    @Pattern(regexp = "[a-zA-Z0-9!@$%^&:;<>,.?/~_+-=]{4,20}", message = "Può contenere lettere numeri e caratteri speciali ")
    @NotEmpty(message = "Il campo password non può essere vuoto")
    private String password;
}
