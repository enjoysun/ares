package cn.gov.zcy.ares.adrasteia.config;

import cn.gov.zcy.ares.adrasteia.core.envluation.AresExpressionEvaluator;
import cn.gov.zcy.ares.adrasteia.core.envluation.AresValueParser;
import org.springframework.context.annotation.Import;

/**
 * @author <a href="mailto:youming@cai-inc.com">斜照</a>
 * @datetime 2021-12-14 17:51:21
 */
@Import({AresExpressionEvaluator.class, AresValueParser.class})
public class AspectEvaluationSourceSelector {
}
