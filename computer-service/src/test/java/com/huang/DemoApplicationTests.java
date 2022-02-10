package com.huang;

import com.huang.po.Run;
import com.huang.service.ArithmeticAndLogicUnitService;
import com.huang.service.StopService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Timer;

@SpringBootTest
class DemoApplicationTests {
    @Autowired
    private ArithmeticAndLogicUnitService arithmeticAndLogicUnitService;
    @Autowired
    private StopService stopService;
    @Test
    void Test(){
        Timer timer = new Timer();
        timer.schedule(new Run(), 1000, 1000);
    }
}
