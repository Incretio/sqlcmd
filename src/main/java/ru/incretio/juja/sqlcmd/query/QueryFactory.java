package ru.incretio.juja.sqlcmd.query;

import ru.incretio.juja.sqlcmd.data.JDBCConnectionType;
import ru.incretio.juja.sqlcmd.exceptions.MissingJDBCConnectionTypeException;

public class QueryFactory {
    public static Queryable makeSQLQuery(JDBCConnectionType jdbcConnectionType) throws MissingJDBCConnectionTypeException {
        switch (jdbcConnectionType) {
            case PostgreSQL:
                return new PostgreSQLQuery();
            case MySQL:
                return new MySQLQuery();
            case MSSQL:
                return new MSSQLQuery();
            default:
                throw new MissingJDBCConnectionTypeException(jdbcConnectionType.toString());
        }

    }
}
