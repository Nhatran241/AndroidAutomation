package com.example.nat.core.action;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.util.Log;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
public class OpenHomeAction extends BaseAction {
    @Override
    public void perform(Object... objects) {
        Object context = objects[0];
        if (!(context instanceof Context)) {
            Log.d(this.getClass().getSimpleName(), "NTS: " + this.getClass().getSimpleName() + "Missing Context");
            failed();
            return;
        }

        Intent startMain = new Intent(Intent.ACTION_MAIN);
        startMain.addCategory(Intent.CATEGORY_HOME);
        startMain.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        ((Context) context).startActivity(startMain);

        success();
    }
}
