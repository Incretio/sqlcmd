package ru.incretio.juja.sqlcmd.utils;

import ru.incretio.juja.sqlcmd.exceptions.EmptyCommandException;
import java.util.*;
import java.util.stream.Collectors;

public class ParsedCommandLine {
    private final static String ESCAPE_STRING = "_&_";
    private final List<String> list;

    public ParsedCommandLine(String line) throws EmptyCommandException {
        if (line == null){
            throw new EmptyCommandException();
        }
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

    private List<String> parse(String line) throws EmptyCommandException {
        if (line == null || line.length() == 0) {
            throw new EmptyCommandException();
        }


        List<String> result = new ArrayList<>();

        String newLine = escapeSpacesInQuotes(trimDoubleSpaces(line), ' ', ESCAPE_STRING);

        result.addAll(Arrays.asList(newLine.split("\\s'|'\\s|'|\\s")));

        result = replaceString(result, ESCAPE_STRING, " ");
        result = trimEveryString(result);

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
        List<String> result = list.stream().collect(Collectors.toList());

        for (int i = 0; i < result.size(); i++) {
            result.set(i, result.get(i).replace(s, s1));
        }

        return result;
    }

    private String trimDoubleSpaces(String text) {
        String result = text;
        while (result.contains("  ")) {
            result = result.replace("  ", " ");
        }
        return result;
    }

    private List<String> trimEveryString(List<String> list) {
        List<String> result = new ArrayList<>(list);
        for (int i = 0; i < result.size(); i++) {
            result.set(i, result.get(i).trim());
        }
        return result;
    }


}


