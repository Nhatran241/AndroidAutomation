package com.example.nat.core.scripts;

import android.util.Log;

import com.example.nat.core.action.BaseAction;
import com.example.nat.core.enums.Status;
import com.example.nat.core.models.NodeInfoExtractedModel;
import com.example.nat.core.service.BaseService;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

public abstract class BaseScript implements Serializable {
    public Status status;
    protected LinkedList<BaseAction> actions;

    public BaseScript() {
        actions = getActions();
    }

    protected abstract LinkedList<BaseAction> getActions();

    public synchronized void performActions(List<NodeInfoExtractedModel> nodeInfoExtractedModelList, BaseService baseService) {
        if (actions.isEmpty()) {
            Log.d(this.getClass().getSimpleName(), "NTS: -----Actions is Empty!!");
            status = Status.SUCCESS;
            return;
        }
        BaseAction action = actions.getFirst();
        action.perform(baseService, nodeInfoExtractedModelList);
        Log.d("SCRIPT ACTION", action.status + " " + Thread.currentThread() + "----" + action.getClass().getSimpleName() + " : " + action.inputData);
        if (action.status == Status.SUCCESS) {
            actions.removeFirst();
            if (action.actionGetOutput != null)
                action.actionGetOutput.inputData = action.outputData;
            if (action.actionSuccess != null)
                actions.addFirst(action.actionSuccess);
        }

        if (action.status == Status.FAILED) {
            actions.removeFirst();
            if (action.actionFail != null)
                actions.addFirst(action.actionFail);
        }
    }
}
