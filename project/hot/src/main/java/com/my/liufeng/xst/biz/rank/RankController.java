package com.my.liufeng.xst.biz.rank;

import com.my.liufeng.xst.annotations.CustomMethod;
import com.my.liufeng.xst.annotations.CustomController;
import com.my.liufeng.xst.util.RequestLocal;

@CustomController(id = "rank")
public class RankController {
    @CustomMethod(actionId = "rank")
    public String start() {
        long timeMillis = System.currentTimeMillis();
        RequestLocal.get().getproject().setUpdateTime(timeMillis);
        return "查询排行榜： " + timeMillis;
    }
}
