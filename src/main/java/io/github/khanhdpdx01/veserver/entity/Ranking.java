package io.github.khanhdpdx01.veserver.entity;

import java.util.HashMap;
import java.util.Map;

public enum Ranking {
    EXCELLENT,
    VERY_GOOD,
    GOOD,
    ORDINARY;

    public static Map<Integer, String> getAllRanks() {
        Map<Integer, String> ranks = new HashMap<>();
        ranks.put(1, "Xuất sắc");
        ranks.put(2, "Giỏi");
        ranks.put(3, "Khá");
        ranks.put(4, "Trung bình");
        return ranks;
    }
}
