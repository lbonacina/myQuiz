What is it?
-----------

myQuiz is a simple application for online quiz, based on the myApp skeleton

    - authentication / authorization with Apache Shiro
    - User/Role management interface


TODO : ideas

    - quick ideas:

        - use a single dummy session for all guests (Guest Session, retrieved by name or by fixed id = 1)
          which is already/always open (date 2000-2099)

        - define a method on QuizRunner to record the answers using varargs to support 1 answer or multiple
          based on question type (must convert list to array if multi)

    - implements the concept of Session to associate Users with Quiz and generate QuizSubmission
    - Session could trace also % of completion of users, aggregate scores and stats
    - generate the result of the test as HTML file for the user to review
    - quiz should be executed without access to the side menu, structure the templates with and without menu (with decoration ?)

uses :

    - Primefaces & Omnifaces as JSF lib
    - Myfaces CODI as CDI extension for ViewAccessScope
    - Apache Shiro (integrated with JPA for Realms & Infinispan for permissions caching)
    - Spring Data JPA for repository (DAO) layer
    - querydsl as an alternative to JPA criteria queries (integrate with Spring Data JPA)
    - Stateless EJB for Service/Transaction layer
    - Arquillian + Drone + Selenium for Webapp test (WIP)