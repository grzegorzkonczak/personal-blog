package com.checkrise.blog.model;

/**
 * Created by Grzegorz Kończak on 15.11.2016.
 */
public class BlogComment {
    private String name;
    private String comment;

    public BlogComment(String name, String comment) {
        this.name = name;
        this.comment = comment;
    }

    public String getName() {
        return name;
    }

    public String getComment() {
        return comment;
    }
}
