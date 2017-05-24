package ru.incretio.juja.sqlcmd.conroller.command.list.utils;

import java.util.List;

public class CheckParamsHelper {
    public boolean isNull(Object object) {
        return object == null;
    }

    private boolean isNotNull(Object object) {
        return object != null;
    }

    public boolean hasZeroParams(List params) {
        return hasXCountParams(params, 0);
    }

    public boolean hasOneParams(List params) {
        return hasXCountParams(params, 1);
    }

    public boolean hasTwoParams(List params) {
        return hasXCountParams(params, 2);
    }

    public boolean hasThreeParams(List params) {
        return hasXCountParams(params, 3);
    }

    public boolean hasFourParams(List params) {
        return hasXCountParams(params, 4);
    }

    public boolean hasFiveParams(List params) {
        return hasXCountParams(params, 5);
    }

    private boolean hasXCountParams(List params, int correctParamsCount) {
        return isNotNull(params) && (params.size() == correctParamsCount);
    }

    public boolean moreThanXParams(List params, int x) {
        return isNotNull(params) && params.size() > x;
    }

    public boolean hasOddParams(List params) {
        return isNotNull(params) && (params.size() % 2 == 1);
    }
}
