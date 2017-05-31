package ru.incretio.juja.sqlcmd.model.query;

import ru.incretio.juja.sqlcmd.model.data.JDBCConnectionType;
import ru.incretio.juja.sqlcmd.exceptions.MissingJDBCConnectionTypeException;

public class QueryFactory {
    public static Queryable makeSQLQuery(JDBCConnectionType jdbcConnectionType) throws MissingJDBCConnectionTypeException {
        switch (jdbcConnectionType) {
            case PostgreSQL:
                return new PostgreSQLQuery();
            case MySQL:
                // ToDo: return new MySQLQuery();
            case MSSQL:
                // ToDo: return new MSSQLQuery();
            default:
                throw new MissingJDBCConnectionTypeException(jdbcConnectionType.toString());
        }

    }
}
