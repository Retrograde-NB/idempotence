package com.xin.stock.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "stock")
@RequestMapping("/stock")
public interface StockFeign {

    @PutMapping("/reduceStock/{flag}")
    public void reduceStock(@RequestParam String orderListValue, @PathVariable("flag") String flag);
}
