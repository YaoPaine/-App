package com.yao.lib_mvp.view;

/**
 * 作者: heyao
 * 创建时间:2017/11/1
 * 功能描述: MVP之V层 是所有VIEW的基类，其他类可以继承该类
 */

public interface IView<T> {

    /**
     * @description 全局的显示加载框
     * @author ydc
     * @createDate
     * @version 1.0
     */
    void showLoading();

    /**
     * @description 全局的显示加载框
     * @author ydc
     * @createDate
     * @version 1.0
     */
    void showLoading(String msg);

    /**
     * @description 全局的显示加载框
     * @author ydc
     * @createDate
     * @version 1.0
     */
    void showLoading(String msg, int progress);

    /**
     * @description 全局的隐藏加载框
     * @author ydc
     * @createDate
     * @version 1.0
     */
    void hideLoading();

    /**
     * @description 全局消息展示
     */
    void showMsg(String msg);

    /**
     * @description 全局错误消息展示
     */
    void showErrorMsg(String msg, String content);

    void close();

    boolean isActive();

}
