package com.example.unittestdemo1;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.*;

public class CalculatorTest {

    private Calculator mCalculator;

    //@BeforeClass 测试类里所有用例运行之前，运行一次这个方法。方法必须是public static void
    @BeforeClass
    public static void beforeClass() throws Exception {
        System.out.println("---beforeClass()---");
    }

    //@AfterClass 测试类里所有用例运行之后，运行一次这个方法。方法必须是public static void
    @AfterClass
    public static void afterClass() throws Exception {
        System.out.println("---afterClass()---");
    }

    //@Before 在每个测试用例运行之前都运行一次
    @Before
    public void setUp() throws Exception {
        System.out.println("---setUp()---");
        mCalculator = new Calculator();
    }

    //@After 在每个测试用例运行之后都运行一次
    @After
    public void tearDown() throws Exception {
        System.out.println("---tearDown()---");
    }

    //@Test 指定该方法为测试方法，方法必须是public void
    @Test
    public void add123() {
        System.out.println("---add123()---");
        int total = mCalculator.add(1, 2, 3);
        assertEquals(5, total);
    }

    @Test
    public void add456() {
        System.out.println("---add456()---");
        int total = mCalculator.add(4,5,6);
        assertEquals(15, total);
    }
}