package com.my.liufeng.ffmpeg.utils;

import org.jaudiotagger.audio.AudioFile;
import org.jaudiotagger.audio.flac.FlacFileReader;
import org.jaudiotagger.tag.FieldKey;
import org.jaudiotagger.tag.Tag;

import java.io.File;

/**
 * 设置flac文件 专辑、歌手等信息
 */
public class FlacExtraUtil {

    public static void main(String[] args) {
        modify("/Users/liufeng/IdeaProjects/duibatest/learn/src/main/java/learn/ffmpeg/我真的愿意的.flac","张信哲","我真的愿意");
    }

    public static void modify(String path, String author, String album) {
        File flacFile = new File(path);
        try {
            AudioFile audioFile = new FlacFileReader().read(flacFile);
            Tag tag = audioFile.getTag();
            // 艺术家
            tag.addField(FieldKey.ARTIST, author);
            // 专辑
            tag.addField(FieldKey.ALBUM, album);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
