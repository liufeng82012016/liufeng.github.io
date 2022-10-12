package com.my.liufeng.rocketmq.controller;

import com.alibaba.fastjson.JSON;
import com.my.liufeng.rocketmq.service.IRocketmqService;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/transaction")
public class TransactionController {

    @Resource
    IRocketmqService rocketmqService;

    @GetMapping("/message")
    public Object messsage(HttpServletRequest request, HttpServletResponse httpServletResponse) {
        String ex = request.getParameter("ex");
        System.out.println(JSON.toJSONString(request.getParameterMap()));
        if (ex == null) {
            ex = "false";
        }
        rocketmqService.sendTransactionMsg(Boolean.parseBoolean(ex));
        return null;
    }

    @GetMapping("/order")
    public Object order(HttpServletRequest request, HttpServletResponse httpServletResponse) {
        rocketmqService.sendMsgOrderly();
        return null;
    }

    @GetMapping("/delay")
    public Object delay(HttpServletRequest request, HttpServletResponse httpServletResponse) {
        String level = request.getParameter("level");
        rocketmqService.sendMsgDelay(Integer.parseInt(ObjectUtils.defaultIfNull(level,"1")));
        return null;
    }
}
