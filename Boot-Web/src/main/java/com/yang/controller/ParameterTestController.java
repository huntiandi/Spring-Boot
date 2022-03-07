package com.yang.controller;

import org.springframework.ui.Model;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.web.bind.annotation.*;
import sun.management.Agent;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ProjectName: com.yang.controller
 * @author: ZhangBiBo
 * @description: 测试请求参数
 * @data: 2022/3/7
 */
@RestController
public class ParameterTestController {
    /**
     *PathVariable找个注解是获取GetMapping中的参数，RequestParam找个注解是获取访问连接中？后面拼接的参数
     * @return
     */
    @GetMapping("car/{id}/owner/{username}")
    public Map<String,Object> getCar(@PathVariable("id") Integer id,
                                     @PathVariable("username") String username,
                                     @PathVariable Map<String,String> pv,
                                     @RequestHeader("User-Agent") String UserAgent,
                                     @RequestHeader Map<String,String> header,
                                     @RequestParam(name = "age") Integer age,
                                     @RequestParam("hobby")List<String> hobby,
                                     //直接获取所有的参数，放进一个map
                                     @RequestParam Map<String,String> params,
                                     //因为有重复的key,例如hobby等于篮球，足球，最后就是hobby=足球，篮球会被覆盖，所以要使用找个map，可以指向多个value
                                     @RequestParam LinkedMultiValueMap<String,String> LParams,
                                     @CookieValue("Idea-8296e76f")String ga){
        HashMap<String, Object> map = new HashMap<>();
        map.put("id",id);
        map.put("username",username);
        map.put("pv:",pv);
        map.put("User-Agent",UserAgent);
//        map.put("header",header);
        map.put("age",age);
        map.put("hobby",hobby);
        map.put("params",params);
        map.put("LParams",LParams);
        map.put("cookie",ga);
        return map;
    }

    /**1、语法： 请求路径：/cars/sell;low=34;brand=byd,audi,yd
       2、SpringBoot默认是禁用了矩阵变量的功能
         手动开启：原理。对于路径的处理。UrlPathHelper进行解析。removeSemicolonContent（移除分号内容）支持矩阵变量的
       3、矩阵变量必须有url路径变量才能被解析
     **/
    @GetMapping("/cars/{path}")
    public Map carsSell(@MatrixVariable("low") Integer low,
                        @MatrixVariable("brand") List<String> brand,
                        @PathVariable("path") String path){
        Map<String,Object> map = new HashMap<>();

        map.put("low",low);
        map.put("brand",brand);
        map.put("path",path);
        return map;
    }

    /*复杂参数的请求，测试map,model*/
    @GetMapping("/params")
    public String testParams(Map<String,Object> map,
                             Model model,
                             HttpServletRequest request,
                             HttpServletResponse response){
        return "forward:/success";
    }
}
