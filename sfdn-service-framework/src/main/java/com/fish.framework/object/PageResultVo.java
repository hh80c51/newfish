package com.fish.framework.object;

import java.util.List;

/**
 * 用于bootstrap table返回json格式
 *
 * @program:hope-boot
 * @author:aodeng
 * @blog:低调小熊猫(https://aodeng.cc)
 * @微信公众号:低调小熊猫
 * @create:2018-10-22 15:16
 **/
public class PageResultVo {
    private Long total;
    private List rows;

    public PageResultVo(Long total, List rows) {
        this.total = total;
        this.rows = rows;
    }

    public PageResultVo() {
    }

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public List getRows() {
        return rows;
    }

    public void setRows(List rows) {
        this.rows = rows;
    }
}
