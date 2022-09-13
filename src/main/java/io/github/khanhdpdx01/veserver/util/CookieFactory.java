package io.github.khanhdpdx01.veserver.util;

import javax.servlet.http.Cookie;

public class CookieFactory {
    public static Cookie create(String name, String value) {
        Cookie cookie = new Cookie(name, value);
        cookie.setMaxAge(60 * 60 * 24 * 365);
        cookie.setHttpOnly(false);
        cookie.setPath("/");
        cookie.setSecure(false);
        return cookie;
    }
}