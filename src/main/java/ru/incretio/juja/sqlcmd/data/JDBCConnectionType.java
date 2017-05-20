package ru.incretio.juja.sqlcmd.data;

public enum JDBCConnectionType {
    PostgreSQL("postgresql", "org.postgresql.Driver"),
    MySQL("mysql", "com.mysql.jdbc.Driver"),
    MSSQL("sqlserver", "com.microsoft.sqlserver.jdbc.SQLServerDriver");

    private final String jdbcUrl;
    private final String jdbcDriverName;

    JDBCConnectionType(String jdbcUrl, String jdbcDriverName) {
        this.jdbcUrl = jdbcUrl;
        this.jdbcDriverName = jdbcDriverName;
    }

    public String getJdbcUrl() {
        return jdbcUrl;
    }

    public String getJdbcDriverName() {
        return jdbcDriverName;
    }

}
