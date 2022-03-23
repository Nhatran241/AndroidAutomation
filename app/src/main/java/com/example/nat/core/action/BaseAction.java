package com.example.nat.core.action;

import android.util.Log;

import com.example.nat.core.enums.Status;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
public abstract class BaseAction implements Serializable {
    public Status status;
    public Object inputData;
    public Object outputData;
    public BaseAction actionGetOutput;
    public BaseAction actionSuccess;
    public BaseAction actionFail;
    public int retry;

    public abstract void perform(Object... objects);

    protected void success() {
        status = Status.SUCCESS;
    }

    protected void failed() {
        status = Status.FAILED;
    }

    protected void failed(BaseAction action, Exception e) {
        status = Status.FAILED;
        Log.d(action.getClass().getSimpleName(), "NTS: " + e.getMessage(), e);
    }

    protected void processing() {
        status = Status.PROCESSING;
    }
}
