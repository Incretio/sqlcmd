package ru.incretio.juja.sqlcmd.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import ru.incretio.juja.sqlcmd.conroller.command.list.utils.MissingTableHelper;
import ru.incretio.juja.sqlcmd.model.ResultSetTableFormatter;
import ru.incretio.juja.sqlcmd.conroller.utils.HelpCommand;
import ru.incretio.juja.sqlcmd.exceptions.CommandException;
import ru.incretio.juja.sqlcmd.exceptions.MissingAnyDataException;
import ru.incretio.juja.sqlcmd.exceptions.NeedExitException;
import ru.incretio.juja.sqlcmd.model.Model;
import ru.incretio.juja.sqlcmd.utils.logger.AppLogger;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.function.Consumer;

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
            throw new ServiceException("Get tables list error", e);
        }
    }

    @Override
    public void createTable(String tableName, List<String> columns) throws ServiceException {
        try {
            model.executeCreateTable(tableName, columns);
        } catch (Exception e) {
            throw new ServiceException("Create table error", e);
        }
    }

    @Override
    public void insert(String tableName, List<String> columns, List<String> values)
            throws ServiceException {
        try {
            model.executeInsertRecord(tableName, columns, values);
        } catch (Exception e) {
            throw new ServiceException("Insert error", e);
        }
    }

    @Override
    public void update(String tableName, String whereColumnName, String whereColumnValue, String setColumnName, String setColumnValue)
            throws ServiceException {
        try {
            model.executeUpdateRecords(tableName, whereColumnName, whereColumnValue, setColumnName, setColumnValue);
        } catch (Exception e) {
            throw new ServiceException("Update error", e);
        }
    }

    @Override
    public void delete(String tableName, String whereColumnName, String whereColumnValue)
            throws ServiceException {
        try {
            model.executeDeleteRecords(tableName, whereColumnName, whereColumnValue);
        } catch (Exception e) {
            throw new ServiceException("Delete error", e);
        }
    }

    @Override
    public List<List<String>> select(String tableName) throws ServiceException {
        try {
//            new MissingTableHelper(model)
//                    .throwExceptionIfTableNotExist(tableName);

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
    public void clear(String tableName) throws ServiceException {
        try {
            model.executeClearTable(tableName);
        } catch (Exception e) {
            throw new ServiceException("Clear table error", e);
        }
    }

    @Override
    public void dropTable(String tableName) throws ServiceException {
        try {
            model.executeDropTable(tableName);
        } catch (Exception e) {
            throw new ServiceException("Drop table error", e);
        }
    }

    @Override
    public HelpCommand getHelp() {
        return new HelpCommand();
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

}
