package com.hogan.hogan_blog.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.time.Instant;

@Entity
@Table
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Instant getCreatOn() {
        return creatOn;
    }

    public void setCreatOn(Instant creatOn) {
        this.creatOn = creatOn;
    }

    public Instant getUpdateOn() {
        return updateOn;
    }

    public void setUpdateOn(Instant updateOn) {
        this.updateOn = updateOn;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @NotBlank
    @Column
    private String title;

    @Lob //speicifies that a persistent property or field should be persisted as a large object to a database-supported object type
    @Column
    @NotEmpty
    private String content;

    @Column
    private Instant creatOn;
    @Column
    private Instant updateOn;

    @Column
    @NotBlank
    private String username;







}