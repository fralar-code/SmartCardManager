package com.wsda.CreditCard.controller;

import com.wsda.CreditCard.dto.CardDto;
import com.wsda.CreditCard.service.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserController {
    @Autowired
    private CardService cardService;

    @PostMapping("/account-balance")
    public String cardCredit(Integer id, Model model) {
        CardDto existingCard = cardService.findCardById(id);
        if (existingCard == null) {
            model.addAttribute("error", "Non esiste alcuna carta con tale id");
        } else {
            model.addAttribute("success", true);
            model.addAttribute("card", existingCard);
        }
        return "home";
    }
}
