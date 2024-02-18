package com.example.unittestdemo1;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.*;

/**
 * 带参数的测试
 * 使用@Parameters来进行单个方法的多次不同参数的测试
 *
 * 1>在测试类上添加@RunWith(Parameterized.class)注解。
 * 2>添加构造方法，并将测试的参数作为其构造参数。
 * 3>添加获取参数集合的static方法，并在该方法上添加@Parameterized.Parameters注解。
 * 4>在需要测试的方法中直接使用成员变量，该变量由JUnit通过构造方法生成。
 *
 * 链接：https://www.jianshu.com/p/7b9396f2c1c1
 */
@RunWith(Parameterized.class)
public class CalculatorWithParamterTest {

    private final int mValue1;
    private final int mValue2;
    private final int mExpectedTotal;
    private Calculator mCalculator;

    @Before
    public void setUp() throws Exception {
        mCalculator = new Calculator();
    }

    /**
     * 构造方法，框架可以自动填充参数
     */
    public CalculatorWithParamterTest(int value1, int value2, int total) {
        mValue1 = value1;
        mValue2 = value2;
        mExpectedTotal = total;
    }

    /**
     * 需要测试的参数和对应结果
     * @return
     */
    @Parameterized.Parameters
    public static Collection<Object[]> autoFillParamter() {
        Object[][] ints = new Object[][]{{
                1, 2, 3},
                {0, 2, 22},
                {4, 2, 6},
                {14, 2, 16}
        };
        return Arrays.asList(ints);
    }

    /**
     * 直接使用成员变量来进行测试
     */
    @Test
    public void add() {
        int total = mCalculator.add(mValue1, mValue2);
        //打印：
        //1+2=3,期待的和=3
        //0+2=2,期待的和=22 此条数据不通过
        //4+2=6,期待的和=6
        //14+2=16,期待的和=16
        System.out.println(mValue1 + "+" + mValue2 + "=" + total + ",期待的和=" + mExpectedTotal);
        assertEquals(total, mExpectedTotal);
    }
}