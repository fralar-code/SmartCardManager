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
public class CardDto {

    private Integer id;
    @Min(value = 1, message = "Il credito deve essere maggiore o uguale ad 1")
    @Max(value = 50000, message = "Il credito deve essere inferiore ai 50000")
    @NotNull(message = "Il campo credito non può essere vuoto")
    private Integer credit;
    @Pattern(regexp = "Attiva|Bloccata")
    private String state;
}
