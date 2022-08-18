package io.github.khanhdpdx01.veserver.entity;

import java.util.HashMap;
import java.util.Map;

public enum Status {
    RECEIVED,
    NOT_RECEIVED,
    PENDING;

    public static Map<Integer, String> getStatus() {
        Map<Integer, String> ranks = new HashMap<>();
        ranks.put(1, "Đã nhận");
        ranks.put(2, "Chưa nhận");
        ranks.put(3, "Đang xử lý");
        return ranks;
    }
}
