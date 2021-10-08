package com.my.liufeng.test.mysql.product.controller;

import com.my.liufeng.test.mysql.product.service.ILogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author liufeng
 * @Description: 发起Tcc调用，这里有一个循环调用，可以独立一个server去写
 * @since 2021/7/12 17:33
 */
@RestController
@RequestMapping("/tcc")
public class TccController {

    @Autowired
    private ILogService logService;

    /**
     * 未开启seata自动代理
     * 1. 直接调用 -- 远程自动回滚
     * 2. 本地事务(包含数据库操作)+远程事务 --本地事务回滚+远程回滚
     * 3. 本地数据库操作(无事务)+远程事务 ----本地不会滚+远程回滚（只有开启了@GlobalTransactional，才会将事务交给seata管理）
     * 4.
     */
    @RequestMapping("/forward1")
    public void forward1(Boolean hasError) {
        logService.forward1(hasError);
    }

    @RequestMapping("/forward2")
    public void forward2(Boolean hasError) {
        logService.forward2(hasError);
    }

    @RequestMapping("/forward3")
    public void forward3(Boolean hasError) {
        logService.forward3(hasError);
    }

}
