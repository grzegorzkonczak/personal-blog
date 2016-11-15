package com.checkrise.blog.model;

import com.github.slugify.Slugify;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Grzegorz Kończak on 15.11.2016.
 */
public class BlogEntry {

    private String slug;
    private String title;
    private String entry;
    private LocalDateTime entryDateTime;
    private List<BlogComment> comments;

    public BlogEntry(String title, String entry, LocalDateTime entryDateTime) {
        this.title = title;
        this.entry = entry;
        this.entryDateTime = entryDateTime;
        comments = new ArrayList<>();
        Slugify slugify = new Slugify();
        slug = slugify.slugify(title);
    }

    public String getTitle() {
        return title;
    }

    public String getEntry() {
        return entry;
    }

    public String getEntryDateTime() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MMM-dd HH:mm");
        return entryDateTime.format(formatter);
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setEntry(String entry) {
        this.entry = entry;
    }

    public boolean addComment(BlogComment comment){
        return comments.add(comment);
    }

    public List<BlogComment> getComments() {
        return new ArrayList<>(comments); // prevents appending to comments list
    }

    public int getCommentsCount(){
        return comments.size();
    }

    public String getSlug() {
        return slug;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BlogEntry blogEntry = (BlogEntry) o;

        if (title != null ? !title.equals(blogEntry.title) : blogEntry.title != null) return false;
        if (entry != null ? !entry.equals(blogEntry.entry) : blogEntry.entry != null) return false;
        return entryDateTime != null ? entryDateTime.equals(blogEntry.entryDateTime) : blogEntry.entryDateTime == null;

    }

    @Override
    public int hashCode() {
        int result = title != null ? title.hashCode() : 0;
        result = 31 * result + (entry != null ? entry.hashCode() : 0);
        result = 31 * result + (entryDateTime != null ? entryDateTime.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "BlogEntry{" +
                "title='" + title + '\'' +
                ", entry='" + entry + '\'' +
                ", entryDateTime=" + entryDateTime +
                '}';
    }
}
