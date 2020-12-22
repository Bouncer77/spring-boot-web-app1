# Course Progress Maestro

<!-- MarkdownTOC autolink="true" -->

- [Назначение приложения](#%D0%9D%D0%B0%D0%B7%D0%BD%D0%B0%D1%87%D0%B5%D0%BD%D0%B8%D0%B5-%D0%BF%D1%80%D0%B8%D0%BB%D0%BE%D0%B6%D0%B5%D0%BD%D0%B8%D1%8F)
- [Стэк технологий](#%D0%A1%D1%82%D1%8D%D0%BA-%D1%82%D0%B5%D1%85%D0%BD%D0%BE%D0%BB%D0%BE%D0%B3%D0%B8%D0%B9)
- [Как запустить приложение](#%D0%9A%D0%B0%D0%BA-%D0%B7%D0%B0%D0%BF%D1%83%D1%81%D1%82%D0%B8%D1%82%D1%8C-%D0%BF%D1%80%D0%B8%D0%BB%D0%BE%D0%B6%D0%B5%D0%BD%D0%B8%D0%B5)

<!-- /MarkdownTOC -->

## Назначение приложения

Онлайн сервис для учета прочитанных книг, сбора статистики по количеству прочитанных страниц, выдачи персональных рекомендаций.

Планируется реализовать возможность общения между пользователями текстовыми сообщениями внутри сервиса, возможность вывода лидеров,
возможность тестирования на определение действительно ли была прочитана книга или глава.

## Стэк технологий 

Full stack приложение с использованием стека технологий:

1. PostgresSQL в качестве РСУБД
2. JPA - взаимодействие с РСУБД PostgresSQL
3. Spring Boot 2 с MVC - поднимает веб сервер на tomcat
4. thymeleaf - шаблонизатор

Для тестирования RESTful через Postman требуется в исходнике WebSecurityConfig включить опции для Postman (см комментрарии)

## Как запустить приложение

1. Перед запуском приложения необходимо установить PostgresSQL
2. Создать базу данных с именем `bookreadermaestro_db` и схему в `jpa` в этой базе данных

```bash
psql -U postgres
  # пароль от пользователя postgres
```
```postgresql
CREATE DATABASE bookreadermaestro_db;
\c bookreadermaestro_db
CREATE SCHEMA jpa;
```

2. Ввести свой логин и пароль от PostgresSQL в файле `src/main/resources/application.properties`
3. Запустить проект (`SpringBootApp1Application.java`)
4. Зайти на сайт [Course Progress Maestro](http://localhost:8080/)