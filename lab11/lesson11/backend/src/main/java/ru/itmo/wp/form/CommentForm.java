package ru.itmo.wp.form;

import ru.itmo.wp.domain.Post;

import javax.persistence.Lob;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class CommentForm {
    @NotEmpty
    private long id;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @NotEmpty
    @Size(min = 1, max = 10000, message = "Text must be isn't empty!")
    @Lob
    private String text;
}
