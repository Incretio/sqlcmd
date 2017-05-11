package ru.incretio.juja.sqlcmd.query;

import ru.incretio.juja.sqlcmd.data.JDBCConnectionType;

public class QueryFactory {
    public static Queryable makePostgreSQLQuery(){
        return new PostgreSQLQuery(JDBCConnectionType.PostgreSQL);
    }
}
