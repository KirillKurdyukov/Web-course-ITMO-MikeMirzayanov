package ru.itmo.wp.domain;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(
        uniqueConstraints = @UniqueConstraint(columnNames = "name")
)
public class Tag {
    @Id
    @GeneratedValue
    private long id;

    @NotNull
    @Enumerated(EnumType.STRING)
    private TagName name;

    /** @noinspection unused*/
    public Tag() {
    }

    public Tag(@NotNull Tag.TagName name) {
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public TagName getName() {
        return name;
    }

    public void setName(TagName name) {
        this.name = name;
    }

    public enum TagName {
        INFO,
        TALK,
        POLL
    }

}
