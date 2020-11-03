package com.fish.demo.controller;

import com.fish.demo.service.OrderService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

/**
 * @author hh
 * @description
 * @date 2020/10/26  23:21
 */
@Controller
@RequestMapping("/order")
@Api(value = "OrderController|订单伪代码")
public class OrderController {

    private OrderService orderService;

    /**
     * @description 生成已审核的订单
     * @param params
     * @return java.util.Map
     * @date 2020/10/26 23:26
     * @author hh
     */
    @ResponseBody
    @RequestMapping(value ="/createAuditedOrder", method= RequestMethod.GET)
    @ApiOperation(value="生成已审核的订单", notes="注意：这是伪代码！")
    public Map createAuditedOrder(Map params){
        orderService.storeAuditedOrder(params);
        orderService.pushNC(params);
        orderService.pushWMS(params);
        orderService.pushTMS(params);
        orderService.pushBill(params);
        orderService.pushSettle(params);
        orderService.pushCRM(params);
        if(params.get("来源") == "线上"){
            orderService.auditPatchOrderRecord(params);
        }

        return null;
    }

    public void wmsCallBack(){}

    public void tmsCallBack(){}

    public void credentialCallBack(){}

    public void seal(){}
}
