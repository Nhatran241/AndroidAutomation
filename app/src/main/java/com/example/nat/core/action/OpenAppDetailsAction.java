package com.example.nat.core.action;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.provider.Settings;
import android.util.Log;

import com.example.nat.core.utils.ApplicationUtils;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
public class OpenAppDetailsAction extends BaseAction {
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
        Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
        Uri uri = Uri.fromParts("package", String.valueOf(inputData), null);
        intent.setData(uri);
        ((Context) context).startActivity(intent);

        success();
    }
}
