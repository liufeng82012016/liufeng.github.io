package com.my.liufeng.xst.biz.rank;

import com.my.liufeng.xst.annotations.CustomRequestAction;
import com.my.liufeng.xst.annotations.PlaywayInstane;
import com.my.liufeng.xst.util.RequestLocal;

@PlaywayInstane(playwayId = "rank")
public class RankPlayway {
    @CustomRequestAction(actionId = "rank")
    public String start() {
        long timeMillis = System.currentTimeMillis();
        RequestLocal.get().getProjectx().setUpdateTime(timeMillis);
        return "查询排行榜： " + timeMillis;
    }
}
