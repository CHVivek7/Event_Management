package com.eventmanagement.security;

import java.util.HashSet;
import java.util.Set;

// Simple in-memory blacklist for JWT tokens
public class TokenBlacklist {
    private static final Set<String> BLACKLIST = new HashSet<>();

    public static void blacklistToken(String token) {
        BLACKLIST.add(token);
    }

    public static boolean isTokenBlacklisted(String token) {
        return BLACKLIST.contains(token);
    }
}

