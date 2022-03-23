package com.example.nat.core.utils;

import android.content.Context;
import android.util.Log;

import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;

public class ApplicationUtils {

    public static boolean isRunning(Context context, String p) {
        Process process = null;
        try {
            process = new ProcessBuilder("ps").start();
        }  catch (IOException e) {
            e.printStackTrace();
        }
        InputStream in = process.getInputStream();
        Scanner scanner = new Scanner(in);
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            if (line.startsWith("u0_")) {
                String[] temp = line.split(" ");
                String packageName = temp[temp.length - 1];
                if (packageName.contains("/")) continue;
                if (packageName.contains(":")) {
                    packageName = packageName.split(":")[0];
                }
                Log.d("1", "NTS: " + packageName);
            }
        }
        scanner.close();
        process.destroy();
        return false;
    }
}
