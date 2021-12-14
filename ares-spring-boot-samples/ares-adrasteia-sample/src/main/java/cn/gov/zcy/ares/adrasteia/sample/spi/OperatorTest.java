package cn.gov.zcy.ares.adrasteia.sample.spi;

import cn.gov.zcy.ares.adrasteia.meta.operator.Operator;
import cn.gov.zcy.ares.adrasteia.spi.operator.OperatorInfuseService;
import org.springframework.context.annotation.Primary;

/**
 * @author <a href="mailto:youming@cai-inc.com">斜照</a>
 * @datetime 2021-12-11 16:39:57
 */
@Primary
public class OperatorTest implements OperatorInfuseService {
    @Override
    public Operator getOperator() {
        Operator operator = new Operator();
        operator.setName("123");
        return operator;
    }
}
