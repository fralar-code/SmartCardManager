package com.wsda.CreditCard.service;


import com.wsda.CreditCard.dto.TransitionDto;

import java.util.List;

public interface TransitionService {
    List<TransitionDto> findAllTransitionByUserId(Long id);

    void saveTransition(TransitionDto transitionDto);
}
