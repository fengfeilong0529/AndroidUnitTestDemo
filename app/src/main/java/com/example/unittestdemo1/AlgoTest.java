package com.example.unittestdemo1;

public class AlgoTest {

    public int testAlog1(int n) {
        int total=0;
        for (int i = 0; i < n; i++) {
            total+=i;
        }
        System.out.println("输出结果======"+total);
        return total;
    }

    public String getstr() {
        return "飞龙 哈哈 666";
    }
}
