package com.fish.design;

public interface OrderAdapterService {

    /**
     * @description 判断是否首单
     * @param uId
     * @return boolean
     * @date 2020/11/13 11:28
     * @author hehang
     */
    boolean isFirst(String uId);

}
