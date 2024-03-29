package com.nacos.order.service.impl;


import com.nacos.order.dao.OrderDao;
import com.nacos.order.domain.OrderDO;
import com.nacos.order.fegin.AccountFeign;
import com.nacos.order.fegin.ProductFeign;
import com.nacos.order.service.OrderService;
import io.seata.core.context.RootContext;
import io.seata.spring.annotation.GlobalTransactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl implements OrderService {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private OrderDao orderDao;

    @Autowired
    private AccountFeign accountService;
    @Autowired
    private ProductFeign productService;

    @Override
    @GlobalTransactional(timeoutMills = 300000,name = "create") // <1>
    public Integer createOrder(Long userId, Long productId, Integer price) {
        Integer amount = 1; // 购买数量，暂时设置为 1。

        logger.info("[createOrder] 当前 XID: {}", RootContext.getXID());

        // <2> 扣减库存

        productService.reduceStock(productId,amount);

        // <3> 扣减余额
        accountService.reduceBalance(userId,price);

        // <4> 保存订单
        OrderDO order = new OrderDO().setUserId(userId).setProductId(productId).setPayAmount(amount * price);
        orderDao.saveOrder(order);
        logger.info("[createOrder] 保存订单: {}", order.getId());

        // 返回订单编号
        return order.getId();
    }

}
