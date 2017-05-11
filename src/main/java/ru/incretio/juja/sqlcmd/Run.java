package ru.incretio.juja.sqlcmd;

import ru.incretio.juja.sqlcmd.query.Querable;
import ru.incretio.juja.sqlcmd.query.QueryFactory;
import ru.incretio.juja.sqlcmd.view.ConsoleView;
import ru.incretio.juja.sqlcmd.view.View;

class Run {
    public static void main(String[] args) {
        View view = new ConsoleView();
        Querable querable = QueryFactory.makePostgreSQLQuery();
        ConnectionConfig connectionConfig = new ConnectionConfig(querable);

        Main main = new Main(view, connectionConfig);
        main.doMainWork();
    }
}
