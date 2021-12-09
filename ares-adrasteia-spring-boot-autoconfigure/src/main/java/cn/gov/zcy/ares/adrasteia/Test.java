package cn.gov.zcy.ares.adrasteia;

import cn.gov.zcy.ares.adrasteia.core.envluation.AresEvaluationContext;
import cn.gov.zcy.ares.adrasteia.core.parser.AresParserPlaceholder;
import org.apache.commons.lang3.StringUtils;
import org.springframework.expression.EvaluationContext;
import org.springframework.expression.Expression;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;

/**
 * @author <a href="mailto:youming@cai-inc.com">斜照</a>
 * @datetime 2021-12-07 21:27:46
 */
public class Test {
    public static void main(String[] args) {
        SpelExpressionParser expressionParser = new SpelExpressionParser();
        StandardEvaluationContext standardEvaluationContext = new StandardEvaluationContext();
        Expression expression = expressionParser.parseExpression("消亡#{T(String).join(#name)}", new AresParserPlaceholder());
        standardEvaluationContext.setVariable("name", "12");
        String value = expression.getValue(standardEvaluationContext, String.class);
        System.out.println("ok");
    }

    public static void test(String... args) {
        System.out.println(String.join(",", args));
    }
}
