package com.wsda.CreditCard.service;

import com.wsda.CreditCard.dto.CardDto;

import java.util.List;

public interface CardService {
    Integer saveCard(CardDto cardDto);

    CardDto findCardById(Integer id);

    void updateStateById(Integer id, String state);

    void updateCreditById(Integer id, Integer credit);

    List<CardDto> findAllCards(String keyword);

    List<CardDto> findAllCards();
}
