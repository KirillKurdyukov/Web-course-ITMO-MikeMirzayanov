package ru.itmo.wp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import ru.itmo.wp.form.NoticeForm;
import ru.itmo.wp.service.NoticeService;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
public class NoticePage extends Page {
    private final NoticeService noticeService;

    public NoticePage(NoticeService noticeService) {
        this.noticeService = noticeService;
    }

    @GetMapping("/notice")
    public String notice() {
        return "NoticePage";
    }

    @PostMapping("/notice")
    public String doNotice(@Valid NoticeForm noticeForm, BindingResult bindingResult,
                           HttpSession httpSession) {
        if (bindingResult.hasErrors()) {
            putMessage(httpSession, "No correct notice!");
            return "NoticePage";
        }

        noticeService.save(noticeForm);
        return "redirect:";
    }
}
