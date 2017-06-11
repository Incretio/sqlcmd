<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<html>
    <head>
        <meta charset="utf-8">
        <title>SQLCmd</title>
    </head>
    <body>
        Список доступных комманд:<br>
            connect serverName dbName username password:<br>
                подключиться к базе данных;<br>
            tables:<br>
                показать список таблиц базы данных;<br>
            create tableName column1 [column2] [columnN]:<br>
                добавить новую таблицу (имя столбца не может начинаться с цифры);<br>
            drop tableName:<br>
                удалить указанную таблицу;<br>
            insert tableName column1 value1 [column2 value2] [columnN valueN]:<br>
                добавить запись в указанную таблицу;<br>
            update tableName whereColumn whereValue setColumn setValue:<br>
                обновить записи, удовлетворяющие условию в указанной таблице;<br>
            find tableName:<br>
                показать содержимое указанной таблицы;<br>
            delete tableName whereColumn whereValue:<br>
                удалить записи, удовлетворяющие условию;<br>
            clear tableName:<br>
                очистить содержимое указанной таблицы;<br>
            close:<br>
                закрыть соединение с базой данных;<br>
            exit:<br>
                закрыть соединение и выйти из программы;<br>
            help:<br>
                показать список команд и их описаниями;<br>
            execute \"textQuery\":<br>
                выполнить пользовательский запрос (должен быть указан в двойных ковычках);<br>
        <br>
        Go to <a href="menu">Menu</a>.
    </body>
</html>