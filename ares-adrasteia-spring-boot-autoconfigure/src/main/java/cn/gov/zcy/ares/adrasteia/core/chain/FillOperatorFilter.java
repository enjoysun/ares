package cn.gov.zcy.ares.adrasteia.core.chain;

import cn.gov.zcy.ares.adrasteia.meta.LogPersistContext;
import cn.gov.zcy.ares.adrasteia.meta.operator.Operator;
import cn.gov.zcy.ares.adrasteia.spi.operator.OperatorInfuseService;
import org.aspectj.lang.JoinPoint;

/**
 * @author <a href="mailto:youming@cai-inc.com">斜照</a>
 * @datetime 2021-12-13 14:12:02
 */
public class FillOperatorFilter implements LogRecordPrepareFilter {
    public FillOperatorFilter(OperatorInfuseService operatorInfuseService) {
        this.operatorInfuseService = operatorInfuseService;
    }

    private OperatorInfuseService operatorInfuseService;

    @Override
    public void doFilter(LogPersistContext persistContext, JoinPoint joinPoint, Object result, FilterChain chain) {
        if (null != operatorInfuseService) {
            Operator operator = operatorInfuseService.getOperator();
            if (null != operator) {
                persistContext.setOperator(operator);
            }
        }
        chain.doFilter(persistContext, joinPoint, result, chain);
    }
}
