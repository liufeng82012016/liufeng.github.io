package com.my.liufeng.xst.biz.game;

import com.my.liufeng.xst.annotations.CustomMethod;
import com.my.liufeng.xst.annotations.CustomController;

@CustomController(id = "game")
public class GameController {
    @CustomMethod(actionId = "start")
    public String start() {
        return "开始游戏： " + System.currentTimeMillis();
    }
}
