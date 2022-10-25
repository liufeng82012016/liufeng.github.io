package com.my.liufeng.ffmpeg;

public class MusicInfo {
    String name;
    String start;
    String continueTime;
    String startStr;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = "00:" + start.substring(0, 5);
    }

    public String getContinueTime() {
        return continueTime;
    }

    public void setContinueTime(String continueTime) {
        this.continueTime = "00:" + continueTime.substring(0, 5);
    }

    public String getStartStr() {
        return startStr;
    }

    public void setStartStr(String startStr) {
        this.startStr = startStr;
    }

    @Override
    public String toString() {
        return "MusicInfo{" +
                "name='" + name + '\'' +
                ", start='" + start + '\'' +
                ", continueTime='" + continueTime + '\'' +
                '}';
    }
}
