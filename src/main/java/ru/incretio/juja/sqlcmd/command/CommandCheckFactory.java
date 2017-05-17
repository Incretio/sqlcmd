package ru.incretio.juja.sqlcmd.command;

import ru.incretio.juja.sqlcmd.command.interfaces.Checkable;
import java.util.List;

class CommandCheckFactory {
    public static Checkable getClearCommandCheck() {
        return params -> isNotNull(params) && hasOneParams(params);
    }

    public static Checkable getCloseCommandCheck() {
        return params -> isNotNull(params) && hasZeroParams(params);
    }

    public static Checkable getColumnExistCommandCheck() {
        return params -> isNotNull(params) && hasTwoParams(params);
    }

    public static Checkable getColumnsCommandCheck() {
        return params -> isNotNull(params) && hasOneParams(params);
    }

    public static Checkable getConnectCommandCheck() {
        return params -> isNotNull(params) && hasFourParams(params);
    }

    public static Checkable getCreateCommandCheck() {
        int firstCharInd = 0;
        return params -> {
            boolean hasErrorInColumnName = false;
            if (isNotNull(params)) {
                for (String param : params) {
                    if (Character.isDigit(param.charAt(firstCharInd))) {
                        hasErrorInColumnName = true;
                    }
                }
            }
            return isNotNull(params) && moreThanXParams(params, 1) && !hasErrorInColumnName;
        };
    }

    public static Checkable getCreateDBCommandCheck() {
        return params -> isNotNull(params) && hasOneParams(params);
    }

    public static Checkable getDeleteCommandCheck() {
        return params -> isNotNull(params) && hasThreeParams(params);
    }

    public static Checkable getDropCommandCheck() {
        return params -> isNotNull(params) && hasOneParams(params);
    }

    public static Checkable getDropDBCommandCheck() {
        return params -> isNotNull(params) && hasOneParams(params);
    }

    public static Checkable getExecuteCommandCheck() {
        return params -> isNotNull(params) && hasOneParams(params);
    }

    public static Checkable getExitCommandCheck() {
        return params -> isNotNull(params) && hasZeroParams(params);
    }

    public static Checkable getFindCommandCheck() {
        return params -> isNotNull(params) && hasOneParams(params);
    }

    public static Checkable getHelpCommandCheck() {
        return params -> isNotNull(params) && hasZeroParams(params);
    }

    public static Checkable getInsertCommandCheck() {
        return params -> isNotNull(params) &&
                moreThanXParams(params, 2) && hasOddParams(params);
    }

    public static Checkable getTableExistCommandCheck() {
        return params -> isNotNull(params) && hasOneParams(params);
    }

    public static Checkable getTablesCommandCheck() {
        return params -> isNotNull(params) && hasZeroParams(params);
    }

    public static Checkable getUpdateCommandCheck() {
        return params -> isNotNull(params) && hasFiveParams(params);
    }


    private static boolean isNotNull(Object object) {
        return object != null;
    }

    private static boolean hasZeroParams(List params) {
        return hasXCountParams(params, 0);
    }

    private static boolean hasOneParams(List params) {
        return hasXCountParams(params, 1);
    }

    private static boolean hasTwoParams(List params) {
        return hasXCountParams(params, 2);
    }

    private static boolean hasThreeParams(List params) {
        return hasXCountParams(params, 3);
    }

    private static boolean hasFourParams(List params) {
        return hasXCountParams(params, 4);
    }

    private static boolean hasFiveParams(List params) {
        return hasXCountParams(params, 5);
    }

    private static boolean hasXCountParams(List params, int correctParamsCount) {
        return params.size() == correctParamsCount;
    }

    private static boolean moreThanXParams(List params, int x) {
        return params.size() > x;
    }

    private static boolean hasOddParams(List params) {
        return (params.size() % 2 == 1);
    }

}
