package com.my.liufeng.xst.biz.rank;

import com.my.liufeng.xst.annotations.CustomMethod;
import com.my.liufeng.xst.annotations.CustomController;

@CustomController(playwayId = "game")
public class GameController {
    @CustomMethod(actionId = "start")
    public String start() {
        return "开始游戏： " + System.currentTimeMillis();
    }
}
