package com.wsda.CreditCard.controller;

import com.wsda.CreditCard.dto.CardDto;
import com.wsda.CreditCard.dto.TransitionDto;
import com.wsda.CreditCard.entity.Card;
import com.wsda.CreditCard.service.CardService;
import com.wsda.CreditCard.service.TransitionService;
import com.wsda.CreditCard.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDateTime;
import java.util.List;

@Controller
public class ShopController {
    @Autowired
    private CardService cardService;
    @Autowired
    private TransitionService transitionService;
    @Autowired
    private UserService userService;
    @Autowired
    private ModelMapper modelMapper;

    @GetMapping("/shop/payment")
    public String home(Model model) {
        CardDto card = new CardDto();
        model.addAttribute("card", card);
        return "payment";
    }

    @PostMapping("/shop/searching-card")
    public String cardCredit(Integer id, Model model) {
        CardDto existingCard = cardService.findCardById(id);
        if (existingCard == null) {
            model.addAttribute("error", "Non esiste alcuna carta con tale id");
        } else {
            model.addAttribute("successSearch", true);
            model.addAttribute("card", existingCard);
        }
        return "payment";
    }

    @PostMapping("/shop/processing-payment")
    public String createShopkeepers(@ModelAttribute("card") CardDto cardDto, Integer amount, String button, Model model,
                                    RedirectAttributes redirectAttrs, Authentication authentication) {
        if (button.equals("debit") && amount > cardDto.getCredit()) {
            //nel caso di errore restituisco la carta sul quale si è verificato l'errore
            model.addAttribute("card", cardDto);
            model.addAttribute("successSearch", true);
            model.addAttribute("errorPayment", "Saldo insufficiente");
            return "payment";
        } else {
            //aggiorniamo il saldo attuale della carta
            cardService.updateCreditById(cardDto.getId(), button.equals("debit") ? cardDto.getCredit() - amount : cardDto.getCredit() + amount);
            //creiamo una transazione fatta da quel negoziante, di quella cifra a quella ora
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            TransitionDto transitionDto = new TransitionDto();
            transitionDto.setUser(userService.findUserByEmail(userDetails.getUsername()));
            transitionDto.setCard(modelMapper.map(cardDto, Card.class));
            transitionDto.setValue(amount);
            transitionDto.setType(button.equals("debit") ? "Addebito" : "Accredito");
            transitionDto.setTimestamp(LocalDateTime.now());
            transitionService.saveTransition(transitionDto);
            redirectAttrs.addFlashAttribute("successPayment", "Transazione correttamente eseguita");
        }
        return "redirect:/shop/payment";
    }

    @GetMapping("/shop/transitions")
    public String transitionsHistory(Model model, Authentication authentication) {
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        List<TransitionDto> transitions = transitionService.findAllTransitionByUserId(userService.findUserByEmail(userDetails.getUsername()).getId());
        model.addAttribute("transitions", transitions);
        return "transitions";
    }
}
