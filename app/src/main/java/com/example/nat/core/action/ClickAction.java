package com.example.nat.core.action;


import android.accessibilityservice.AccessibilityService;
import android.accessibilityservice.GestureDescription;
import android.graphics.Path;
import android.graphics.Rect;
import android.os.Build;

import androidx.annotation.RequiresApi;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
public class ClickAction extends BaseAction {
    private int x = -1;
    private int y = -1;
    private int startTime;
    private int duration;

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void perform(Object... objects) {
        AccessibilityService accessibilityService = (AccessibilityService) objects[0];

        Path path = new Path();
        if (inputData instanceof Rect) {
            path.moveTo(((Rect) inputData).centerX(), ((Rect) inputData).centerY());
        } else {
            path.moveTo(x, y);
        }
        GestureDescription.Builder builder = new GestureDescription.Builder();
        builder.addStroke(new GestureDescription.StrokeDescription(path, startTime, duration));
        accessibilityService.dispatchGesture(builder.build(), new AccessibilityService.GestureResultCallback() {
            @Override
            public void onCompleted(GestureDescription gestureDescription) {
                super.onCompleted(gestureDescription);
                success();
            }

            @Override
            public void onCancelled(GestureDescription gestureDescription) {
                super.onCancelled(gestureDescription);
                failed();
            }
        }, null);
    }
}
