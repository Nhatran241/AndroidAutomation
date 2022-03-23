package com.example.nat.core.service;

import com.example.nat.core.scripts.BaseScript;
import com.example.nat.core.scripts.ForceStopClearChromeDataScript;

import java.util.LinkedList;

public class ScriptsManager {
    private static ScriptsManager instance;

    public static ScriptsManager getInstance() {
        if (instance == null)
            instance = new ScriptsManager();
        return instance;
    }

    public LinkedList<BaseScript> getScripts(){
        LinkedList<BaseScript> scripts = new LinkedList<>();
        scripts.add(new ForceStopClearChromeDataScript());
        return scripts;
    }
}
