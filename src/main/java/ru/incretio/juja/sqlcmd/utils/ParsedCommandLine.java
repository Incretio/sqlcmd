package ru.incretio.juja.sqlcmd.utils;

import ru.incretio.juja.sqlcmd.exceptions.commandexception.EmptyCommandException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Created by incre on 18.02.2017.
 */
public class ParsedCommandLine {
    private final List<String> list;

    public ParsedCommandLine(String line) throws EmptyCommandException {
        this.list = parse(line);
    }

    public String getCommandName() {
        return (list == null || list.size() == 0) ? "" : list.get(0).toUpperCase();
    }

    public List<String> getParamsList() {
        if (list == null || list.size() < 2) {
            return Collections.emptyList();
        }

        return list.subList(1, list.size());
    }

    public List<String> parse(String line) throws EmptyCommandException {
        if (line == null || line.length() == 0) {
            throw new EmptyCommandException();
        }

        List<String> result = new ArrayList<>();
        result.addAll(Arrays.asList(line.split(" ")));

        return result;
    }

    public String getValue(int index){
        return list.get(index);
    }


}
