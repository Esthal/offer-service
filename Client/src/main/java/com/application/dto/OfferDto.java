package com.application.dto;


import com.application.dto.Enum.Answer;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class OfferDto {
    private long id;
    private String message;
    private int amount;
    private boolean alreadyAnswered;
    private Answer answer;
    private UserDto user;
    private LocalDate dateOfAnswer;
}
