package cn.gov.zcy.ares.adrasteia.core.envluation;

/**
 * @author <a href="mailto:youming@cai-inc.com">斜照</a>
 * @datetime 2021-12-09 15:39:51
 */
public enum ExpressionEnum {
    /**
     * 文本模板表达式
     */
    TEXT(1, "文本模板表达式"),
    /**
     * 条件表达式
     */
    CONDITION(2, "条件模板表达式"),
    /**
     * 业务ID
     */
    BIZ(3, "业务植入模板表达式"),
    /**
     * 扩展上下文
     */
    CONTEXT(4, "扩展上下文植入表达式"),
    /**
     * 子业务ID
     */
    CHILD_BIZ_ID(5, "子业务标识植入表达式"),
    /**
     * 身份标识
     */
    IDENTITY_TYPE(6, "身份标识植入表达式"),
    /**
     * 身份标识
     */
    OPERATOR(7, "操作人植入表达式");

    public String getName() {
        return name;
    }

    private String name;
    private int type;

    public int getType() {
        return type;
    }

    ExpressionEnum(int type, String name) {
        this.name = name;
        this.type = type;
    }
}
