package ru.incretio.juja.sqlcmd;

import ru.incretio.juja.sqlcmd.conroller.Controller;
import ru.incretio.juja.sqlcmd.model.Model;
import ru.incretio.juja.sqlcmd.model.query.Queryable;
import ru.incretio.juja.sqlcmd.model.query.QueryFactory;
import ru.incretio.juja.sqlcmd.view.ConsoleView;
import ru.incretio.juja.sqlcmd.view.View;

class Main {
    public static void main(String[] args) {
        View view = new ConsoleView();
        Queryable queryable = QueryFactory.makePostgreSQLQuery();
        Model model = new Model(queryable);

        Controller main = new Controller(view, model);
        main.doMainWork();
    }
}
