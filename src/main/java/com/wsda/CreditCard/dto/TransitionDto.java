package com.wsda.CreditCard.dto;


import com.wsda.CreditCard.entity.Card;
import com.wsda.CreditCard.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TransitionDto {

    private Integer id;

    private Integer value;

    private String type;

    private User user;

    private Card card;

    private LocalDateTime timestamp;
}
