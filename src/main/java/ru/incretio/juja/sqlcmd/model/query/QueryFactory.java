package ru.incretio.juja.sqlcmd.model.query;

public class QueryFactory {
    public static Queryable makePostgresQLQuery() {
        return new PostgreSQLQuery();

    }
}
