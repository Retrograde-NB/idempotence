package com.xin.stock;

import com.xin.stock.pojo.StockFlow;
import com.xin.stock.service.StockFlowService;
import com.xin.zookeeper.util.IdWorker;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)
public class StockFlowTest {

    @Autowired
    private StockFlowService stockFlowService;

    @Autowired
    private IdWorker idWorker;

    @Test
    public void findTest(){

        List<StockFlow> flowList = stockFlowService.findByFlag("1273436457767407616");

        System.out.println(flowList.size() ==0);
    }
}
