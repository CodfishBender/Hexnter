package com.budgienet.hexnter.utils;

import com.budgienet.hexnter.Hexnter;

import java.util.logging.Level;
public class LogUtil {
    public static void log(String string) {
        Hexnter.getInstance().getLogger().log(Level.INFO, string);
    }
    public static void log(String level, String string) {
        Level l = Level.INFO;
        switch(level) {
            case "WARNING": l = Level.WARNING; break;
            case "SEVERE": l = Level.SEVERE; break;
            case "ALL": l = Level.ALL; break;
        }
        Hexnter.getInstance().getLogger().log(l, string);
    }
}
