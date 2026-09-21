package com.school.flashmeal.context;

public class BaseContext {
    public static final ThreadLocal<Integer> currentUserId= new ThreadLocal<>();

    public static void setCurrentUserId(int userId) {
        currentUserId.set(userId);
    }

    public static int getCurrentUserId() {
        return currentUserId.get();
    }

    public static void removeCurrentUserId() {
        currentUserId.remove();
    }
}
