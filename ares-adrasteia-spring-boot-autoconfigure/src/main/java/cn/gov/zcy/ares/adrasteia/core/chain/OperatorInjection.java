package cn.gov.zcy.ares.adrasteia.core.chain;

import cn.gov.zcy.ares.adrasteia.core.envluation.AresEvaluationContext;
import cn.gov.zcy.ares.adrasteia.meta.LogPersistContext;
import cn.gov.zcy.ares.adrasteia.meta.operator.Operator;
import cn.gov.zcy.ares.adrasteia.spi.operator.OperatorInfuseService;

/**
 * @author <a href="mailto:youming@cai-inc.com">斜照</a>
 * @datetime 2021-12-13 14:12:02
 */
public class OperatorInjection {

    /**执行链之前执行*/
    public void doInjection(OperatorInfuseService operatorInfuseService, LogPersistContext persistContext, AresEvaluationContext evaluationContext) {
        if (null != operatorInfuseService) {
            Operator operator = operatorInfuseService.getOperator();
            if (null != operator) {
                persistContext.setOperator(operator);
                evaluationContext.setVariable("operator", operator);
            }
        }
    }

    private static class Injection {
        private final static OperatorInjection OPERATOR_INJECTION;

        static {
            OPERATOR_INJECTION = new OperatorInjection();
        }

        public static OperatorInjection getOperatorInjection() {
            return OPERATOR_INJECTION;
        }
    }

    public static OperatorInjection getInstance() {
        return Injection.getOperatorInjection();
    }
}
