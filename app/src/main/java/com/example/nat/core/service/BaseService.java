package com.example.nat.core.service;

import android.accessibilityservice.AccessibilityService;
import android.graphics.Rect;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;

import com.example.nat.core.enums.Status;
import com.example.nat.core.models.NodeInfoExtractedModel;
import com.example.nat.core.scripts.BaseScript;
import com.example.nat.core.utils.CollectionsUtils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import lombok.NonNull;

public class BaseService extends AccessibilityService {
    protected LinkedList<BaseScript> scripts = new LinkedList<>();

    @Override
    protected void onServiceConnected() {
        super.onServiceConnected();
        scripts.addAll(ScriptsManager.getInstance().getScripts());
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                try {
                    AccessibilityNodeInfo nodeInfo = getRootInActiveWindow();
                    if (nodeInfo == null) return;
                    performScripts(getNodes(nodeInfo, 0));
                } catch (Exception e) {
                    Log.d("ERROR", "NTS: ----- " + e.getMessage(), e);
                }
            }

        },0,1000);
    }

    @Override
    public void onAccessibilityEvent(AccessibilityEvent accessibilityEvent) {

    }

    private synchronized void performScripts(List<NodeInfoExtractedModel> nodeInfoExtractedModelList) {
        if(CollectionsUtils.isEmpty(scripts)) {
            Log.d(this.getClass().getSimpleName(), "NTS: ----- Scripts is Empty!!");
            return;
        }
        BaseScript script = scripts.getFirst();
        Log.d("SCRIPT", "Running " + script.getClass().getSimpleName());
        script.performActions(nodeInfoExtractedModelList, this);
        if (script.status == Status.SUCCESS) {
            scripts.removeFirst();
        }
    }

    @Override
    public void onInterrupt() {

    }


//    public void performClickAction(final ClickAction clickAction) {
//        if(clickAction.path.isEmpty()){
//            return;
//        }
//        GestureDescription.Builder builder = new GestureDescription.Builder();
//        builder.addStroke(new GestureDescription.StrokeDescription(clickAction.path, 0, clickAction.getDuration()));
//        dispatchGesture(builder.build(), new GestureResultCallback() {
//            @Override
//            public void onCompleted(GestureDescription gestureDescription) {
//                super.onCompleted(gestureDescription);
//            }
//
//            @Override
//            public void onCancelled(GestureDescription gestureDescription) {
//                super.onCancelled(gestureDescription);
//            }
//        }, null);
//    }

    public List<NodeInfoExtractedModel> getNodes(AccessibilityNodeInfo nodeInfo, int depth) {
        List<NodeInfoExtractedModel> nodeInfoExtractedModelList = new ArrayList<>();

        if (nodeInfo != null) {
            NodeInfoExtractedModel nodeInfoExtractedModel = new NodeInfoExtractedModel();
            String nodeText = nodeInfo.getText() != null
                    ? nodeInfo.getText().toString()
                    : nodeInfo.getContentDescription() != null
                    ? nodeInfo.getContentDescription().toString()
                    : "";
            Rect rect = new Rect();
            nodeInfo.getBoundsInScreen(rect);
            nodeInfoExtractedModel.setNodeText(nodeText);
            nodeInfoExtractedModel.setRect(rect);
            nodeInfoExtractedModel.setNodeInfo(nodeInfo);
            if (nodeInfoExtractedModel.getNodeText().length() > 0) {
                nodeInfoExtractedModelList.add(nodeInfoExtractedModel);
            }
            for (int i = 0; i < nodeInfo.getChildCount(); ++i) {
                nodeInfoExtractedModelList.addAll(getNodes(nodeInfo.getChild(i), depth + 1));
            }
        }
        return nodeInfoExtractedModelList;
    }
}
