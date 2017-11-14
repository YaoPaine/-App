package com.yao.lib_common.dagger.model;

import javax.inject.Inject;

/**
 * @Description:
 * @Author: YaoPaine
 * @CreateDate: 2017/11/13 下午7:57
 * @Version:
 */

public class FruitInfo {
    private int color;
    private int size;

    @Inject
    public FruitInfo() {
        this.color = 65534;
        this.size = 4;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }
}
