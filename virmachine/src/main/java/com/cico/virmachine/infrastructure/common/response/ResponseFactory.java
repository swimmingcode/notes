package com.cico.virmachine.infrastructure.common.response;

/**
 * @Describe: #请描述当前类的功能#
 * @Author: youjiancheng
 * @date 2021/4/16 16:09
 */
public class ResponseFactory<T> {

    public static <T> Response<T> productResult(ResponseCode responseCode,T data) {
        return new Response<>(responseCode,data);
    }

    /**
     * 自定义Code码
     *
     * @param code
     * @param message
     * @param data
     * @param <T>
     * @return
     */
    public static <T> Response<T> productResult(int code,String message,T data) {
        return new Response<>(code,message,data);
    }

    /**
     * 当返回值为空时使用
     *
     * @param responseCode
     * @param <Void>
     * @return
     */
    public static <Void> Response<Void> productEmptyResult(ResponseCode responseCode) {
        return new Response<>(responseCode);
    }


    public static <Void> Response<Void> productEmptyResult(int code, String message) {
        return new Response<>(code,message);
    }


}