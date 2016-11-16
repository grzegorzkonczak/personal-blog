package com.checkrise.blog;

import com.checkrise.blog.model.BlogComment;
import com.checkrise.blog.model.BlogEntry;
import com.checkrise.blog.model.BlogEntryDAO;
import com.checkrise.blog.model.InMemoryBlogEntryDAO;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import static spark.Spark.*;

/**
 * Created by Grzegorz Kończak on 15.11.2016.
 */
public class Blog {

    public static void main(String[] args) {
        staticFileLocation("/public");
        BlogEntryDAO dao = new InMemoryBlogEntryDAO();

        // Checks if cookie with password is present, then sets cookie as request attribute
        // Not dependant on cookie implementation for user authentication (could be swapped relatively easy)
        before((request, response) -> {
            if (request.cookie("password") != null){
                request.attribute("password", request.cookie("password"));
            }
        });

        // Redirects not authenticated user to password page with attribute informing
        // about further destination
        before("/edit/:slug", (request, response) -> {
            if (request.attribute("password") == null || !request.attribute("password").equals(InMemoryBlogEntryDAO.PASSWORD)){
                request.session().attribute("destination", "/edit/" + request.params("slug"));
                response.redirect("/password");
                halt();
            }
        });

        // Redirects not authenticated user to password page with attribute informing
        // about further destination
        before("/new", (request, response) -> {
            if (request.attribute("password") == null || !request.attribute("password").equals(InMemoryBlogEntryDAO.PASSWORD)){
                request.session().attribute("destination", "/new");
                response.redirect("/password");
                halt();
            }
        });


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
            String[] tags = request.queryParams("tags").trim().split(" +");
            BlogEntry blogEntry = new BlogEntry(title, entry, LocalDateTime.now());
            // Checks for empty tags list
            if (tags.length > 1 || !tags[0].equals("")) {
                blogEntry.setTags(Arrays.asList(tags));
            }
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
            return new ModelAndView(null, "add-edit.hbs");
        }, new HandlebarsTemplateEngine());

        // displays form for editing blog entry
        get("/edit/:slug", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            model.put("entry", dao.findBySlug(request.params("slug")));
            return new ModelAndView(model, "add-edit.hbs");
        }, new HandlebarsTemplateEngine());

        // handles actual editing of blog entry
        post("/edit", (request, response) -> {
            String title = request.queryParams("title");
            String entry = request.queryParams("entry");
            String slug = request.queryParams("slug");
            String[] tags = request.queryParams("tags").trim().split(" +");
            BlogEntry blogEntry = dao.findBySlug(slug);
            blogEntry.setTitle(title);
            blogEntry.setEntry(entry);
            // Checks for empty tags list
            if (tags.length > 1 || !tags[0].equals("")) {
                blogEntry.setTags(Arrays.asList(tags));
            } else {
                blogEntry.clearTags();
            }
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

        // displays form for entering password
        get("/password", (request, response) -> {
            return new ModelAndView(null, "password.hbs");
        }, new HandlebarsTemplateEngine());

        // handles user authentication. If proper password sets up cookie
        post("/password", (request, response) -> {
            String password = request.queryParams("password");
            if (!password.equals(InMemoryBlogEntryDAO.PASSWORD)){
                response.redirect("/");
            }
            response.cookie("password", password);
            response.redirect(request.session().attribute("destination"));
            return null;
        });

        // removes blog entry
        get("/remove/:slug", (request, response) -> {
            dao.remove(dao.findBySlug(request.params("slug")));
            response.redirect("/");
            return null;
        });
    }
}
