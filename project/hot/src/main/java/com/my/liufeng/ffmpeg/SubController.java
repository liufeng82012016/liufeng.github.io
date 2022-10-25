package com.my.liufeng.ffmpeg;

import com.my.liufeng.ffmpeg.utils.CmdUtil;
import com.my.liufeng.ffmpeg.utils.CueParseUtil;
import com.my.liufeng.ffmpeg.utils.RandomUtil;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("ffmpeg")
public class SubController {
    @RequestMapping("sub")
    public Object sub(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String dir = request.getParameter("dir");
        if (dir == null) {
            return "dir is empty.";
        }
        File dirFile = new File("/tmp/ape/" + dir);
        File[] files = dirFile.listFiles();
        List<MusicInfo> musicInfoList = null;
        String apeFile = null;
        for (int i = 0; i < files.length; i++) {
            if (files[i].getName().endsWith(".ape")||files[i].getName().endsWith(".wav")) {
                apeFile = files[i].getAbsolutePath();
                continue;
            }
            if (!files[i].getName().endsWith(".cue")) {
                continue;
            }
            // 如果是cue，解析
            musicInfoList = CueParseUtil.parse(files[i]);
            if (musicInfoList.isEmpty()) {
                return "cue musicInfoList fail";
            }
        }
        if (apeFile == null || musicInfoList == null) {
            return "no cue file";
        }

        // 构建cmd命令
        Map<MusicInfo, String> nameMap = new HashMap<>();
        for (MusicInfo musicInfo : musicInfoList) {
            String randomFileName = RandomUtil.randomStr(16);
            nameMap.put(musicInfo, randomFileName);
            String cmd = String.format("ffmpeg -i %s -ss %s -t %s %s",
                    apeFile, musicInfo.getStart(), musicInfo.getContinueTime(), "/tmp/ape/" + dir + "/" + randomFileName + ".flac");
            CmdUtil.execute(cmd);
        }
        System.out.println(nameMap);
        // 重命名文件
        for (MusicInfo musicInfo : musicInfoList) {
            File file = new File("/tmp/ape/" + dir + "/" + nameMap.get(musicInfo) + ".flac");
            if (file.exists()) {
                File toRename = new File("/tmp/ape/" + dir + "/" + musicInfo.getName() + ".flac");
                boolean b = file.renameTo(toRename);
                if (b) {
                    System.out.println(String.format("%s rename success", musicInfo.getName()));
                } else {
                    System.out.println(String.format("%s rename fail", musicInfo.getName()));
                }
            } else {
                System.out.println(musicInfo.getName() + " 不存在对应文件");
            }
        }
        return "musicInfoList " + musicInfoList.size() + " file";
    }


}
