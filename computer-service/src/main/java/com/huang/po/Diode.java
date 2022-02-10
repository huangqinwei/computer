package com.huang.po;

import lombok.Data;

/**
 * 二极管
 */
@Data
public class Diode {
    /**
     * 导通
     */
    private static final String  CONDUCTION = "1";
    /**
     * 截止
     */
    private static final String END = "0";
    /**
     * 状态:导通或截止
     */
    private String state;
    /**
     * 正极
     */
    private String positiveElectrode;
    /**
     * 负极
     */
    private String  negativeEole;

    public void setPositiveElectrode(String positiveElectrode) {
        this.positiveElectrode = positiveElectrode;
        judgmentState();
    }

    public void setNegativeEole(String negativeEole) {
        this.negativeEole = negativeEole;
        judgmentState();
    }

    /**
     * 当LED灯的正极为高电平负极为低电平时亮，其余情况为暗
     * @param positiveElectrode
     * @param negativeEole
     */
    public Diode(String positiveElectrode, String negativeEole) {
        this.positiveElectrode = positiveElectrode;
        this.negativeEole = negativeEole;
        judgmentState();
    }

    void judgmentState(){
        if ("1".equals(positiveElectrode) && "0".equals(negativeEole)){
            this.state = CONDUCTION;
        }else {
            this.state = END;
        }
    }

}
