package com.fish.demo.service;

import java.util.Map;

/**
 * @author hh
 * @description
 * @date 2020/10/26  23:33
 */
public interface OrderService {

    public void storeAuditedOrder(Map params);
    /**
     * @description 推NC
     * @param params
     * @return java.util.Map
     * @date 2020/10/26 23:26
     * @author hh
     */
    public void pushNC(Map params);

    public void pushWMS(Map params);

    public void pushTMS(Map params);

    public void pushBill(Map params);

    public void pushSettle(Map params);

    public void pushCRM(Map params);

    public void auditPatchOrderRecord(Map params);
}
