/**
 * Created by incre on 03.03.2017.
 */
public class TestConstants {
    public final static String TEST_DB_NAME = "testDB_a5d8e6";
    public final static String SERVER_NAME = "localhost";
    public final static String MASTER_DB = "postgres";
    public final static String USER_NAME = "postgres";
    public final static String PASSWORD = "postgres";
    public final static String CONNECTION_STRING =
            String.format("connect %s %s %s %s",
                    SERVER_NAME, MASTER_DB, USER_NAME, PASSWORD);
}
