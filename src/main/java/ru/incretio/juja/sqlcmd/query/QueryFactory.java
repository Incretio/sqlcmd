package ru.incretio.juja.sqlcmd.query;

/**
 * Created by incre on 20.02.2017.
 */
public class QueryFactory {
    public static Querable makePostgreSQLQuery(){
        return new PostgreSQLQuery();
    }

}
