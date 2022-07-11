package com.my.liufeng.xst.biz.game;

import com.my.liufeng.xst.annotations.CustomRequestAction;
import com.my.liufeng.xst.annotations.PlaywayInstane;

@PlaywayInstane(playwayId = "game")
public class GamePlayway {
    @CustomRequestAction(actionId = "start")
    public String start() {
        return "开始游戏： " + System.currentTimeMillis();
    }
}
