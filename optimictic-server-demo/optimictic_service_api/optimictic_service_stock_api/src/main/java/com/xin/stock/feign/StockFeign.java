package com.xin.stock.feign;

import com.xin.stock.pojo.Stock;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient(name = "stock")
@RequestMapping("/stock")
public interface StockFeign {


    @PutMapping("/reduceStockNoLock/{goodsId}/{num}")
    public String reduceStockNoLock(@PathVariable("goodsId") String goodsId,
                                    @PathVariable("num") Integer num);


    @PutMapping("/reduceStock/{goodsId}/{num}/{version}")
    public int reduceStock(@PathVariable("goodsId") String goodsId,
                              @PathVariable("num") Integer num,
                              @PathVariable("version") Integer version);

    @GetMapping("/getStockInfo/{goodsId}")
    public Stock getStockInfo(@PathVariable("goodsId") String goodsId);

    @GetMapping("/demo")
    public void demo();
}
