package com.example.nat.core.scripts;

import com.example.nat.core.action.BaseAction;
import com.example.nat.core.action.ClickTextAndVerifyAction;
import com.example.nat.core.action.OpenAppAction;
import com.example.nat.core.action.OpenAppDetailsAction;

import java.util.LinkedList;

public class ForceStopClearChromeDataScript extends BaseScript {
    @Override
    protected LinkedList<BaseAction> getActions() {
        LinkedList<BaseAction> actions = new LinkedList<>();

        OpenAppAction openChrome = OpenAppAction.builder()
                .inputData("com.android.chrome")
                .build();

        OpenAppDetailsAction openAppDetailsAction = OpenAppDetailsAction.builder()
                .inputData("com.android.chrome")
                .build();

        ClickTextAndVerifyAction clickForceStop = ClickTextAndVerifyAction.builder()
                .inputData("FORCE STOP")
                .verifyTextAppears("If you force stop an app, it may misbehave.")
                .build();

        ClickTextAndVerifyAction clickOk = ClickTextAndVerifyAction.builder()
                .inputData("OK")
                .verifyTextAppears("DISABLE")
                .build();

        ClickTextAndVerifyAction clickStorage = ClickTextAndVerifyAction.builder()
                .inputData("Storage")
                .verifyTextAppears("MANAGE SPACE")
                .build();


        ClickTextAndVerifyAction clickManageSpace = ClickTextAndVerifyAction.builder()
                .inputData("MANAGE SPACE")
                .verifyTextAppears("CLEAR ALL DATA")
                .build();

        ClickTextAndVerifyAction clickClearAll = ClickTextAndVerifyAction.builder()
                .inputData("CLEAR ALL DATA")
                .verifyTextAppears("This includes all files")
                .build();

        ClickTextAndVerifyAction clickOkClear = ClickTextAndVerifyAction.builder()
                .inputData("OK")
                .verifyTextAppears("MANAGE SPACE")
                .build();

        actions.add(openChrome);
        actions.add(openAppDetailsAction);
        actions.add(clickForceStop);
        actions.add(clickOk);
        actions.add(clickStorage);
        actions.add(clickManageSpace);
        actions.add(clickClearAll);
        actions.add(clickOkClear);
        return actions;
    }
}
