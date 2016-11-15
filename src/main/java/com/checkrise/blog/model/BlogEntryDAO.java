package com.checkrise.blog.model;

import java.util.List;

/**
 * Created by Grzegorz Kończak on 15.11.2016.
 */
public interface BlogEntryDAO {

    boolean add(BlogEntry entry);

    boolean remove(BlogEntry entry);

    List<BlogEntry> findAll();

    BlogEntry findBySlug(String slug);
}
