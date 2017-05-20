package ru.incretio.juja.sqlcmd.command;

import java.util.List;

public class CheckParamsHelper {
    protected boolean isNull(Object object) {
        return object == null;
    }

    protected boolean isNotNull(Object object) {
        return object != null;
    }

    protected boolean hasZeroParams(List params) {
        return hasXCountParams(params, 0);
    }

    protected boolean hasOneParams(List params) {
        return hasXCountParams(params, 1);
    }

    protected boolean hasTwoParams(List params) {
        return hasXCountParams(params, 2);
    }

    protected boolean hasThreeParams(List params) {
        return hasXCountParams(params, 3);
    }

    protected boolean hasFourParams(List params) {
        return hasXCountParams(params, 4);
    }

    protected boolean hasFiveParams(List params) {
        return hasXCountParams(params, 5);
    }

    protected boolean hasXCountParams(List params, int correctParamsCount) {
        return isNotNull(params) && (params.size() == correctParamsCount);
    }

    protected boolean moreThanXParams(List params, int x) {
        return isNotNull(params) && params.size() > x;
    }

    protected boolean hasOddParams(List params) {
        return isNotNull(params) && (params.size() % 2 == 1);
    }
}
