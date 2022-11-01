package io.github.khanhdpdx01.veserver.entity;

import java.util.HashMap;
import java.util.Map;

public enum Role {
    ADMIN,
    SUPER_ADMIN,
    PRINCIPAL;

    public static Map<Integer, String> getAllRoles() {
        Map<Integer, String> ranks = new HashMap<>();
        ranks.put(1, "ROLE_ADMIN");
        ranks.put(2, "ROLE_SUPERADMIN");
        ranks.put(3, "ROLE_PRINCIPAL");
        return ranks;
    }
}
