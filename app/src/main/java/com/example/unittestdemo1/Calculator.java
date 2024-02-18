package com.example.unittestdemo1;

public class Calculator {

    /**
     * 求和
     *
     * @param value
     * @return
     */
    public int add(int... value) {
        int total = 0;
        for (int i : value) {
            total += i;
        }
        return total;
    }
}
