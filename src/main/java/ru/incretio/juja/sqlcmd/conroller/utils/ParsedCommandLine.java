package ru.incretio.juja.sqlcmd.conroller.utils;

import ru.incretio.juja.sqlcmd.exceptions.EmptyCommandException;

import java.util.*;

public class ParsedCommandLine {
    private final static String ESCAPE_STRING = "_&_";
    private final static char DELIMITER_QUOTE = '"';
    private final static int MIN_PARAMS_COUNT = 2;
    private final List<String> list;

    public ParsedCommandLine(String line) throws EmptyCommandException {
        if (line == null || line.trim().isEmpty()) {
            throw new EmptyCommandException();
        }
        this.list = parse(line.trim());
    }

    private List<String> parse(String line) {
        List<String> result = new ArrayList<>();

        // line = execute "SELECT * FROM 'table'"
        String newLine = replaceSpacesInQuotes(line, ESCAPE_STRING);
        // newLine = execute SELECT_&_*_&_FROM_&_'table'
        result.addAll(Arrays.asList(newLine.split("\\s+")));
        // result = [execute, SELECT_&_*_&_FROM_&_'table']
        result = replaceString(result, ESCAPE_STRING, " ");
        // result = [execute, SELECT * FROM 'table']

        return result;
    }

    private String replaceSpacesInQuotes(String sourceText, String escapeString) {
        Boolean inQuotes = false;
        StringBuilder result = new StringBuilder();

        for (int i = 0; i < sourceText.length(); i++) {
            if (sourceText.charAt(i) == DELIMITER_QUOTE) {
                inQuotes = !inQuotes;
            } else if (sourceText.charAt(i) == ' ' && inQuotes) {
                result.append(escapeString);
            } else {
                result.append(sourceText.charAt(i));
            }
        }
        return result.toString();
    }

    private List<String> replaceString(List<String> list, String s, String s1) {
        List<String> result = new ArrayList<>(list);

        for (int i = 0; i < result.size(); i++) {
            result.set(i, result.get(i).replace(s, s1));
        }

        return result;
    }

    public String getCommandName() {
        return list.get(0);
    }

    public List<String> getParamsList() {
        if (list.size() < MIN_PARAMS_COUNT) {
            return Collections.emptyList();
        }
        return list.subList(1, list.size());
    }
}




