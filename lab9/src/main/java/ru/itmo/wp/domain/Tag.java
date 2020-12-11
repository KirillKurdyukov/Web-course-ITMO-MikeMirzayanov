package ru.itmo.wp.domain;

import javax.persistence.*;

@Entity
@Table(
        uniqueConstraints = @UniqueConstraint(columnNames = "name")
)
public class Tag implements Comparable<Tag>{
    @Id
    @GeneratedValue
    private long id;

    private String name;

    /** @noinspection unused*/
    public Tag() {
    }

    public Tag(String name) {
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int compareTo(Tag tag) {
        return this.getName().compareTo(tag.getName());
    }
}
