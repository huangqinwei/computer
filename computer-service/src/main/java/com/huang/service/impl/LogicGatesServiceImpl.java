package com.huang.service.impl;

import com.huang.service.LogicGatesService;
import org.springframework.stereotype.Service;

@Service
public class LogicGatesServiceImpl implements LogicGatesService {
    /**
     * 与门
     * @param a 输入A
     * @param b 输入B
     * @return 输出 Y
     */
    @Override
    public int andGate(int a, int b) {
        if(a==0){
            return 0;
        }
        if(b==0){
            return 0;
        }
        return 1;
    }

    @Override
    public int nAndGate(int a, int b) {
        if(a==0){
            return 1;
        }
        if(b==0){
            return 1;
        }
        return 0;
    }

    /**
     * 或门
     * @param a 输入A
     * @param b 输入B
     * @return 输出 Y
     */
    @Override
    public int orGate(int a, int b) {
        if(a==1){
            return 1;
        }
        if(b==1){
            return 1;
        }
        return 0;
    }
    /**
     * 非门
     * @param a 输入A
     * @return 输出 Y
     */
    @Override
    public int notGate(int a) {
        if(a==0){
            return 1;
        }
        return 0;
    }
    /**
     * 异或门
     * @param a 输入A
     * @param b 输入B
     * @return 输出 Y
     */
    @Override
    public int xOrGate(int a, int b) {
        if(a!=b){
            return 1;
        }
        return 0;
    }
}
