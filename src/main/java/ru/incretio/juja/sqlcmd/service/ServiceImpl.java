package ru.incretio.juja.sqlcmd.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import ru.incretio.juja.sqlcmd.exceptions.*;
import ru.incretio.juja.sqlcmd.model.Model;
import ru.incretio.juja.sqlcmd.model.ResultSetTableFormatter;
import ru.incretio.juja.sqlcmd.utils.logger.AppLogger;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static ru.incretio.juja.sqlcmd.utils.ResourcesLoader.takeCaption;

@Component
@Scope(value = "prototype")
public class ServiceImpl implements Service {

    @Autowired
    private Model model;

    public ServiceImpl(Model model) {
        this.model = model;
    }

    @Override
    public List<String> commandsList() {
        return Arrays.asList(
                "connect", "closeConnection",
                "takeTablesList", "createTable", "insert", "update", "delete", "select", "clear", "dropTable",
                "help");
    }

    @Override
    public String connect(String serverName, String dbName, String userName, String password) throws ServiceException {
        String result = "";
        try {
            try {
                model.connect(serverName, dbName, userName, password);
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
        } catch (Exception e) {
            throw new ServiceException("Create database error", e);
        }
    }

    @Override
    public void dropDB(String dbName) throws ServiceException {
        try {
            model.executeDropDB(dbName);
        } catch (Exception e) {
            throw new ServiceException("Drop database error", e);
        }
    }

    @Override
    public List<String> takeTablesList() throws ServiceException {
        try {
            return model.takeTablesList();
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
                return String.format(takeCaption("tableAddedPattern"), tableName);
            }
        } catch (Exception e) {
            throw new ServiceException("Create table error", e);
        }
    }

    @Override
    public String insert(String tableName, List<String> columns, List<String> values)
            throws ServiceException {
        try {
            // try table exists
            // try columns exist
            model.executeInsertRecord(tableName, columns, values);
            return String.format(takeCaption("recordInsertedPattern"), tableName);
        } catch (Exception e) {
            throw new ServiceException("Insert error", e);
        }
    }

    @Override
    public void update(String tableName, String whereColumnName, String whereColumnValue, String setColumnName, String setColumnValue)
            throws ServiceException {
        try {
            // try table exists
            // try columns exist
            model.executeUpdateRecords(tableName, whereColumnName, whereColumnValue, setColumnName, setColumnValue);
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
            return String.format(takeCaption("recordDeletedPattern"), tableName);
        } catch (Exception e) {
            throw new ServiceException("Delete error", e);
        }
    }

    @Override
    public List<List<String>> select(String tableName) throws ServiceException {
        try {
            // try table exists
            ResultSetTableFormatter resultSetTableFormatter = model.find(tableName);

            if (resultSetTableFormatter.getColumnsNames() != null &&
                    !resultSetTableFormatter.getColumnsNames().isEmpty()) {
                List<List<String>> table = resultSetTableFormatter.getData();
                List<String> columnsNames = resultSetTableFormatter.getColumnsNames();
                table.add(0, columnsNames);
                return table;
            }
        } catch (Exception e) {
            throw new ServiceException("Select error", e);
        }

        return Collections.emptyList();
    }

    @Override
    public String clear(String tableName) throws ServiceException {
        try {
            // try table exists
            model.executeClearTable(tableName);
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
            return String.format(takeCaption("tableDeletedPattern"), tableName);
        } catch (Exception e) {
            throw new ServiceException("Drop table error", e);
        }
    }

    private String getExceptionDescription(Exception exception) {
        String result;
        try {
            exception.printStackTrace();
            throw exception;
        } catch (CommandException | MissingAnyDataException | NeedExitException e) {
            result = e.getMessage();
        } catch (SQLException e) {
            result = String.format(takeCaption("sqlErrorPattern"),
                    e.getMessage().replace("\n", System.lineSeparator()));
        } catch (Exception e) {
            result = takeCaption("badAppConfiguration");
            AppLogger.warning(e.getMessage().concat(": ").concat(Arrays.toString(e.getStackTrace())));
        }
        return result;
    }

    private boolean columnExists(String tableName, String columnName) throws ServiceException {
        try {
            // try table exists
            List<String> columns = model.takeTableColumns(tableName);
            if (columns.contains(columnName)) {
                return true;
            } else {
                throw new MissingColumnException(columnName);
            }
        } catch (Exception e) {
            throw new ServiceException("Column exists error", e);
        }
    }

    private boolean tableExists(String tableName) throws ServiceException {
        try {
            List<String> tables = model.takeTablesList();
            if (tables.contains(tableName)) {
                return true;
            } else {
                throw new MissingTableException(tableName);
            }
        } catch (Exception e) {
            throw new ServiceException("Column exists error", e);
        }
    }

    public String createDatabase(String dbName) throws ServiceException {
        try {
            model.executeCreateDB(dbName);
            return String.format(takeCaption("dbCreatedPattern"), dbName);
        } catch (Exception e) {
            throw new ServiceException("Column exists error", e);
        }
    }

    public String dropDatabase(String dbName) throws ServiceException {
        try {
            model.executeDropDB(dbName);
            return String.format(takeCaption("dbDeletedPattern"), dbName);
        } catch (Exception e) {
            throw new ServiceException("", e);
        }
    }

}
