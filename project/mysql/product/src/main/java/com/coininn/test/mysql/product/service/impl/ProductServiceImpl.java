package com.coininn.test.mysql.product.service.impl;


import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.coininn.test.mysql.product.entity.Product;
import com.coininn.test.mysql.product.mapper.ProductMapper;
import com.coininn.test.mysql.product.service.IProductService;
import io.seata.rm.tcc.api.BusinessActionContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
public class ProductServiceImpl extends ServiceImpl<ProductMapper, Product> implements IProductService {


    @Override
    @Transactional(rollbackFor = Exception.class)
    public void minusStock() {
        baseMapper.minusStock();
        Product product = baseMapper.selectById(1);
        System.out.println("count1 is " + product.getStock());
    }

    @Override
    public void minusStock2() {
        baseMapper.minusStock();
        Product product = baseMapper.selectById(1);
        System.out.println("count2 is " + product.getStock());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean prepareTestTcc(int a) {
        log.info("parameter is {}", a);
        System.out.println("parameter is " + a);
        baseMapper.minusStock();
        return false;
    }

    @Override
    public boolean commitTestTcc(BusinessActionContext actionContext) {
        log.info("commit xid is {},parameter is {}", actionContext.getXid(), actionContext.getActionContext("a"));
        System.out.println(String.format("commit xid is %s,parameter is %s", actionContext.getXid(), actionContext.getActionContext("a")));
        return true;
    }

    @Override
    public boolean rollbackTestTcc(BusinessActionContext actionContext) {
        log.info("rollback xid is {},parameter is {}", actionContext.getXid(), actionContext.getActionContext("a"));
        System.out.println(String.format("rollback xid is %s,parameter is %s", actionContext.getXid(), actionContext.getActionContext("a")));
        baseMapper.addStock();
        return true;
    }


}
