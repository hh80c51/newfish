package com.fish.design.impl;

import com.fish.design.OrderAdapterService;
import com.fish.design.service.OrderService;

/**
 * @author 86131
 */
public class InsideOrderServiceImpl implements OrderAdapterService {

    private OrderService orderService = new OrderService();

    @Override
    public boolean isFirst(String uId) {
        return orderService.queryUserOrderCount(uId) <= 1;
    }

}
