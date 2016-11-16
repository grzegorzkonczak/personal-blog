package com.checkrise.blog.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Grzegorz Kończak on 15.11.2016.
 */
public class InMemoryBlogEntryDAO implements BlogEntryDAO {

    public static final String PASSWORD = "admin";
    List<BlogEntry> entries;

    public InMemoryBlogEntryDAO() {
        entries = new ArrayList<>();
        BlogEntry entry1 = new BlogEntry("The best day I’ve ever had", "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nunc ut rhoncus felis, vel tincidunt neque. Vestibulum ut metus eleifend, malesuada nisl at, scelerisque sapien. Vivamus pharetra massa libero, sed feugiat turpis efficitur at.", LocalDateTime.now());
        BlogEntry entry2 = new BlogEntry("The absolute worst day I’ve ever had", "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nunc ut rhoncus felis, vel tincidunt neque. Vestibulum ut metus eleifend, malesuada nisl at, scelerisque sapien. Vivamus pharetra massa libero, sed feugiat turpis efficitur at.", LocalDateTime.now());
        BlogEntry entry3 = new BlogEntry("That time at the mall", "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nunc ut rhoncus felis, vel tincidunt neque. Vestibulum ut metus eleifend, malesuada nisl at, scelerisque sapien. Vivamus pharetra massa libero, sed feugiat turpis efficitur at.", LocalDateTime.now());
        entry1.addComment(new BlogComment("Don Jones", "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nunc ut rhoncus felis, vel tincidunt neque. Vestibulum ut metus eleifend, malesuada nisl at, scelerisque sapien. Vivamus pharetra massa libero, sed feugiat turpis efficitur at.", LocalDateTime.now()));
        entry1.addComment(new BlogComment("Mike Jones", "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nunc ut rhoncus felis, vel tincidunt neque. Vestibulum ut metus eleifend, malesuada nisl at, scelerisque sapien. Vivamus pharetra massa libero, sed feugiat turpis efficitur at.", LocalDateTime.now()));
        entries.add(entry1);
        entries.add(entry2);
        entries.add(entry3);
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
