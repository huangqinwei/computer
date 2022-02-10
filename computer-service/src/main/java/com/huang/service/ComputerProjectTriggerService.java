package com.huang.service;

import com.huang.po.ComputerProjectTrigger;

public interface ComputerProjectTriggerService {
    /**
     * RS触发器
     * @param code
     * @param s
     * @param r
     * @return
     */
    ComputerProjectTrigger rsTrigger(String code, int s, int r);

    /**
     * 同步RS触发器
     * @param code
     * @param cp
     * @param r
     * @param s
     * @return
     */
    ComputerProjectTrigger synchronousRsTrigger(String code,int cp,int r,int s);

    /**
     * 主从RS触发器
     * @param masterCode
     * @param slaverCode
     * @param cp
     * @param r
     * @param s
     * @return
     */
    ComputerProjectTrigger masterSlaveRsTrigger(String masterCode,String slaverCode,int cp,int r,int s);
}
