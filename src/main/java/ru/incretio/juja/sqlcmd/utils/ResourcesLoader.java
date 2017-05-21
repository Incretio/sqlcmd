package ru.incretio.juja.sqlcmd.utils;

import java.util.ResourceBundle;

public class ResourcesLoader{
    private final static ResourceBundle resourceBundle =
            ResourceBundle.getBundle("StringResource");

    public static String takeCaption(String resourceName){
        return resourceBundle.getString(resourceName);
    }
}
