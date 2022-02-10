package com.huang.po;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * LED灯
 */
@Data
public class LedLamp {
    /**
     * 亮
     */
    private static final String BRIGHT = "1";

    /**
     * 暗
     */
    private static final String  DARK = "0";
    /**
     * 亮或暗
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
    }

    public void setNegativeEole(String negativeEole) {
        this.negativeEole = negativeEole;
    }

    /**
     * 当LED灯的正极为高电平负极为低电平时亮，其余情况为暗
     * @param positiveElectrode
     * @param negativeEole
     */
    public LedLamp(String positiveElectrode, String negativeEole) {
        this.positiveElectrode = positiveElectrode;
        this.negativeEole = negativeEole;
        if ("1".equals(positiveElectrode) && "0".equals(negativeEole)){
            this.state = BRIGHT;
        }else {
            this.state = DARK;
        }
    }
}
