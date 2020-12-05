package ru.itmo.wp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import ru.itmo.wp.domain.Comment;
import ru.itmo.wp.domain.Post;
import ru.itmo.wp.service.PostService;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
public class PostPage extends Page{
    private final PostService postService;

    public PostPage(PostService postService) {
        this.postService = postService;
    }


    @GetMapping("/post/{id}")
    public String postGet(Model model, @PathVariable("id") String id) {
        try {
            model.addAttribute("post", postService.findById(Long.parseLong(id)));
            model.addAttribute("comment", new Comment());
        } catch (NumberFormatException e) {
            // No operation
        }
        return "PostPage";
    }

    @PostMapping("/post/{id}")
    public String postPost(Model model, @Valid @ModelAttribute("comment") Comment comment,
                           BindingResult bindingResult,
                           @PathVariable("id") String id,
                           HttpSession httpSession) {
        model.addAttribute("post", postService.findById(Long.parseLong(id)));
        if (bindingResult.hasErrors()) {
            return "PostPage";
        }

        try {
            Post currentPost = postService.findById(Long.parseLong(id));
            comment.setUser(getUser(httpSession));
            postService.writeComment(currentPost, comment);
        } catch (NumberFormatException e) {
            return "PostPage";
        }
        putMessage(httpSession, "You published new comment");

        return "redirect:/post/" + id;
    }


    @GetMapping("/post/")
    public String emptyUser(HttpSession httpSession) {
        putMessage(httpSession, "please write post Id");
        return "PostPage";
    }
}
