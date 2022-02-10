package com.huang.po;

import com.huang.service.LogicGatesService;
import com.huang.utils.LoginGateUtils;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Random Access Memory
 * 随机 存取 存储器
 */
@Data

public class Ram {
    private int q;

    public Integer storage(Integer dataBit,Integer keepBit) {
        Integer s = LoginGateUtils.andGate(dataBit,keepBit);

        Integer r = LoginGateUtils.andGate(LoginGateUtils.notGate(dataBit),keepBit);
        q = LoginGateUtils.nAndGate(r,LoginGateUtils.notGate(q));
        return q;
    }
}
