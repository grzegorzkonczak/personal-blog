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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BlogComment that = (BlogComment) o;

        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        return comment != null ? comment.equals(that.comment) : that.comment == null;

    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (comment != null ? comment.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "BlogComment{" +
                "name='" + name + '\'' +
                ", comment='" + comment + '\'' +
                '}';
    }
}
