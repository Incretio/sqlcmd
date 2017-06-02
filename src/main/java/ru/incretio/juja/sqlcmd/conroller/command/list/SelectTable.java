package ru.incretio.juja.sqlcmd.conroller.command.list;

import ru.incretio.juja.sqlcmd.conroller.command.interfaces.Checkable;
import ru.incretio.juja.sqlcmd.conroller.command.interfaces.Notationable;
import ru.incretio.juja.sqlcmd.conroller.command.list.utils.MissingTableHelper;
import ru.incretio.juja.sqlcmd.conroller.command.list.utils.ResultSetTableFormatter;
import ru.incretio.juja.sqlcmd.model.Model;
import ru.incretio.juja.sqlcmd.utils.logger.AppLogger;
import ru.incretio.juja.sqlcmd.view.TableFormatter;
import ru.incretio.juja.sqlcmd.view.View;

import javax.annotation.Resource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

import static ru.incretio.juja.sqlcmd.utils.ResourcesLoader.takeCaption;

public class SelectTable extends Base {

    private ResultSetTableFormatter resultSetTableFormatter;

    public SelectTable(Checkable checkable, Notationable notationable, ResultSetTableFormatter resultSetTableFormatter) {
        this(checkable, notationable);
        this.resultSetTableFormatter = resultSetTableFormatter;
    }

    public SelectTable(Checkable checkable, Notationable notationable) {
        super(checkable, notationable);
    }

    @Override
    public void perform(Model model, View view, List<String> params) throws Exception {
        int tableNameInd = 0;

        String tableName = params.get(tableNameInd);
        new MissingTableHelper(model, view)
                .throwExceptionIfTableNotExist(tableName);

        Consumer<ResultSet> resultSetConsumer = resultSet -> {
            try {
                if (resultSetTableFormatter == null) {
                    resultSetTableFormatter = new ResultSetTableFormatter();
                    resultSetTableFormatter.setResultSet(resultSet);
                }
            } catch (SQLException e) {
                AppLogger.warning(e);
            }
        };

        model.find(resultSetConsumer, tableName);

        if ((resultSetTableFormatter != null) &&
                (resultSetTableFormatter.getColumnsNames() != null) &&
                (!resultSetTableFormatter.getColumnsNames().isEmpty())) {
            List<List<String>> data = resultSetTableFormatter.getData();
            List<String> columnsNames = resultSetTableFormatter.getColumnsNames();
            view.writeSelectTable(data, columnsNames);
        } else {
            view.write(String.format(takeCaption("tableEmptyPattern"), tableName));
        }
    }
}
