package com.example.nat.core.action;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.widget.Toast;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
public class ToastAction extends BaseAction {
    @Override
    public void perform(Object... objects) {
        Object context = objects[0];
        if (!(context instanceof Context)) {
            Log.d(this.getClass().getSimpleName(), "NTS: " + this.getClass().getSimpleName() + "Missing Context");
            failed();
            return;
        }
        new Handler(Looper.getMainLooper()).post(() -> {
            Toast.makeText((Context) context, "" + inputData, Toast.LENGTH_SHORT).show();
            success();
        });
    }
}
