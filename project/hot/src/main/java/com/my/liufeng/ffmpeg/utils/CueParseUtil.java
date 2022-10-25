package com.my.liufeng.ffmpeg.utils;

import com.my.liufeng.ffmpeg.MusicInfo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.LinkedList;
import java.util.List;

public class CueParseUtil {
    public static List<MusicInfo> parse(File file) throws Exception {
        BufferedReader reader = new BufferedReader(new FileReader(file));
        String s;
        LinkedList<MusicInfo> musicInfoList = new LinkedList<>();
        boolean open = false;
        while ((s = reader.readLine()) != null) {
            String content = s.trim();
            if (content.contains("FILE")) {
                open = true;
            }
            if (!open) {
                continue;
            }
            if (content.startsWith("TRACK")) {
                MusicInfo musicInfo = new MusicInfo();
                musicInfoList.add(musicInfo);
            } else if (content.startsWith("TITLE")) {
                musicInfoList.getLast().setName(content.replaceAll("\"", "").replace("TITLE", "").trim());
            } else if (content.contains("INDEX 01")) {
                musicInfoList.getLast().setStart(content.replace("INDEX 01", "").trim());
                musicInfoList.getLast().setStartStr(content.replace("INDEX 01", "").trim());
            } else if (content.contains("INDEX 00")) {
                musicInfoList.get(musicInfoList.size() - 2).setContinueTime(content.replace("INDEX 00", "").trim());
            }
        }
        musicInfoList.getLast().setContinueTime(addSixMinute(musicInfoList.getLast().getStartStr()));
        musicInfoList.forEach(System.out::println);
        return musicInfoList;
    }


    private static String addSixMinute(String time) {
        int minute = Integer.parseInt(time.substring(0, 2)) + 5;
        return minute + time.substring(2);
    }


}
