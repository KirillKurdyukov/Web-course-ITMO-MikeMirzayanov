package ru.itmo.wp.controller;

import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.itmo.wp.domain.Comment;
import ru.itmo.wp.domain.Post;
import ru.itmo.wp.domain.User;
import ru.itmo.wp.exception.ValidationException;
import ru.itmo.wp.form.CommentForm;
import ru.itmo.wp.form.PostForm;
import ru.itmo.wp.service.PostService;
import ru.itmo.wp.service.UserService;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/1")
public class PostController {
    private final PostService postService;
    private final UserService userService;

    public PostController(PostService postService, UserService userService) {
        this.postService = postService;
        this.userService = userService;
    }

    @GetMapping("posts")
    public List<Post> findPosts() {
        return postService.findAll();
    }

    @PostMapping("posts")
    public void writePost(@RequestBody @Valid PostForm postForm,
                          BindingResult bindingResult,
                          HttpSession httpSession) {
        if (bindingResult.hasErrors()) {
            throw new ValidationException(bindingResult);
        }
        Post post = new Post();
        post.setTitle(postForm.getTitle());
        post.setText(postForm.getText());
        userService.writePost((User) httpSession.getAttribute("user"), post);
    }

    @PostMapping("comments")
    public void writeComments(@RequestBody @Valid CommentForm commentForm,
                              BindingResult bindingResult,
                              HttpSession httpSession) {
        if (bindingResult.hasErrors()) {
            throw new ValidationException(bindingResult);
        }

        Comment comment = new Comment();
        comment.setText(comment.getText());
        comment.setUser((User) httpSession.getAttribute("user"));
        postService.writeComment(postService.findById(commentForm.getId()), comment);
    }
}
