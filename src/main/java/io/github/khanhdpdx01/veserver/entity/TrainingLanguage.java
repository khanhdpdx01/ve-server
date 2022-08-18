package io.github.khanhdpdx01.veserver.entity;

import java.util.HashMap;
import java.util.Map;

public enum TrainingLanguage {
    VIETNAMESE,
    ENGLISH,
    FRANCE,
    JAPANESE;

    public static Map<Integer, String> getAllLanguages() {
        Map<Integer, String> languages = new HashMap<>();
        languages.put(1, "Việt nam");
        languages.put(2, "Tiếng anh");
        languages.put(3, "Pháp");
        languages.put(4, "Nhật bản");
        return languages;
    }
}
