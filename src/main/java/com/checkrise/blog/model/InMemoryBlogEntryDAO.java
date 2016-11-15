package com.checkrise.blog.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Grzegorz Kończak on 15.11.2016.
 */
public class InMemoryBlogEntryDAO implements BlogEntryDAO {

    List<BlogEntry> entries;

    public InMemoryBlogEntryDAO() {
        entries = new ArrayList<>();
        entries.add(new BlogEntry("The best day I’ve ever had", "Nothing Special", LocalDateTime.now()));
        entries.add(new BlogEntry("The absolute worst day I’ve ever had", "Nothing Special", LocalDateTime.now()));
        entries.add(new BlogEntry("That time at the mall", "Nothing Special", LocalDateTime.now()));
    }

    @Override
    public boolean add(BlogEntry entry) {
        return entries.add(entry);
    }

    @Override
    public boolean remove(BlogEntry entry) {
        return entries.remove(entry);
    }

    @Override
    public List<BlogEntry> findAll() {
        return new ArrayList<>(entries);
    }

    @Override
    public BlogEntry findBySlug(String slug) {
        return entries.stream()
                .filter(entry -> entry.getSlug().equals(slug))
                .findFirst()
                .orElseThrow(NotFoundException::new);
    }
}
