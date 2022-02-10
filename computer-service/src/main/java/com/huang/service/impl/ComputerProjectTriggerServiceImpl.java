package com.huang.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.huang.dao.ComputerProjectTriggerDao;
import com.huang.po.ComputerProjectTrigger;
import com.huang.service.ComputerProjectTriggerService;
import com.huang.service.LogicGatesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class ComputerProjectTriggerServiceImpl implements ComputerProjectTriggerService {
    @Autowired
    private ComputerProjectTriggerDao computerProjectTriggerDao;
    @Autowired
    private LogicGatesService logicGatesService;

    /**
     * 触发器
     * @param code
     * @param r
     * @param s
     * @return
     */
    @Override
    public ComputerProjectTrigger rsTrigger(String code, int r, int s) {
        ComputerProjectTrigger rsTrigger = computerProjectTriggerDao.selectOne(new QueryWrapper<ComputerProjectTrigger>().lambda()
                .eq(ComputerProjectTrigger::getCode,code)
                .last("limit 1"));
        int q = logicGatesService.nAndGate(s,rsTrigger.getNQ());
        int nQ = logicGatesService.nAndGate(r,rsTrigger.getQ());
        rsTrigger.setQ(q);
        rsTrigger.setNQ(nQ);
        computerProjectTriggerDao.updateById(rsTrigger);
        System.out.println("Q:"+q+"nQ:"+nQ);
        return rsTrigger;
    }

    /**
     * 同步RS触发器
     * @param cp
     * @param r
     * @param s
     * @return
     */
    @Override
    public ComputerProjectTrigger synchronousRsTrigger(String code ,int cp, int r, int s) {
        int nCpAndS = logicGatesService.nAndGate(cp,s);
        int nCpAndR = logicGatesService.nAndGate(cp,r);
        return this.rsTrigger(code,nCpAndR,nCpAndS);
    }

    /**
     * 主从RS触发器
     * @param masterCode
     * @param slaverCode
     * @param cp
     * @param r
     * @param s
     * @return
     */
    @Override
    public ComputerProjectTrigger masterSlaveRsTrigger(String masterCode,String slaverCode, int cp, int r, int s) {
        //主触发器
        ComputerProjectTrigger synchronousRsTrigger = this.synchronousRsTrigger(masterCode,cp,r,s);
        int nCp = logicGatesService.notGate(cp);
        //从触发器
        return this.synchronousRsTrigger(slaverCode,nCp,synchronousRsTrigger.getNQ(),synchronousRsTrigger.getQ());
    }
}
