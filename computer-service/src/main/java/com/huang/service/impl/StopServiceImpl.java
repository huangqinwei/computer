package com.huang.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.huang.dao.StopDao;
import com.huang.po.Stop;
import com.huang.service.StopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StopServiceImpl implements StopService {
    @Autowired
    private StopDao stopDao;

    static int num = 0;

    @Override
    public String run(String text) {
        num++;
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Stop stop = stopDao.selectOne(new QueryWrapper<Stop>().lambda()
                .eq(Stop::getCode,"1")
                .last("limit 1"));
        if ("1".equals(stop.getStop())){
            stop.setStop("0");
            stopDao.updateById(stop);
            System.out.println("停止");
            return null;
        }
        System.out.println(text+num);
        return this.run(text);
    }

    @Override
    public void stop() {
        Stop stop = stopDao.selectOne(new QueryWrapper<Stop>().lambda()
                .eq(Stop::getCode,"1")
                .last("limit 1"));
        stop.setStop("1");
        stopDao.updateById(stop);
    }
}
