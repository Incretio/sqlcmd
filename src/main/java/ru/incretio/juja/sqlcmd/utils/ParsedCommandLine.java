package ru.incretio.juja.sqlcmd.utils;

import ru.incretio.juja.sqlcmd.exceptions.EmptyCommandException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class ParsedCommandLine {
    private final String ESCAPE_STRING = "_&_";
    private final List<String> list;

    public ParsedCommandLine(String line) throws EmptyCommandException {
        this.list = parse(line.trim());
    }

    public String getCommandName() {
        return (list == null || list.size() == 0) ? "" : list.get(0);
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

        String newLine = escapeSpacesInQuotes(line, ' ', ESCAPE_STRING);

        result.addAll(Arrays.asList(newLine.split("\\s'|'\\s|'|\\s")));

        result = replaceString(result, ESCAPE_STRING, " ");

        return result;
    }

    private String escapeSpacesInQuotes(String sourceText, char charQuote, String escapeString) {
        Boolean inQuotes = false;
        String result = "";

        for (int i = 0; i < sourceText.length(); i++) {
            inQuotes = (sourceText.charAt(i) == '\'') ? !inQuotes : inQuotes;

            if (sourceText.charAt(i) == charQuote && inQuotes) {
                result += escapeString;
            } else {
                result += sourceText.charAt(i);
            }

        }
        return result;
    }

    private List<String> replaceString(List<String> list, String s, String s1) {
        List<String> result = new ArrayList<>();
        for (String value : list) {
            result.add(value);
        }

        for (int i = 0; i < result.size(); i++) {
            result.set(i, result.get(i).replace(s, s1));
        }

        return result;
    }

}


