package com.xin.stock.service.impl;

import com.xin.order.pojo.OrderDetail;
import com.xin.stock.dao.StockFlowMapper;
import com.xin.stock.dao.StockMapper;
import com.xin.stock.pojo.Stock;
import com.xin.stock.pojo.StockFlow;
import com.xin.stock.service.StockService;
import com.xin.zookeeper.lock.HighLock;
import com.xin.zookeeper.util.IdWorker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class StockServiceImpl implements StockService {

    @Autowired
    private StockMapper stockMapper;

    @Autowired
    private StockFlowMapper stockFlowMapper;

    @Autowired
    private IdWorker idWorker;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean reduceStock(List<OrderDetail> orderDetailList, String flag) {
        String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
        HighLock highLock = new HighLock("/" + methodName);
        try {
            // zk加锁
            highLock.getLock();
            orderDetailList.stream().forEach(orderDetail -> {
                //扣减库存
                int reduceStockResult = stockMapper.reduceStock(orderDetail.getGoodsId(), orderDetail.getGoodsNum());
                if (reduceStockResult != 1){
                    //扣减库存失败
                    throw new RuntimeException("扣减库存失败");
                }
                //新增库存流水
                StockFlow stockFlow = new StockFlow();
                stockFlow.setId(String.valueOf(idWorker.nextId()));
                stockFlow.setFlag(flag);
                stockFlow.setGoodsId(orderDetail.getGoodsId());
                stockFlow.setNum(orderDetail.getGoodsNum());
                stockFlowMapper.insert(stockFlow);
            });
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            highLock.releaseLock();
        }
        return false;
    }

    @Override
    public Stock getStockInfo(String goodsId) {
        Stock stock = stockMapper.findByGoodsId(goodsId);
        return stock;
    }
}
