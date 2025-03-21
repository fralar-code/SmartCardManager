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
    @Min(value = 1, message = "The credit must be greater than or equal to 1")
    @Max(value = 50000, message = "The credit must be less than 50000")
    @NotNull(message = "The credit field cannot be blank")
    private Integer credit;
    @Pattern(regexp = "Active|Blocked")
    private String state;
}
