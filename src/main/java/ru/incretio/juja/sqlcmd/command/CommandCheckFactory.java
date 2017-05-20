package ru.incretio.juja.sqlcmd.command;

import ru.incretio.juja.sqlcmd.command.interfaces.Checkable;

public class CommandCheckFactory {
    private final CheckParamsHelper checkParamsHelper = new CheckParamsHelper();

    public Checkable getClearCommandCheck() {
        return (params) -> checkParamsHelper.hasOneParams(params);
    }

    public Checkable getCloseCommandCheck() {
        return (params) -> checkParamsHelper.hasZeroParams(params);
    }

    public Checkable getColumnExistCommandCheck() {
        return (params) -> checkParamsHelper.hasTwoParams(params);
    }

    public Checkable getColumnsCommandCheck() {
        return (params) -> checkParamsHelper.hasOneParams(params);
    }

    public Checkable getConnectCommandCheck() {
        return (params) -> checkParamsHelper.hasFourParams(params);
    }

    public Checkable getCreateCommandCheck() {
        int firstCharInd = 0;
        return params -> {
            if (checkParamsHelper.isNull(params)) {
                return false;
            }
            boolean hasErrorInColumnName =
                    params.stream()
                            .anyMatch(s -> Character.isDigit(s.charAt(firstCharInd)));
            return checkParamsHelper.moreThanXParams(params, 1) && !hasErrorInColumnName;
        };
    }

    public Checkable getCreateDBCommandCheck() {
        return (params) -> checkParamsHelper.hasOneParams(params);
    }

    public Checkable getDeleteCommandCheck() {
        return (params) -> checkParamsHelper.hasThreeParams(params);
    }

    public Checkable getDropCommandCheck() {
        return (params) -> checkParamsHelper.hasOneParams(params);
    }

    public Checkable getDropDBCommandCheck() {
        return (params) -> checkParamsHelper.hasOneParams(params);
    }

    public Checkable getExecuteCommandCheck() {
        return (params) -> checkParamsHelper.hasOneParams(params);
    }

    public Checkable getExitCommandCheck() {
        return (params) -> checkParamsHelper.hasZeroParams(params);
    }

    public Checkable getFindCommandCheck() {
        return (params) -> checkParamsHelper.hasOneParams(params);
    }

    public Checkable getHelpCommandCheck() {
        return (params) -> checkParamsHelper.hasZeroParams(params);
    }

    public Checkable getInsertCommandCheck() {
        return params -> checkParamsHelper.moreThanXParams(params, 2) && checkParamsHelper.hasOddParams(params);
    }

    public Checkable getTableExistCommandCheck() {
        return (params) -> checkParamsHelper.hasOneParams(params);
    }

    public Checkable getTablesCommandCheck() {
        return (params) -> checkParamsHelper.hasZeroParams(params);
    }

    public Checkable getUpdateCommandCheck() {
        return (params) -> checkParamsHelper.hasFiveParams(params);
    }

}
