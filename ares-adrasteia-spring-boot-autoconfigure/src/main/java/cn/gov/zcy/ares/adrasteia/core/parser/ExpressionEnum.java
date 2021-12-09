package cn.gov.zcy.ares.adrasteia.core.parser;

/**
 * @author <a href="mailto:youming@cai-inc.com">斜照</a>
 * @datetime 2021-12-09 15:39:51
 */
public enum ExpressionEnum {
    TEXT(1),
    CONDITION(2);

    private int type;

    ExpressionEnum(int type) {
        this.type = type;
    }
}
