package com.checkrise.blog;

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

        get("/", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            model.put("entries", dao.findAll());
            return new ModelAndView(model, "index.hbs");
        }, new HandlebarsTemplateEngine());

        get("/detail/:slug", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            model.put("entry", dao.findBySlug(request.params("slug")));
            return new ModelAndView(model, "detail.hbs");
        }, new HandlebarsTemplateEngine());

        get("/new", (request, response) -> {
            return new ModelAndView(null, "new.hbs");
        }, new HandlebarsTemplateEngine());

        post("/", (request, response) -> {
            String title = request.queryParams("title");
            String entry = request.queryParams("entry");
            BlogEntry blogEntry = new BlogEntry(title, entry, LocalDateTime.now());
            dao.add(blogEntry);
            response.redirect("/");
            return null;
        });
    }
}
