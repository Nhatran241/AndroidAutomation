package com.example.nat.core.utils;

import java.util.Collection;

public class CollectionsUtils {

    public static boolean isEmpty(Collection collection) {
        if (collection == null) {
            return true;
        }
        return collection.isEmpty();
    }
}
