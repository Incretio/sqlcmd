package ru.incretio.juja.sqlcmd.model;

import java.util.List;

public interface UserActionsDAO {
    void log(String userName, String dbName, String action);
    List<UserAction> getAllFor(String userName);
}
