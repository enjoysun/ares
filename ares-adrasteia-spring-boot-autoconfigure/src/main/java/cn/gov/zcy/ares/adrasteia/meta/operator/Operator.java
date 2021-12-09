package cn.gov.zcy.ares.adrasteia.meta.operator;

import lombok.Data;

/**
 * @author <a href="mailto:youming@cai-inc.com">斜照</a>
 * @datetime 2021-12-08 18:14:04
 */
@Data
public class Operator {
    /**
     * 操作人ID
     * */
    private Long id;
    /**
     * 老用户ID<继承>
     * */
    private Long empId;
    /**
     * 机构名称
     * */
    private String institutionName;

    /**
     * 机构ID
     * */
    private Integer institutionId;
    /**
     * 操作人名称
     * */
    private String name;
    /**
     * 区划编码
     * */
    private String districtCode;
    /**
     * 联系号码
     * */
    private String phone;
    /**
     * 邮箱
     * */
    private String email;
    /**
     * 身份证
     * */
    private String idCard;
}
