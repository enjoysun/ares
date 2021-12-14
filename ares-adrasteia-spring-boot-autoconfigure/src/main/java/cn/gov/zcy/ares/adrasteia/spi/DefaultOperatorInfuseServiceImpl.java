package cn.gov.zcy.ares.adrasteia.spi;

import cn.gov.zcy.ares.adrasteia.meta.operator.Operator;
import cn.gov.zcy.ares.adrasteia.spi.operator.OperatorInfuseService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author <a href="mailto:youming@cai-inc.com">斜照</a>
 * @datetime 2021-12-11 17:51:43
 */
@Slf4j
@AutoConfigureAfter(SpringBootApplication.class)
public class DefaultOperatorInfuseServiceImpl implements OperatorInfuseService {
    @Override
    public Operator getOperator() {
        log.info("执行默认操作人获取操作，应重写,无法获取操作人");
        return null;
    }
}
