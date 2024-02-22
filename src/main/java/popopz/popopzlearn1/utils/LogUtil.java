package popopz.popopzlearn1.utils;

import popopz.popopzlearn1.PopopzLearn1;

import java.util.logging.Level;

public class LogUtil {


    public static void log(String string) {
        PopopzLearn1.getInstance().getLogger().log(Level.INFO, string);
    }
    public static void log(String level, String string) {
        Level l = Level.INFO;
        switch(level) {
            case "WARNING": l = Level.WARNING; break;
            case "SEVERE": l = Level.SEVERE; break;
            case "ALL": l = Level.ALL; break;
        }
        PopopzLearn1.getInstance().getLogger().log(l, string);
    }
}
