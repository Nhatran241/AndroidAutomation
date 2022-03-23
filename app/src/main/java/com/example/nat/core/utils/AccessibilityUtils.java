package com.example.nat.core.utils;

import android.view.accessibility.AccessibilityNodeInfo;

public class AccessibilityUtils {
    public static AccessibilityNodeInfo getClickableNode(AccessibilityNodeInfo nodeInfo) {
        return nodeInfo.isClickable() || nodeInfo.getParent() == null ? nodeInfo : getClickableNode(nodeInfo.getParent());
    }
}
