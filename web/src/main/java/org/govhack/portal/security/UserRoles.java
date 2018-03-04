package org.govhack.portal.security;

import javafx.util.Pair;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public enum UserRoles {

    ADMIN("ADMIN"),
    SPONSOR_ADMIN("SPONSOR_ADMIN"),
    EVENT_ADMIN("EVENT_ADMIN"),
    PARTICIPANT("PARTICIPANT"),
    GUEST("GUEST");

    private final String x;

    UserRoles(String x) {
        this.x = x;
    }

    public static UserRoles fromString(String text) {
        if (text != null) {
            for (UserRoles x : UserRoles.values()) {
                if (text.equalsIgnoreCase(x.toString())) {
                    return x;
                }
            }
        }
        return GUEST;
    }

    public static List<Pair<UserRoles, String>> getListWithNames() {
        return Arrays.stream(UserRoles.values())
                .map(x -> new Pair<>(x, x + " - " + x.getNiceName()))
                .collect(Collectors.toList());
    }

    public String getNiceName() {
        return x;
    }

}
