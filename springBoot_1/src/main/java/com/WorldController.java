package com;

import com.yang.bean.User;
import org.springframework.context.annotation.Import;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ProjectName: com.yang
 * @author: ZhangBiBo
 * @description:
 * @data: 2022/2/16
 */
@Import({User.class})
@RestController
public class WorldController {
    @RequestMapping("/w")
    public String hello(){
        return "hello boot 你好W";
    }
}
