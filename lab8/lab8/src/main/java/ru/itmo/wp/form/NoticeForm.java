package ru.itmo.wp.form;

import javax.persistence.Lob;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class NoticeForm {
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @NotNull(message = "The message can't be empty.")
    @NotEmpty(message = "The message can't be empty!")
    @Lob
    @Size(min = 1, max = 255, message =
            "You have exceeded the size of the notification (maxSize = 255)")
    private String content;

}
