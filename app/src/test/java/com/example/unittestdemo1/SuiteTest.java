package com.example.unittestdemo1;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * 套件测试
 * 通俗讲就是批量测试，可以测试多个测试类
 * 被测试类：AlgoTestTest.class, CalculatorTest.class, CalculatorWithParamterTest.class
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({AlgoTestTest.class, CalculatorTest.class, CalculatorWithParamterTest.class})
public class SuiteTest {
}
