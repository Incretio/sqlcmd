package ru.incretio.juja.sqlcmd.conroller.command.list;

import ru.incretio.juja.sqlcmd.conroller.command.interfaces.Checkable;
import ru.incretio.juja.sqlcmd.conroller.command.interfaces.Notationable;
import ru.incretio.juja.sqlcmd.conroller.command.list.utils.MissingTableHelper;
import ru.incretio.juja.sqlcmd.conroller.command.list.utils.ResultSetTableFormatter;
import ru.incretio.juja.sqlcmd.model.Model;
import ru.incretio.juja.sqlcmd.utils.logger.AppLogger;
import ru.incretio.juja.sqlcmd.view.TableFormatter;
import ru.incretio.juja.sqlcmd.view.View;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

import static ru.incretio.juja.sqlcmd.utils.ResourcesLoader.takeCaption;

public class SelectTable extends Base {

    public SelectTable(Checkable checkable, Notationable notationable) {
        super(checkable, notationable);
    }

    @Override
    public void perform(Model model, View view, List<String> params) throws Exception {
        int tableNameInd = 0;
        String tableName = params.get(tableNameInd);

        new MissingTableHelper(model, view)
                .throwExceptionIfTableNotExist(tableName);

        ResultSetTableFormatter resultSetTableFormatter = new ResultSetTableFormatter();

        Consumer<ResultSet> resultSetConsumer = resultSet -> {
            try {
                resultSetTableFormatter.setResultSet(resultSet);
            } catch (SQLException e) {
                AppLogger.warning(e);
            }
        };

        model.find(resultSetConsumer, tableName);

        TableFormatter tableFormatter = new TableFormatter(
                resultSetTableFormatter.getData(),
                resultSetTableFormatter.getColumnsNames());

        String result = tableFormatter.getFormattedTable();

        if (result.trim().isEmpty()) {
            result = String.format(takeCaption("tableEmptyPattern"), tableName);
        }

        view.write(result);
    }
}
