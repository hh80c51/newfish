package com.fish.design.impl;

import com.fish.design.OrderAdapterService;
import com.fish.design.service.POPOrderService;

/**
 * @author 86131
 */
public class POPOrderAdapterServiceImpl implements OrderAdapterService {

    private POPOrderService popOrderService = new POPOrderService();

    @Override
    public boolean isFirst(String uId) {
        return popOrderService.isFirstOrder(uId);
    }

}
