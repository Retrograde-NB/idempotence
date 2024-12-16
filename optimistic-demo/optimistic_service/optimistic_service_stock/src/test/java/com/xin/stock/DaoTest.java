package com.xin.stock;

import com.xin.stock.dao.StockMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class DaoTest {

    @Autowired
    private StockMapper stockMapper;

    @Test
    public void lessInventory(){

        stockMapper.lessInventory("1271700536000909312",1);
    }
}
