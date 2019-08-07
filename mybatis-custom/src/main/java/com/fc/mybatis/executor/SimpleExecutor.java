package com.fc.mybatis.executor;

/**
 * @author lize
 * @date 6/23/19 9:46 PM
 */
public class SimpleExecutor implements Executor {
    @Override
    public <T> T query(String statement, Object[] parameter, Class pojo) {
        StatementHandler statementHandler = new StatementHandler();
        return statementHandler.query(statement, parameter, pojo);
    }
}
