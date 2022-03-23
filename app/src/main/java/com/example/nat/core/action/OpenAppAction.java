package com.example.nat.core.action;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.util.Log;

import com.example.nat.core.utils.ApplicationUtils;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
public class OpenAppAction extends BaseAction {
    @Override
    public void perform(Object... objects) {
        Object context = objects[0];
        if (!(context instanceof Context)) {
            Log.d(this.getClass().getSimpleName(), "NTS: " + this.getClass().getSimpleName() + "Missing Context");
            failed();
            return;
        }
        if (!(inputData instanceof String)) {
            Log.d(this.getClass().getSimpleName(), "NTS: " + this.getClass().getSimpleName() + "data must be package name");
            failed();
            return;
        }
        try {
            PackageManager pm = ((Context) context).getPackageManager();
            Intent launchIntent = pm.getLaunchIntentForPackage(inputData.toString());
            ((Context) context).startActivity(launchIntent);
        } catch (Exception e) {
            failed(this, e);
            return;
        }

        success();
    }
}
