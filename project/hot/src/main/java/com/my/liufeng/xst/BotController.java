package com.my.liufeng.xst;

import com.alibaba.fastjson.JSONObject;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 配合h5
 */
@RestController
@RequestMapping("/bot")
@CrossOrigin
public class BotController {
    @GetMapping("autoReply")
    public Object getBookList(@RequestParam String msg) {
        return new JSONObject()
                .fluentPut("code", 200)
                .fluentPut("data", new JSONObject()
                        .fluentPut("msg", "抱歉哦，今天休息～"));
    }

    @GetMapping("getVoice")
    public Object getVoice(@RequestParam String msg) {
        return new JSONObject()
                .fluentPut("code", 200)
                .fluentPut("data", new JSONObject()
                        .fluentPut("voice", "https://www.xzmp3.com/down/4874bea05337.mp3"));
    }
}
