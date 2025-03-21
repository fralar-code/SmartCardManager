package com.wsda.CreditCard.controller;

import com.wsda.CreditCard.dto.CardDto;
import com.wsda.CreditCard.dto.UserDto;
import com.wsda.CreditCard.entity.User;
import com.wsda.CreditCard.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.WebAttributes;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


@Controller
public class AuthController implements ErrorController {
    @Autowired
    private UserService userService;

    @GetMapping("/access-denied")
    public String accessDenied(Authentication authentication, Model model) {
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        model.addAttribute("errorInfo", "You cannot access this page, role: " + userDetails.getAuthorities());
        return "error";
    }

    @GetMapping("/error")
    public String errorPage(Model model) {
        model.addAttribute("errorInfo", "Page not found");
        return "error";
    }

    @GetMapping("/")
    public String home(Model model) {
        CardDto card = new CardDto();
        model.addAttribute("card", card);
        return "home";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/login-error")
    public String login(HttpServletRequest request, Model model) {
        HttpSession session = request.getSession(false);
        String errorMessage = null;
        if (session != null) {
            AuthenticationException exception = (AuthenticationException) session.getAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);
            if (exception instanceof BadCredentialsException) {
                errorMessage = "Incorrect email or password";
            } else {
                errorMessage = exception.getMessage();
            }
        }
        model.addAttribute("error", errorMessage);
        return "login";
    }

}
