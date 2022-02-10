package com.huang.service; ;

/**
 * 逻辑门
 */
public interface LogicGatesService {
    /**
     * 与门
     * @param a 输入A
     * @param b 输入B
     * @return 输出 Y
     */
    int andGate(int a, int b);
    /**
     * 与非门
     * @param a 输入A
     * @param b 输入B
     * @return 输出 Y
     */
    int nAndGate(int a,int b);
    /**
     * 或门
     * @param a 输入A
     * @param b 输入B
     * @return 输出 Y
     */
    int orGate(int a,int b);
    /**
     * 非门
     * @param a 输入A
     * @return 输出 Y
     */
    int notGate(int a);
    /**
     * 异或门
     * @param a 输入A
     * @param b 输入B
     * @return 输出 Y
     */
    int xOrGate(int a,int b);
}
