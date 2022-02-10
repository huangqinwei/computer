package com.huang.controller;

import com.huang.core.PubUtils;
import com.huang.http_request.service.HttpRequestService;
import com.huang.po.*;
import com.huang.service.ArithmeticAndLogicUnitService;
import com.huang.service.ComputerProjectTriggerService;
import com.huang.service.LogicGatesService;
import com.huang.service.StopService;
import com.huang.vo.RequestVo;
import com.huang.vo.TestVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/computer/test")
public class TestController {
    @Autowired
    private LogicGatesService logicGatesService;
    @Autowired
    private StopService stopService;
    @Autowired
    private ArithmeticAndLogicUnitService arithmeticAndLogicUnitService;
    @Autowired
    private HttpRequestService httpRequestService;
    @Autowired
    private ComputerProjectTriggerService computerProjectTriggerService;

    @PostMapping("/gate")
    public Integer testHttpGetByUrl(@RequestBody TestVo testVo) {
        int output = arithmeticAndLogicUnitService.add(testVo.getInputA(),testVo.getInputB());
        return output/0;
    }

    @GetMapping("/run")
    public void run() {
        stopService.run("运行中");
    }

    @GetMapping("/stop")
    public void stop() {
        stopService.stop();
    }

    @GetMapping("/request")
    public String request(@RequestBody RequestVo requestVo) {
       return httpRequestService.httpPost(requestVo.getUrl(),requestVo.getObject());
    }

    @GetMapping("/rsTrigger")
    public ComputerProjectTrigger request(@RequestBody Map<String,Integer> rs) {
        int nCp = logicGatesService.notGate(rs.get("cp"));
        computerProjectTriggerService.masterSlaveRsTrigger("0","1",nCp,rs.get("r"),rs.get("s"));
        computerProjectTriggerService.masterSlaveRsTrigger("0","1",nCp,rs.get("r"),rs.get("s"));
        computerProjectTriggerService.masterSlaveRsTrigger("0","1",rs.get("cp"),rs.get("r"),rs.get("s"));
        return computerProjectTriggerService.masterSlaveRsTrigger("0","1",rs.get("cp"),rs.get("r"),rs.get("s"));
//        return computerProjectTriggerService.synchronousRsTrigger("0",rs.get("cp"),rs.get("r"),rs.get("s"));
    }

    @GetMapping("/led")
    public String led() {
        Diode diode = new Diode(PowerSupply.POSITIVE_ELECTRODE,PowerSupply.NEGATIVE_POLE);
        return diode.getState();
    }

    @PostMapping("/ram")
    public Integer ram(@RequestBody Map<String,Integer> a) {
        Ram ram = new Ram();
        return ram.storage(a.get("dataBit"),a.get("keepBit"));
    }

    @PostMapping("/ccc")
    public void racccm() {
        Clock clock = new Clock(10);
        Thread thread = new Thread(clock);
        thread.start();
    }

}
