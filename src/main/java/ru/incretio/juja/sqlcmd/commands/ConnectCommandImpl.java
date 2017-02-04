package ru.incretio.juja.sqlcmd.commands;

import java.util.List;

public class ConnectCommandImpl implements Command {

    private List<String> params;

    @Override
    public String perform() {
        if (isCorrectCommand()) {
            return "Подключились к БД...";
        }
        return "Список параметров не совпадает!";
    }

    @Override
    public void setParams(List<String> params) {
        this.params = params;
    }

    @Override
    public boolean isCorrectCommand() {
        return params.size() == 3;
    }
}


    /*public static Connection connect(
            String dbHost, String dbName,
            String userName, String password) throws ClassNotFoundException, SQLException {
        Connection connection = JDBCConnectableFactory.makePostgreSQLConnection(dbHost, dbName).
                getConnection(userName, password);
        return connection;
    }

    @Override
    public void perform() {

    }
    */
