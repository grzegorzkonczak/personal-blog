package com.checkrise.blog;

import com.checkrise.blog.model.BlogEntryDAO;
import com.checkrise.blog.model.InMemoryBlogEntryDAO;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.util.HashMap;
import java.util.Map;

import static spark.Spark.get;
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
    }
}
