package ru.incretio.juja.sqlcmd.command;

import ru.incretio.juja.sqlcmd.command.interfaces.Checkable;
import ru.incretio.juja.sqlcmd.command.utils.CheckParamsHelper;

abstract class CommandCheckFactory {
    private static final CheckParamsHelper checkParamsHelper = new CheckParamsHelper();

    public static Checkable makeClearCommandCheck() {
        return checkParamsHelper::hasOneParams;
    }

    public static Checkable makeCloseCommandCheck() {
        return checkParamsHelper::hasZeroParams;
    }

    public static Checkable makeColumnExistCommandCheck() {
        return checkParamsHelper::hasTwoParams;
    }

    public static Checkable makeColumnsCommandCheck() {
        return checkParamsHelper::hasOneParams;
    }

    public static Checkable makeConnectCheck() {
        return checkParamsHelper::hasFourParams;
    }

    public static Checkable makeCreateCommandCheck() {
        return params -> {
            if (checkParamsHelper.isNull(params)) {
                return false;
            }
            int firstCharInd = 0;
            boolean hasErrorInColumnName =
                    params.stream()
                            .anyMatch(s -> Character.isDigit(s.charAt(firstCharInd)));
            return checkParamsHelper.moreThanXParams(params, 1) && !hasErrorInColumnName;
        };
    }

    public static Checkable makeCreateDBCommandCheck() {
        return checkParamsHelper::hasOneParams;
    }

    public static Checkable makeDeleteCommandCheck() {
        return checkParamsHelper::hasThreeParams;
    }

    public static Checkable makeDropCommandCheck() {
        return checkParamsHelper::hasOneParams;
    }

    public static Checkable makeDropDBCommandCheck() {
        return checkParamsHelper::hasOneParams;
    }

    public static Checkable makeExecuteCommandCheck() {
        return checkParamsHelper::hasOneParams;
    }

    public static Checkable makeExitCommandCheck() {
        return checkParamsHelper::hasZeroParams;
    }

    public static Checkable makeFindCommandCheck() {
        return checkParamsHelper::hasOneParams;
    }

    public static Checkable makeHelpCommandCheck() {
        return checkParamsHelper::hasZeroParams;
    }

    public static Checkable makeInsertCommandCheck() {
        return params -> checkParamsHelper.moreThanXParams(params, 2) && checkParamsHelper.hasOddParams(params);
    }

    public static Checkable makeTableExistCommandCheck() {
        return checkParamsHelper::hasOneParams;
    }

    public static Checkable makeTablesCommandCheck() {
        return checkParamsHelper::hasZeroParams;
    }

    public static Checkable makeUpdateCommandCheck() {
        return checkParamsHelper::hasFiveParams;
    }

}
