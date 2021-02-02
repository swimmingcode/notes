package org.youyuan.jwt.utils.common.response;

/**
 * @Describe: 工厂模式构建返回前端数据
 * @Author: youjiancheng
 * @Date: 2021/2/2 15:17
 */
public class ResponseFactory<T> {

    public static <T> Response<T> productResult(ResponseCode responseCode,T data) {
        return new Response<>(responseCode,data);
    }

    public static <T> Response<T> productResult(int code,String message,T data) {
        return new Response<>(code,message,data);
    }

    public static <Void> Response<Void> productEmptyResult(ResponseCode responseCode) {
        return new Response<>(responseCode);
    }


    public static <Void> Response<Void> productEmptyResult(int code, String message) {
        return new Response<>(code,message);
    }


}
