package com.my.liufeng.ffmpeg.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * 构建cmd命令，并执行
 */
public class CmdUtil {
    public static void execute(String cmd) {
        System.out.println("do execute : " + cmd);
        Process process = null;
        List<String> processList = new ArrayList<>();
        try {
            process = Runtime.getRuntime().exec(cmd);
            BufferedReader input = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line = "";
            while ((line = input.readLine()) != null) {
                processList.add(line);
            }
            input.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(String.format("process:%S result:%s", cmd, processList));
    }
}
