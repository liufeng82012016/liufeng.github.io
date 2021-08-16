package com.coininn.test.mysql.product.service;


import io.seata.rm.tcc.api.BusinessActionContext;
import io.seata.rm.tcc.api.BusinessActionContextParameter;
import io.seata.rm.tcc.api.LocalTCC;
import io.seata.rm.tcc.api.TwoPhaseBusinessAction;

@LocalTCC
public interface IProductService {


    void minusStock();

    void minusStock2();


    /**
     * Prepare boolean.
     *
     * @param a the a
     * @return the boolean
     */
    @TwoPhaseBusinessAction(name = "ProductService", commitMethod = "commitTestTcc", rollbackMethod = "rollbackTestTcc")
    boolean prepareTestTcc(@BusinessActionContextParameter(paramName = "a") int a);

    /**
     * Commit boolean.
     *
     * @param actionContext the action context
     * @return the boolean
     */
    boolean commitTestTcc(BusinessActionContext actionContext);

    /**
     * Rollback boolean.
     *
     * @param actionContext the action context
     * @return the boolean
     */
    boolean rollbackTestTcc(BusinessActionContext actionContext);
}
