package com.huang.po;

/**
 * 时钟
 */
public class Clock implements Runnable {
    /**
     * 输出
     */
    private int output = 0;

    /**
     * 频率
     */
    private int frequency;

    public Clock(int frequency) {
        this.frequency = frequency;
    }

    @Override
    public void run() {
        while (true){
            try {
                Thread.sleep(1000/frequency);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (output == 1){
                output = 0;
            }else {
                output = 1;
            }
            System.out.println(output);
        }
    }
}
