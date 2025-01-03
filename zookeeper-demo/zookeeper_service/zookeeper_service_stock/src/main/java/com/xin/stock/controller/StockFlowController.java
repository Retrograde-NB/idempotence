package com.xin.stock.controller;

import com.xin.stock.pojo.StockFlow;
import com.xin.stock.service.StockFlowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/stockFlow")
public class StockFlowController {

    @Autowired
    private StockFlowService stockFlowService;


    @GetMapping("/findByFlag}")
    public List<StockFlow> findByFlag(@RequestParam String flag){

        return stockFlowService.findByFlag(flag);
    }
}
