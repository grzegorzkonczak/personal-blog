package com.checkrise.blog.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Grzegorz Kończak on 15.11.2016.
 */
public class BlogEntry {

    private String title;
    private String entry;
    private LocalDateTime entryDateTime;
    private List<BlogComment> comments;

    public BlogEntry(String title, String entry, LocalDateTime entryDateTime) {
        this.title = title;
        this.entry = entry;
        this.entryDateTime = entryDateTime;
        comments = new ArrayList<>();
    }

    public String getTitle() {
        return title;
    }

    public String getEntry() {
        return entry;
    }

    public LocalDateTime getEntryDateTime() {
        return entryDateTime;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setEntry(String entry) {
        this.entry = entry;
    }

    public List<BlogComment> getComments() {
        return new ArrayList<>(comments); // prevents appending to comments list
    }
}
