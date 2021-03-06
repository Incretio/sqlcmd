package ru.incretio.juja.sqlcmd;

public class TestConstants {
    public final static String TEST_DB_NAME = "testdb_a5d8e6";
    public final static String SERVER_NAME = "localhost";
    public final static String MASTER_DB = "postgres";
    public final static String USER_NAME = "postgres";
    public final static String PASSWORD = "postgres";
    public final static String MASTER_CONNECTION_STRING =
            String.format("connect %s %s %s %s",
                    SERVER_NAME, MASTER_DB, USER_NAME, PASSWORD);
    public final static String TEST_CONNECTION_STRING =
            String.format("connect %s %s %s %s",
                    SERVER_NAME, TEST_DB_NAME, USER_NAME, PASSWORD);
}
