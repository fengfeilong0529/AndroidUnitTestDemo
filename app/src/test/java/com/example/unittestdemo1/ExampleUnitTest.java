package com.example.unittestdemo1;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 * 本地测试：
 * 无需运行在模拟器或真机上，无法直接运行含有Android系统api引用的测试代码，优点是速度快
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
    }
}