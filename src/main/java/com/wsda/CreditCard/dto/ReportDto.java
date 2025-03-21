package com.wsda.CreditCard.dto;

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
public class ReportDto {

    private Integer id;

    private User user;

    private String operationType;

    private String info;

    private LocalDateTime timestamp;
}
