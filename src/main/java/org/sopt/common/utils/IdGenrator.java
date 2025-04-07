package org.sopt.common.utils;

public class IdGenrator {
    private static int id = 0;

    public static int generateId() {
        return id++;
    }
}
