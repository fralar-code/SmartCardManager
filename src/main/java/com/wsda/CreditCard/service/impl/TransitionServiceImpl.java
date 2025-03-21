package com.wsda.CreditCard.service.impl;


import com.wsda.CreditCard.dto.TransitionDto;
import com.wsda.CreditCard.entity.Transition;
import com.wsda.CreditCard.repository.TransitionRepository;
import com.wsda.CreditCard.service.TransitionService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TransitionServiceImpl implements TransitionService {
    @Autowired
    private TransitionRepository transitionRepository;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public void saveTransition(TransitionDto transitionDto) {
        Transition transition = modelMapper.map(transitionDto, Transition.class);
        transitionRepository.save(transition);
    }

    @Override
    public List<TransitionDto> findAllTransitionByUserId(Long id) {
        List<Transition> transitions = transitionRepository.findAllTransitionByUserId(id);
        return transitions.stream()
                .map((transition) -> convertToTransitionDto(transition))
                .collect(Collectors.toList());
    }

    private TransitionDto convertToTransitionDto(Transition transition) {
        TransitionDto transitionDto = null;
        if (transition != null) {
            transitionDto = modelMapper.map(transition, TransitionDto.class);
        }
        return transitionDto;
    }
}
