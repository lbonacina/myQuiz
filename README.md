What is it?
-----------

myQuiz is a simple application for online quiz, based on the myApp skeleton

    - authentication / authorization with Apache Shiro
    - User/Role management interface

uses :

    - Primefaces & Omnifaces as JSF lib
    - Myfaces CODI as CDI extension for ViewAccessScope
    - Apache Shiro (integrated with JPA for Realms & Infinispan for permissions caching)
    - Spring Data JPA for repository (DAO) layer
    - querydsl as an alternative to JPA criteria queries (integrate with Spring Data JPA)
    - Stateless EJB for Service/Transaction layer
    - Arquillian + Drone + Selenium for Webapp test (WIP)