package com.wsda.CreditCard.service.impl;

import com.wsda.CreditCard.dto.CardDto;
import com.wsda.CreditCard.entity.Card;
import com.wsda.CreditCard.repository.CardRepository;
import com.wsda.CreditCard.service.CardService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class CardServiceImpl implements CardService {
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private CardRepository cardRepository;


    @Override
    public Integer saveCard(CardDto cardDto) {
        Card card = new Card();
        card.setCredit(cardDto.getCredit());
        card.setState(cardDto.getState());
        return cardRepository.save(card).getId();
    }

    @Override
    public CardDto findCardById(Integer id) {
        return convertToCardDto(cardRepository.searchCardById(id));
    }

    @Transactional
    @Override
    public void updateStateById(Integer id, String state) {
        cardRepository.updateStateById(id, state);
    }

    @Transactional
    @Override
    public void updateCreditById(Integer id, Integer credit) {
        cardRepository.updateCreditById(id, credit);
    }

    @Override
    public List<CardDto> findAllCards(String keyword) {
        List<Card> cards;
        if (keyword != null) {
            cards = cardRepository.search(keyword);

        } else {
            cards = cardRepository.findAll();
        }
        return cards.stream()
                .map((m) -> convertToCardDto(m))
                .collect(Collectors.toList());
    }

    @Override
    public List<CardDto> findAllCards() {
        return cardRepository.findAll().stream()
                .map((m) -> convertToCardDto(m))
                .collect(Collectors.toList());
    }

    private CardDto convertToCardDto(Card card) {
        CardDto cardDto = null;
        if (card != null) {
            cardDto = modelMapper.map(card, CardDto.class);
        }
        return cardDto;
    }
}
