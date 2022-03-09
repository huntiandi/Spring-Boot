package com.yang;

import org.junit.jupiter.api.*;

import java.util.concurrent.TimeUnit;

/**
 * @ProjectName: com.yang
 * @author: ZhangBiBo
 * @description: Junit5 基本注解测试
 * @data: 2022/3/9
 */
@TestMethodOrder(MethodOrderer.MethodName.class)//指定方法执行顺序
@DisplayName("Junit5功能测试类")
public class Junit5Test {
    @DisplayName("测试displayName注解")
    @Test
    void testJunit5Test() {
        int i = 2147483647;
        i = i + 2;
        System.out.println(i);
    }

    @Test
    @Timeout(unit = TimeUnit.MILLISECONDS,value = 500)
    void testJunit5Test2() throws Exception{
        Thread.sleep(400);
        System.out.println(22222);
    }

    @Test
    @Disabled//跳过该测试方法
    void test3(){
        System.out.println(3333);
    }

    /**
     * 前置条件
     */
    @Test
    void testAssumptions(){
        Assumptions.assumeTrue(false,"结果不为真");
        System.out.println(11111);
    }
    @BeforeEach
    void beforeEach() {
        System.out.println("测试开始.....");
    }

    @AfterEach
    void afterEach() {
        System.out.println("测试结束.....");
    }

    @BeforeAll
    static void beforeAll() {
        System.out.println("所有的测试开始.....");
    }

    @AfterAll
    static void afterAll() {
        System.out.println("所有的测试结束.....");
    }
}
