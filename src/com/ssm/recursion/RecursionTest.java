package com.ssm.recursion;

/**
 * @author shaoshao
 * @version 1.0
 * @date 2021/9/30 13:22
 */
public class RecursionTest {
    public static void main(String[] args) {
        test(4);

    }

    //打印问题
    public static void test(int n) {
        if (n > 2) {
            test(n - 1);
        }
        System.out.println("n=" + n);
    }

    //阶层问题
    public static int factorial(int n) {
        if (n == 1) {
            return 1;
        } else {
            return factorial(n - 1) * n; // 1 * 2 * 3 * 4
        }
    }
}
