package ru.itmo.wp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import ru.itmo.wp.service.UserService;

import javax.servlet.http.HttpSession;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Controller
public class UserPage extends Page {

    private final UserService userService;

    public UserPage(UserService userService) {
        this.userService = userService;
    }


    @GetMapping("/user/{id}")
    public String user(Model model, @PathVariable("id") String id) {
        try {
            model.addAttribute("user", userService.findById(Long.parseLong(id)));
        } catch (NumberFormatException e) {
            // No operation
        }
        return "UserPage";
    }


    @GetMapping("user")
    public String emptyUser(HttpSession httpSession) {
        putMessage(httpSession, "please write userId");
        return "redirect:";
    }

}
