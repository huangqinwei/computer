package com.huang.service; ;

import java.util.Map;

/**
 * 算术逻辑单元
 */
public interface ArithmeticAndLogicUnitService {
    /**
     * 半加器
     * @param a 输入A
     * @param b 输入B
     * @return sum-半加和数 carry-进位数
     */
    Map<String,Integer> halfAdder(int a, int b);

    /**
     * 全加器
     * @param c Ci-1-相邻低位来的进位数
     * @param a Ai-被加数
     * @param b Bi-加数
     * @return sum-本位和 carry-进位数
     */
    Map<String,Integer> fullAdder(int c,int a,int b);

    /**
     * 加法器
     * @param a 被加数的二进制
     * @param b 加数的二进制
     * @return 和的二进制
     */
    int[] adder(int c,int[] a,int[] b,int bit);

    /**
     * 减法器
     * @param a
     * @param b
     * @param bit
     * @return
     */
    int[] subtracter(int[] a,int[] b,int bit);

    /**
     * 乘法器
     * @param a
     * @param b
     * @param bit
     * @return
     */
    int[] multiplier(int[] a,int[] b,int bit);

    int add(int a,int b);

    int subtract(int a, int b);

    int[] decimalToBinary(int decimal);

    int binaryToDecimal(int[] binary);
    String test(String test);

}
