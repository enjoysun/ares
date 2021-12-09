package cn.gov.zcy.ares.adrasteia.spi.operator;

import cn.gov.zcy.ares.adrasteia.meta.operator.Operator;

/**
 * @author <a href="mailto:youming@cai-inc.com">斜照</a>
 * @datetime 2021-12-08 18:23:45
 */
public interface OperatorInfuseService {
    /**
     * 获取操作人信息
     * */
    Operator getOperator();
}
