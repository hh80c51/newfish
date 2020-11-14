package com.fish.framework;

import com.fish.framework.object.BaseConditionVo;
import com.fish.framework.object.PageResultVo;
import com.fish.framework.object.ResponseVo;
import com.fish.framework.utils.BeanCopyUtils;
import org.junit.Test;

/**
 * @ClassName BeanCopyTest
 * @Description bean复制工具测试
 * @Author 86131
 * @Date 2020/11/14 11:42
 * @Version 1.0
 **/
public class BeanCopyTest {

    @Test
    public void beanCopyTest(){
        BaseConditionVo baseConditionVo = new BaseConditionVo();
        baseConditionVo.setPageNum(1);
        baseConditionVo.setPageSize(100);
        BaseConditionVo baseConditionVoNew = new BaseConditionVo();
        BeanCopyUtils.copy(baseConditionVo, baseConditionVoNew);
        System.out.printf(baseConditionVoNew.getPageSize() + "");

    }
}
