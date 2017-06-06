package ru.incretio.juja.sqlcmd.model.query;

public class QueryFactory {
    public static Queryable makePostgreSQLQuery() {
        return new PostgreSQLQuery();

    }
}
