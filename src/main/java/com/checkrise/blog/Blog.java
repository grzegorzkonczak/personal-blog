package com.checkrise.blog;

import com.checkrise.blog.model.BlogComment;
import com.checkrise.blog.model.BlogEntry;
import com.checkrise.blog.model.BlogEntryDAO;
import com.checkrise.blog.model.InMemoryBlogEntryDAO;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import static spark.Spark.get;
import static spark.Spark.post;
import static spark.Spark.staticFileLocation;

/**
 * Created by Grzegorz Kończak on 15.11.2016.
 */
public class Blog {

    public static void main(String[] args) {
        staticFileLocation("/public");
        BlogEntryDAO dao = new InMemoryBlogEntryDAO();

        // Main page, shows all blog entries
        get("/", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            model.put("entries", dao.findAll());
            return new ModelAndView(model, "index.hbs");
        }, new HandlebarsTemplateEngine());

        // Handles adding new blog entry
        post("/", (request, response) -> {
            String title = request.queryParams("title");
            String entry = request.queryParams("entry");
            BlogEntry blogEntry = new BlogEntry(title, entry, LocalDateTime.now());
            dao.add(blogEntry);
            response.redirect("/");
            return null;
        });

        // shows details of chosen blog entry
        get("/detail/:slug", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            model.put("entry", dao.findBySlug(request.params("slug")));
            return new ModelAndView(model, "detail.hbs");
        }, new HandlebarsTemplateEngine());

        // displays form for adding new blog entry
        get("/new", (request, response) -> {
            return new ModelAndView(null, "new.hbs");
        }, new HandlebarsTemplateEngine());

        // displays form for editing blog entry
        get("/edit/:slug", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            model.put("entry", dao.findBySlug(request.params("slug")));
            return new ModelAndView(model, "edit.hbs");
        }, new HandlebarsTemplateEngine());

        // handles actual editing of blog entry
        post("/edit", (request, response) -> {
            String title = request.queryParams("title");
            String entry = request.queryParams("entry");
            String slug = request.queryParams("slug");
            BlogEntry blogEntry = dao.findBySlug(slug);
            blogEntry.setTitle(title);
            blogEntry.setEntry(entry);
            response.redirect("detail/" + slug);
            return null;
        });

        // handles adding comment to blog entry
        post("/add-comment", (request, response) -> {
            String name = request.queryParams("name");
            String comment = request.queryParams("comment");
            String slug = request.queryParams("slug");
            BlogEntry entry = dao.findBySlug(slug);
            entry.addComment(new BlogComment(name, comment, LocalDateTime.now()));
            response.redirect("/detail/" + slug);
            return null;
        });
    }
}
