package com.checkrise.blog;

import com.checkrise.blog.model.BlogEntryDAO;
import com.checkrise.blog.model.InMemoryBlogEntryDAO;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

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
            return new ModelAndView(null, "index.hbs");
        }, new HandlebarsTemplateEngine());
    }
}
