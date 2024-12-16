package com.xin.stock.feign;

import com.xin.stock.pojo.StockFlow;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name = "stock")
@RequestMapping("/stockFlow")
public interface StockFlowFeign {

    @GetMapping("/findByFlag}")
    public List<StockFlow> findByFlag(@RequestParam String flag);
}
