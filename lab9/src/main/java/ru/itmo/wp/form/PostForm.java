package ru.itmo.wp.form;

import javax.persistence.Lob;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class PostForm {
    @Size(min = 0, max = 60)
    @Pattern(regexp = "[A-Za-z ]*", message = "Expected simple Latin letters")
    private String tags;

    @NotNull
    @NotEmpty
    @Size(min = 1, max = 60)
    private String title;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @NotNull
    @NotEmpty
    @Size(min = 1, max = 65000, message = "Text must be isn't empty!")
    @Lob
    private String text;

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
