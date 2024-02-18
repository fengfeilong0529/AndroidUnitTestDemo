package com.example.unittestdemo1;

import org.junit.Before;
import org.junit.Test;

import static com.google.common.truth.Truth.assertThat;
import static com.google.common.truth.Truth.assertWithMessage;
import static org.junit.Assert.assertEquals;

/**
 * 官方文档：
 * https://developer.android.google.cn/training/testing/local-tests?hl=th
 */
public class AlgoTestTest {

    @Test
    public void testAlog1() {
    }

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void testAlog11() {
        AlgoTest a = new AlgoTest();
        int i = a.testAlog1(10);
        //junit测试
        //assertEquals断言2个数是否相等
        assertEquals(i,45);
    }

    @Test
    public void testAlog2() {
        AlgoTest a = new AlgoTest();
        String s = a.getstr();
        //普通的junit测试
//        assertEquals(a.testAlog1(11),45);


        //Truth断言库，可链式调用，更方便
        //官方文档：https://truth.dev/
        assertThat(s).contains("666");
        assertThat(s).startsWith("飞龙");
        assertThat(s).isNotEmpty();

        //如果that里的字符串不包含指定字符串，就会打印messageToPrepend
        assertWithMessage("字符串s中不包含‘哟西’")
                .that(s)
                .contains("哟西");
    }
}