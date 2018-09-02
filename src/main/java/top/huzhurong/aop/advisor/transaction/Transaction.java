package top.huzhurong.aop.advisor.transaction;

import top.huzhurong.aop.advisor.transaction.manager.ConnectionManager;

/**
 * 事务
 *
 * @author luobo.cs@raycloud.com
 * @since 2018/8/29
 */
public class Transaction {
    private ConnectionManager connectionManager;
    private boolean newTransaction;
    private boolean active;

    public ConnectionManager getConnection() {
        return connectionManager;
    }

    public void setConnectionManager(ConnectionManager connectionManager) {
        this.connectionManager = connectionManager;
    }

    public boolean isNewTransaction() {
        return newTransaction;
    }

    public void setNewTransaction(boolean newTransaction) {
        this.newTransaction = newTransaction;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
