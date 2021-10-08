package liufeng;

import liufeng.modul.Music;

/**
 * @Author liufeng
 * @Description: 中央控制器 保存一些静态常量
 * @since 2021/2/6 17:36
 */
public class CentralController {
    /**
     * 选中的音乐
     */
    private static Music selected;
    /**
     * 播放的音乐
     */
    private static Music playing;


    public static Music getSelected() {
        return selected;
    }

    public static void setSelected(Music selected) {
        CentralController.selected = selected;
    }

    public static Music getPlaying() {
        return playing;
    }

    public static void setPlaying(Music playing) {
        CentralController.playing = playing;
    }
}
