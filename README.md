## Book Registry System

### Используемые стэк технологий:

* JDK 18;
* Maven;
* Spring Core;
* Spring MVC (web-функционал);
* Spring DATA Jdbc (работа с БД);
* PostgreSQL.

### Описание:

* Система библиотечного учета читателей и книг, с помощью которой можно создавать, добавлять и удалять читателей и
  книги;
* На странице каждого читателя предоставлена информацию о взятых им книгах;
* На странице книги имеется возможность назначить ее любому из зарегистрированных читателей или освободить ее;
* Для создания или редактирования информации о читателе или книге должны выполняться предъвляемые требования (Уникальное
  имя читателя, верный возраст, допустимое название книги, год издания и т.д.);

![](materials/images/welcome.PNG)

Рисунок 1 - Приветственная страница

![](materials/images/emptyPeople.PNG)

Рисунок 2 - Пустой список читателей

![](materials/images/createPerson.PNG)

Рисунок 3 - Форма создания читателя 

![](materials/images/peopleList.PNG)

Рисунок 4 - Список читателей после добавления в реестр

![](materials/images/booksList.PNG)

Рисунок 5 - Список книг после добавления в реестр

![](materials/images/bookChoose.PNG)

Рисунок 6 - Страница книги, когда она никем не взята (с возможностью выбора читателя из списка зарегистрированных)

![](materials/images/bookTaken.PNG)

Рисунок 7 - Страница книги, взятой читателем

![](materials/images/personNoBook.PNG)

Рисунок 8 - Страница читателя до взятия книги 

![](materials/images/afterBookTaken.PNG)

Рисунок 9 - Страница человека, когда ему была присвоена книга

![](materials/images/incorrectBookEdit.PNG)

Рисунок 10 - Предупреждение на странице редактирования информации о книге после ввода неверных данных  

### Условия для запуска:

* Для запуска требуется веб-сервер Tomcat версии 9.0.73 или более ранняя версия;
* Настройки для соединения с БД находятся в файле ```src/main/resources/database.properties```;
* Для создания таблиц можно воспользоваться SQL-скриптами из ```materials/fillDatabase.sql```;

### Запуск:

* В Intellij IDEA: Run -> Edit Configurations -> выбрать Tomcat Local:

1) Настроить сервер (указать путь до папки с Tomcat);
2) Во вкладке `Deployment` добавить war-exploded архив, а также указать пустой графу Application Context. После этого
   можно запускать сервер через Intellij IDEA;
3) По адресу ```http://www.localhost:8080/``` будет находиться приветственная страница проиложения

