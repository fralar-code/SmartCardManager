package com.wsda.CreditCard.controller;

import com.wsda.CreditCard.dto.CardDto;
import com.wsda.CreditCard.dto.ReportDto;
import com.wsda.CreditCard.dto.UserDto;
import com.wsda.CreditCard.entity.User;
import com.wsda.CreditCard.service.CardService;
import com.wsda.CreditCard.service.ReportService;
import com.wsda.CreditCard.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDateTime;
import java.util.List;

@Controller
public class AdminController {
    @Autowired
    private ReportService reportService;
    @Autowired
    private CardService cardService;
    @Autowired
    private UserService userService;

    @GetMapping("/admin/cards")
    public String cards(Model model, String keyword) {
        CardDto card = new CardDto();
        model.addAttribute("card", card);

        List<CardDto> cards = cardService.findAllCards(keyword);
        model.addAttribute("keyword", keyword);
        model.addAttribute("cards", cards);
        return "cards";
    }


    @PostMapping("/admin/card-creation")
    public String createCard(@Valid @ModelAttribute("card") CardDto cardDto, BindingResult result, Model model,
                             RedirectAttributes redirectAttrs, Authentication authentication) {
        cardDto.setState("Active");
        if (result.hasErrors()) {
            model.addAttribute("card", cardDto);
            List<CardDto> cards = cardService.findAllCards();
            model.addAttribute("cards", cards);
            model.addAttribute("error", true);
            return "cards";
        }
        Integer id = cardService.saveCard(cardDto);
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        ReportDto reportDto = new ReportDto();
        reportDto.setUser(userService.findUserByEmail(userDetails.getUsername()));
        reportDto.setOperationType("Card created");
        reportDto.setInfo("Card_id: " + id + " amount: " + cardDto.getCredit());
        reportDto.setTimestamp(LocalDateTime.now());
        reportService.saveReport(reportDto);

        redirectAttrs.addFlashAttribute("successCard", "Cartd created correctly!");
        return "redirect:/admin/cards";
    }

    @PostMapping("/admin/change-card-status")
    public String changeCardStatus(@ModelAttribute("card") CardDto cardDto, RedirectAttributes redirectAttrs,
                                   Authentication authentication) {
        String newState = cardDto.getState().equals("Active") ? "Blocked" : "Active";
        cardService.updateStateById(cardDto.getId(), newState);

        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        ReportDto reportDto = new ReportDto();
        reportDto.setUser(userService.findUserByEmail(userDetails.getUsername()));
        reportDto.setOperationType("Card status changed");
        reportDto.setInfo("Card_id: " + cardDto.getId() + " new status: " + newState);
        reportDto.setTimestamp(LocalDateTime.now());
        reportService.saveReport(reportDto);

        redirectAttrs.addFlashAttribute("success", "Status changed correctly!");
        return "redirect:/admin/cards";
    }

    @GetMapping("/admin/shopkeepers")
    public String shopkeepers(Model model) {
        UserDto userDto = new UserDto();
        model.addAttribute("user", userDto);
        List<UserDto> users = userService.findAllShopkeepers();
        model.addAttribute("users", users);
        return "shopkeepers";
    }

    @PostMapping("/admin/save-shopkeepers")
    public String createShopkeepers(@Valid @ModelAttribute("user") UserDto userDto, BindingResult result, Model model,
                                    RedirectAttributes redirectAttrs,
                                    Authentication authentication) {
        User existingUser = userService.findUserByEmail(userDto.getEmail());

        if (existingUser != null && existingUser.getEmail() != null && !existingUser.getEmail().isEmpty()) {
            result.rejectValue("email", null, "There is already an account registered with the same email");
        }

        userDto.setState("Active");

        if (result.hasErrors()) {
            model.addAttribute("user", userDto);
            List<UserDto> users = userService.findAllShopkeepers();
            model.addAttribute("users", users);
            model.addAttribute("error", true);
            return "shopkeepers";
        }
        Long id = userService.saveUser(userDto);

        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        ReportDto reportDto = new ReportDto();
        reportDto.setUser(userService.findUserByEmail(userDetails.getUsername()));
        reportDto.setOperationType("Registered shopkeeper");
        reportDto.setInfo("user_id: " + id);
        reportDto.setTimestamp(LocalDateTime.now());
        reportService.saveReport(reportDto);

        redirectAttrs.addFlashAttribute("successRegister", "User successfully registered!");
        return "redirect:/admin/shopkeepers";
    }

    @PostMapping("/admin/change-shopkeepers-status")
    public String changeShopkeepersStatus(@ModelAttribute("user") UserDto userDto, RedirectAttributes redirectAttrs,
                                          Authentication authentication) {
        String newState = userDto.getState().equals("Active") ? "Blocked" : "Active";
        userService.updateStateById(userDto.getId(), newState);

        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        ReportDto reportDto = new ReportDto();
        reportDto.setUser(userService.findUserByEmail(userDetails.getUsername()));
        reportDto.setOperationType("Shopkeeper status changed");
        reportDto.setInfo("user_id: " + userDto.getId() + " new status: " + newState);
        reportDto.setTimestamp(LocalDateTime.now());
        reportService.saveReport(reportDto);

        redirectAttrs.addFlashAttribute("success", "Status changed correctly!");
        return "redirect:/admin/shopkeepers";
    }

    @GetMapping("/admin/reports")
    public String reports(Model model) {
        List<ReportDto> reports = reportService.findAllReport();
        model.addAttribute("reports", reports);
        return "reports";
    }


}
