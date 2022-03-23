package com.example.nat.core.models;

import android.graphics.Rect;
import android.view.accessibility.AccessibilityNodeInfo;

/**
 * Created by Nhatran241 on 11/5/2021.
 * trannhat2411999@gmail.com
 */
public class NodeInfoExtractedModel {
    private AccessibilityNodeInfo nodeInfo;
    private String nodeText;
    private Rect rect;

    public NodeInfoExtractedModel(String nodeText, Rect rect) {
        this.nodeText = nodeText;
        this.rect = rect;
    }

    public NodeInfoExtractedModel() {
    }

    public String getNodeText() {
        return nodeText;
    }

    public void setNodeText(String nodeText) {
        this.nodeText = nodeText;
    }

    public Rect getRect() {
        return rect;
    }

    public void setRect(Rect rect) {
        this.rect = rect;
    }

    public AccessibilityNodeInfo getNodeInfo() {
        return nodeInfo;
    }

    public void setNodeInfo(AccessibilityNodeInfo nodeInfo) {
        this.nodeInfo = nodeInfo;
    }
}