package ru.incretio.juja.sqlcmd;

import ru.incretio.juja.sqlcmd.data.JDBCConnectionType;
import ru.incretio.juja.sqlcmd.exceptions.MissingJDBCConnectionTypeException;
import ru.incretio.juja.sqlcmd.query.Queryable;
import ru.incretio.juja.sqlcmd.query.QueryFactory;
import ru.incretio.juja.sqlcmd.view.ConsoleView;
import ru.incretio.juja.sqlcmd.view.View;

class Run {
    public static void main(String[] args) {
        View view = new ConsoleView();
        try {
            Queryable queryable = QueryFactory.makeSQLQuery(JDBCConnectionType.PostgreSQL);
            ConnectionConfig connectionConfig = new ConnectionConfig(queryable);

            Main main = new Main(view, connectionConfig);
            main.doMainWork();
        } catch (MissingJDBCConnectionTypeException e) {
            e.printStackTrace();
        }
    }
}
