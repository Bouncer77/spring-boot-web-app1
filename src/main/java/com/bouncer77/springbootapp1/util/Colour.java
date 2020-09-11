package com.bouncer77.springbootapp1.util;

/**
 * @author Kosenkov Ivan on 24.07.2020
 * Класс хранит константы для цветного вывода в стандартный поток вывода
 * */
public class Colour {
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_WHITE = "\u001B[37m";

    public static String yellow(String msg) {
        return ANSI_YELLOW + msg + ANSI_RESET;
    }

    public static String blue(String msg) { return ANSI_BLUE + msg + ANSI_RESET; }

    public static String green(String msg) { return ANSI_GREEN + msg + ANSI_RESET; }

    public static String purple(String msg) { return ANSI_PURPLE + msg + ANSI_RESET; }

    public static String cyan(String msg) { return ANSI_CYAN + msg + ANSI_RESET; }

    public static String red(String msg) { return ANSI_RED + msg + ANSI_RESET; }
}
