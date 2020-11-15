package com.fish.demo.bean;

import lombok.Data;

/**
 * @author hh
 * @description
 * @date 2020/11/15  16:16
 */
@Data
public class Point {
    private double horizontal;

    private double vertical;

    public Point(int horizontal, int vertical) {
        this.horizontal = horizontal;
        this.vertical = vertical;
    }

    //到坐标(x,y)的距离，写个伪计算公式
    public double distance(int x, int y){
        return horizontal - x + vertical - y;
    }
}
