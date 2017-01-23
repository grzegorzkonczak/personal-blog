## Personal Blog with Spark Framework
Simple web blog application built using Spark Framework.

Project initial files (like html mockups and initial css styling) where provided by project idea creators (https://teamtreehouse.com).

Project was meant to follow a set of requirements.
Base requirements were as follows:

1. Index page shows 3 blog entries with titles, date/time created
2. Detail page shows: title, date, body, comments
3. Add/Edit page enables the user to post new entries or edit existing entries
4. Password page is displayed each time user clicks on add or edit link
5. Anonymous user is able to post comments
6. Model should be designed correctly: Blog has list of entries, Entry has list of comments
7. A DAO interface and implementation should be present for blog entries. Implementation allows to add and retrive bog entries
8. Styling implemented with separate CSS file, fonts have colors and faces, header fonts are larger than body fonts
9. All routes should be mapped correctly and use correct HTTP methods:
  - index page mapped as "/"
  - details page mapped as "/blog/{id}" or similar

Additional requirements (also implemented):

1. Blog entries show tags
2. Edit page includes delete button
3. Password not required if cookie is valid
4. The DAO implementation allows for deletion of blog entries
5. Routing uses slugs

To check my other work please go to:

- https://github.com/grzegorzkonczak/instateam-with-spring-and-hibernate - Project team management web application using Spring with Hibernate.
- https://github.com/grzegorzkonczak/todo-api-with-spark - REST API for "TODO" application using Spark framework
- https://github.com/grzegorzkonczak/analyze-public-data-with-hibernate - Console application for managing Countries data using Hibernate and H2 file database. Edit
- https://github.com/grzegorzkonczak/countries-of-the-world-with-spring - Spring web application that displays information about 5 countries
- https://github.com/grzegorzkonczak/Soccer-League-Organizer - Console based soccer team management application
- https://github.com/grzegorzkonczak/how_many_in_jar_game - Console based implementation of "How many in jar" game
