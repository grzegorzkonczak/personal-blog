package com.checkrise.blog.model;

import org.junit.Before;
import org.junit.Test;

import java.time.LocalDateTime;

import static org.junit.Assert.*;

/**
 * Created by Grzegorz Kończak on 15.11.2016.
 */
public class InMemoryBlogEntryDAOTest {

    private InMemoryBlogEntryDAO dao;
    private BlogEntry entry1;

    @Before
    public void setUp() throws Exception {
        dao = new InMemoryBlogEntryDAO();
        entry1 = new BlogEntry("Test1", "entry", LocalDateTime.now());
        dao.add(entry1);
    }

    @Test
    public void ifSlugPresentReturnsProperBlogEntry() throws Exception {
        String slug = entry1.getSlug();

        BlogEntry testEntry = dao.findBySlug(slug);

        assertEquals(entry1, testEntry);
    }

    @Test(expected = NotFoundException.class)
    public void ifSlugNotPresentThrowsException() throws Exception {
        dao.findBySlug("NonExistingSlug");
    }
}