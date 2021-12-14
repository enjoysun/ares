package cn.gov.zcy.ares.adrasteia.sample.config;

/**
 * @author <a href="mailto:youming@cai-inc.com">斜照</a>
 * @datetime 2021-12-10 11:01:10
 */
public class PersonAware {
    public String sayHobby(String hobby) {
        System.out.println(hobby);
        return String.format("我的爱好%s", hobby);
    }
}
