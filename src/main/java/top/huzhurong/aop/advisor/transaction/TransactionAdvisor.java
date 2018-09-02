package top.huzhurong.aop.advisor.transaction;

import lombok.Getter;
import lombok.Setter;
import top.huzhurong.aop.advisor.MethodInterceptor;
import top.huzhurong.aop.advisor.transaction.manager.TransactionInterceptor;
import top.huzhurong.aop.invocation.Invocation;

/**
 * 事务增强器
 *
 * @author luobo.cs@raycloud.com
 * @since 2018/8/29
 */
public class TransactionAdvisor implements MethodInterceptor {

    @Getter
    @Setter
    private Object object;

    @Getter
    @Setter
    private TransactionManager transactionManager;


    private TransactionInterceptor transactionInterceptor;

    public TransactionAdvisor(TransactionManager transactionManager) {
        this.transactionManager = transactionManager;
        this.transactionInterceptor = new TransactionInterceptor(this.transactionManager);
    }

    @Override
    public Object invoke(Invocation invocation) throws Throwable {
        try {
            return transactionInterceptor.doTransaction(invocation,this.object);
        } catch (Throwable e) {
            throw e;
        }
    }
}