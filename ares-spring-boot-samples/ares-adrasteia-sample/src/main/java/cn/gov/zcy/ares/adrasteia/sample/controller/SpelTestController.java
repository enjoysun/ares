package cn.gov.zcy.ares.adrasteia.sample.controller;

import cn.gov.zcy.ares.adrasteia.annotation.LogRecord;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author <a href="mailto:youming@cai-inc.com">斜照</a>
 * @datetime 2021-12-08 18:46:43
 */
@RestController
@RequestMapping("/sample/adrasteia/")
public class SpelTestController {

    @LogRecord(success = "小明操作了方法#{#name}", bixNo = 1234, condition = "#{T(org.apache.commons.lang3.StringUtils).isNotBlank(#name)}")
    @GetMapping("get")
    public String get(String name) {
        System.out.println(name);
        return String.format("123%s", name);
    }
}
