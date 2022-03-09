package com.yang;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @ProjectName: com.yang
 * @author: ZhangBiBo
 * @description: assertions断言机制测试
 * @data: 2022/3/9
 */
public class AssertionsTest {
    @Test
    @DisplayName("简单 assertion")
    public void simple() {
        assertEquals(3, 1 + 2, "simple math");
        assertNotEquals(3, 1 + 1);

        assertNotSame(new Object(), new Object());
        Object obj = new Object();
        assertSame(obj, obj);

        assertFalse(1 > 2);
        assertTrue(1 < 2);

        assertNull(null);
        assertNotNull(new Object());
    }

    /**
     * 数组断言测试
     */
    @Test
    @DisplayName("数组 断言")
    public void array(){
        assertArrayEquals(new int []{1,2},new int[]{1,2},"数组结果不一样");
    }

    /**
     * 组合断言
     */
    @Test
    @DisplayName("组合 断言")
    public void all(){
        assertAll("标题一",
                ()->assertEquals(1,1,"结果不一致"),
                ()->assertTrue(1>2,"结果为假"));
    }

    @Test
    @DisplayName("异常测试")
    public void exceptionTest() {
        ArithmeticException exception = Assertions.assertThrows(
                //扔出断言异常
                ArithmeticException.class, () -> System.out.println(1 % 0));

    }

    @Test
    @DisplayName("超时测试")
    public void timeoutTest() {
        //如果测试方法时间超过1s将会异常
        Assertions.assertTimeout(Duration.ofMillis(1000), () -> Thread.sleep(500));
    }

    /**
     * 快速失败
     */
    @Test
    @DisplayName("fail")
    public void shouldFail() {
        fail("This should fail");
    }
}
