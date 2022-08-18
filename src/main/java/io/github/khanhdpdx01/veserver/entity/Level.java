package io.github.khanhdpdx01.veserver.entity;

import java.util.HashMap;
import java.util.Map;

public enum Level {
    ENGINEER,
    BACHELOR,
    MASTER,
    PROFESSOR;

    public static Map<Integer, String> getAllLevels() {
        Map<Integer, String> levels = new HashMap<>();
        levels.put(1, "Kỹ sư");
        levels.put(2, "Cử nhân");
        levels.put(3, "Thạc sĩ");
        levels.put(4, "Tiến sĩ");
        return levels;
    }
}
