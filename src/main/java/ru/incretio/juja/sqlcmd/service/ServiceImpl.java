package ru.incretio.juja.sqlcmd.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;
import ru.incretio.juja.sqlcmd.model.Model;
import ru.incretio.juja.sqlcmd.model.entity.UserAction;
import ru.incretio.juja.sqlcmd.model.UserActionsRepository;
import ru.incretio.juja.sqlcmd.service.exceptions.ServiceException;

import java.util.Arrays;
import java.util.List;

import static ru.incretio.juja.sqlcmd.utils.ResourcesLoader.takeCaption;

@Repository
@Scope(value = "prototype")
public class ServiceImpl implements Service {

    @Autowired
    private final Model model;
    @Autowired
    private UserActionsRepository userActionsRepository;

    public ServiceImpl(Model model) {
        this.model = model;
    }

    @Override
    public List<String> commandsList() {
        return Arrays.asList(
                "connect", "closeConnection",
                "createTable", "dropTable",
                "takeTablesList",
                "insert", "update", "delete", "clear",
                "help",
                "actions");
    }

    @Override
    public String connect(String serverName, String dbName, String userName, String password) throws ServiceException {
        String result;
        try {
            try {
                model.connect(serverName, dbName, userName, password);
                doLog("connect");
                result = String.format(takeCaption("connectionSuccessPattern"), dbName);
            } catch (ClassNotFoundException e) {
                throw new ClassNotFoundException(takeCaption("driverLoadingErrorText"));
            }
        } catch (Exception e) {
            throw new ServiceException("Connect error", e);
        }

        return result;
    }

    @Override
    public String closeConnection() throws ServiceException {
        try {
            if (model.isConnected()) {
                doLog("closeConnection");
            }
            model.closeConnection();
            return takeCaption("connectionClosed");
        } catch (Exception e) {
            throw new ServiceException("Close connection error", e);
        }
    }

    @Override
    public void createDB(String dbName) throws ServiceException {
        try {
            model.executeCreateDB(dbName);
            doLog("createDB");
        } catch (Exception e) {
            throw new ServiceException("Create database error", e);
        }
    }

    @Override
    public void dropDB(String dbName) throws ServiceException {
        try {
            model.executeDropDB(dbName);
            doLog("dropDB");
        } catch (Exception e) {
            throw new ServiceException("Drop database error", e);
        }
    }

    @Override
    public List<String> takeTablesList() throws ServiceException {
        try {
            List<String> result = model.takeTablesList();
            doLog("takeTableList");
            return result;
        } catch (Exception e) {
            throw new ServiceException("Get tables actions error", e);
        }
    }

    @Override
    public String createTable(String tableName, List<String> columns) throws ServiceException {
        try {
            if (tableExists(tableName)) {
                return String.format(takeCaption("tableAlreadyExistsPattern"), tableName);
            } else {
                model.executeCreateTable(tableName, columns);
                doLog("createTable");
                return String.format(takeCaption("tableAddedPattern"), tableName);
            }
        } catch (Exception e) {
            throw new ServiceException("Create table error", e);
        }
    }

    private boolean tableExists(String tableName) throws Exception {
        List<String> tables = model.takeTablesList();
        return tables.contains(tableName);
    }

    @Override
    public String insert(String tableName, List<String> columns, List<String> values)
            throws ServiceException {
        try {
            // try table exists
            // try columns exist
            model.executeInsertRecord(tableName, columns, values);
            doLog("insert");
            return String.format(takeCaption("recordInsertedPattern"), tableName);
        } catch (Exception e) {
            throw new ServiceException("Insert error", e);
        }
    }

    @Override
    public String update(String tableName, String whereColumnName, String whereColumnValue, String setColumnName, String setColumnValue)
            throws ServiceException {
        try {
            // try table exists
            // try columns exist
            model.executeUpdateRecords(tableName, whereColumnName, whereColumnValue, setColumnName, setColumnValue);
            doLog("update");
            return String.format(takeCaption("recordUpdated"), tableName);
        } catch (Exception e) {
            throw new ServiceException("Update error", e);
        }
    }

    @Override
    public String delete(String tableName, String whereColumnName, String whereColumnValue)
            throws ServiceException {
        try {
            // try table exists
            // try columns exist
            model.executeDeleteRecords(tableName, whereColumnName, whereColumnValue);
            doLog("delete");
            return String.format(takeCaption("recordDeletedPattern"), tableName);
        } catch (Exception e) {
            throw new ServiceException("Delete error", e);
        }
    }

    @Override
    public List<List<String>> select(String tableName) throws ServiceException {
        try {
            // try table exists
            List<List<String>> data = model.find(tableName);
            doLog("select");
            return data;
        } catch (Exception e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    @Override
    public String clear(String tableName) throws ServiceException {
        try {
            // try table exists
            model.executeClearTable(tableName);
            doLog("clear");
            return String.format(takeCaption("tableCleared"), tableName);
        } catch (Exception e) {
            throw new ServiceException("Clear table error", e);
        }
    }

    @Override
    public String dropTable(String tableName) throws ServiceException {
        try {
            // try table exists
            model.executeDropTable(tableName);
            doLog("dropTable");
            return String.format(takeCaption("tableDeletedPattern"), tableName);
        } catch (Exception e) {
            throw new ServiceException("Drop table error", e);
        }
    }

    @Override
    public String help() {
        doLog("help");
        return "It's enjoy project.";
    }

    private boolean columnExists(String tableName, String columnName) throws ServiceException {
        try {
            // try table exists
            List<String> columns = model.takeTableColumns(tableName);
            return columns.contains(columnName);
        } catch (Exception e) {
            throw new ServiceException("Column exists error", e);
        }
    }

    public String createDatabase(String dbName) throws ServiceException {
        try {
            model.executeCreateDB(dbName);
            doLog("createDatabase");
            return String.format(takeCaption("dbCreatedPattern"), dbName);
        } catch (Exception e) {
            throw new ServiceException("Column exists error", e);
        }
    }

    public String dropDatabase(String dbName) throws ServiceException {
        try {
            model.executeDropDB(dbName);
            doLog("dropDataBase");
            return String.format(takeCaption("dbDeletedPattern"), dbName);
        } catch (Exception e) {
            throw new ServiceException("", e);
        }
    }

    @Override
    public List<UserAction> getAllFor(String userName) {
        if (userName == null) {
            throw new IllegalArgumentException("User name can't be null!");
        }
        return userActionsRepository.findByUserName(model.getUserName());
    }

    @Override
    public List<UserAction> getAllForCurrentUser() {
        String userName = model.getUserName();
        if (userName == null) {
            throw new IllegalArgumentException("User name can't be null!");
        }
        return userActionsRepository.findByUserName(userName);
    }

    private void doLog(String actionName) {
        userActionsRepository.save(
                new UserAction(model.getUserName(), model.getDbName(), actionName));
    }

}
