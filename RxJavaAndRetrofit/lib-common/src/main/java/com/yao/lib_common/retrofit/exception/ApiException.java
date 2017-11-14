package com.yao.lib_common.retrofit.exception;

/**
 * @Description:
 * @Author: YaoPaine
 * @CreateDate: 2017/11/9 下午9:27
 * @Version:
 */

public class ApiException extends RuntimeException {
    private String message;
    private int code;

    public ApiException(int resultCode, String message) {
        this.code = resultCode;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
