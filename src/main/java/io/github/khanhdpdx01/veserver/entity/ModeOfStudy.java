package io.github.khanhdpdx01.veserver.entity;

import java.util.HashMap;
import java.util.Map;

public enum ModeOfStudy {
    FULL_TIME,
    PASS_TIME,
    DISTANCE_LEANING;

    public static Map<Integer, String> getAllModeOfStudies() {
        Map<Integer, String> ranks = new HashMap<>();
        ranks.put(1, "Chính quy");
        ranks.put(2, "Vừa làm vừa học");
        ranks.put(3, "Đào tạo từ xa");
        return ranks;
    }
}
