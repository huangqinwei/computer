package com.huang.service.impl;;

import com.huang.service.ArithmeticAndLogicUnitService;
import com.huang.service.LogicGatesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class ArithmeticAndLogicUnitServiceImpl implements ArithmeticAndLogicUnitService {
    @Autowired
    private LogicGatesService logicGatesService;

    /**
     * 半加器
     * @param a 输入A
     * @param b 输入B
     * @return sum-半加和数 carry-进位数
     */
    @Override
    public Map<String, Integer> halfAdder(int a, int b) {
        Map<String,Integer> output = new HashMap<>();
        //半加和数为两个输入的异或
        output.put("sum",logicGatesService.xOrGate(a,b));
        //进位数为两个输入的与
        output.put("carry",logicGatesService.andGate(a,b));
        return output;
    }

    /**
     * 全加器
     * 全加器=两个半加器与一个或门的组合
     * @param c Ci-1-相邻低位来的进位数
     * @param a Ai-被加数
     * @param b Bi-加数
     * @return sum-本位和 carry-进位数
     */
    @Override
    public Map<String, Integer> fullAdder(int c, int a, int b) {
        //半加器1
        Map<String,Integer> halfAdderOutput1 = this.halfAdder(a,b);
        //半加器2
        Map<String,Integer> halfAdderOutput2 = this.halfAdder(c,halfAdderOutput1.get("sum"));
        //输出
        Map<String,Integer> output = new HashMap<>();
        output.put("sum",halfAdderOutput2.get("sum"));
        output.put("carry",logicGatesService.orGate(halfAdderOutput2.get("carry"),halfAdderOutput1.get("carry")));
        return output;
    }

    /**
     * x位加法器
     * @param a
     * @param b
     * @param bit
     * @return
     */
    @Override
    public int[] adder(int c,int[] a, int[] b,int bit) {
        //若输入未达到x位则用0补齐
        a = this.complement(a,bit);
        b = this.complement(b,bit);
        int[] output = new int[bit+1];
        int carry = c;
        for (int i=0;i<bit;i++){
            Map<String,Integer> fullAdderOutput = this.fullAdder(carry,a[i],b[i]);
            output[i] = fullAdderOutput.get("sum");
            carry = fullAdderOutput.get("carry");
        }
        output[bit] = carry;
        return output;
    }

    /**
     *减法器
     * @param a
     * @param b
     * @param bit
     * @return
     */
    @Override
    public int[] subtracter(int[] a, int[] b, int bit) {
        //若输入未达到x位则用0补齐
        a = this.complement(a,bit);
        b = this.complement(b,bit);
        for (int i=0;i<b.length;i++){
            b[i] = logicGatesService.notGate(b[i]);
        }
        int[] output = this.adder(1,a,b,bit);
        output[bit] = logicGatesService.xOrGate(output[bit],1);
        return output;
    }

    @Override
    public int[] multiplier(int[] a, int[] b, int bit) {
        return new int[0];
    }

    @Override
    public int add(int a, int b) {
        int[] inputA = this.decimalToBinary(a);
        int[] inputB = this.decimalToBinary(b);
        int bit = inputA.length>inputB.length ? inputA.length:inputB.length;
        int[] adder = this.adder(0,inputA, inputB,bit);
        return this.binaryToDecimal(adder);
    }

    @Override
    public int subtract(int a, int b) {
        int[] inputA = this.decimalToBinary(a);
        int[] inputB = this.decimalToBinary(b);
        int bit = inputA.length>inputB.length ? inputA.length:inputB.length;
//        int[] adder = this.adder(0,inputA, inputB,bit);
        int[] adder = this.subtracter(inputA, inputB,bit);
        return this.binaryToDecimal(adder);
    }

    /**
     * 二进制补位
     * @param binary
     * @param bit
     * @return
     */
    private int[] complement(int[] binary,int bit){
        int[] newBinary = new int[bit];
        for (int i=0;i<binary.length;i++){
            newBinary[i] = binary[i];
        }
        for (int i=binary.length;i<bit;i++){
            newBinary[i] = 0;
        }
        return newBinary;
    }

    /**
     * 十进制转二进制
     * @param decimal
     */
    public int[] decimalToBinary(int decimal){
        String binary = "";
        while (decimal != 0 && decimal != 1){
            binary = binary+decimal % 2;
            decimal = decimal/2;
        }
        binary =  binary + decimal;
        int[] binaryArr = new int[binary.length()];
        char[] ch = binary.toCharArray();
        for (int i = 0; i < binary.length(); i++) {
            binaryArr[i] = (int) ch[i] - 48;
        }
        return binaryArr;
    }

    /**
     * 二进制转十进制
     * @param binary
     */
    public int binaryToDecimal(int[] binary){
        int decimal = 0;
        for (int i=0;i<binary.length;i++){
            decimal =  decimal + binary[i]*(int)(Math.pow(2,i));
        }
        return decimal;
    }

    @Override
    public String test(String test) {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(test);
        return this.test(test);
    }
}
