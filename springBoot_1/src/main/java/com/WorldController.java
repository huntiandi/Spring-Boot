package com;

import com.yang.bean.Car;
import com.yang.bean.User;
import org.springframework.beans.factory.annotation.Autowired;
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
    @Autowired
    Car car;
    @RequestMapping("/w")
    public String hello(){
        return "hello boot 你好W";
    }

    @RequestMapping("/car")
    public Car car(){
       return car;
    }
}
