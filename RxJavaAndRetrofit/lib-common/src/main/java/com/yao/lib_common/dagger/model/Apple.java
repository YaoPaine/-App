package com.yao.lib_common.dagger.model;

/**
 * @Description:
 * @Author: YaoPaine
 * @CreateDate: 2017/11/13 下午5:45
 * @Version:
 */

public class Apple extends Fruit {
    private int color;
    private int size;

    public Apple(int color, int size) {
        this.color = color;
        this.size = size;
    }

    public Apple(FruitInfo fruitInfo) {
        if (fruitInfo == null) throw new NullPointerException("fruitInfo can not be null");
        this.color = fruitInfo.getColor();
        this.size = fruitInfo.getSize();
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

    @Override
    public String toString() {
        return "Apple{" +
                "color=" + color +
                ", size=" + size +
                '}';
    }
}
