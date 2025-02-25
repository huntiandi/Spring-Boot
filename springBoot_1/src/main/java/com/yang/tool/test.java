package com.yang.tool;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class test {
    public static void main(String[] args) {
        String content = " \n" +
                " 身故保险金 如被保险人在本合同保险期间内遭受意外伤害导致身故，我们按基本保险\n" +
                "金额的 被保险人为9人，本合同终止";
        String input = "10倍撒旦大神保险金额";
        String regex = "被保险人为([2-9]|[1-9][0-9]+)人";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(content);

        if (matcher.find()) {
            String result = matcher.group();
            System.out.println("提取到的数字+倍：" + result);
        }
    }
}
