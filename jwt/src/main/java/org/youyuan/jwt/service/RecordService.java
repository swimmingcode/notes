package org.youyuan.jwt.service;

import org.youyuan.jwt.utils.jwt.Token;
import org.youyuan.jwt.vo.request.ReserveBookVO;

/**
 * @Describe: #请描述当前类的功能#
 * @Author: youjiancheng
 * @date 2021/3/6 15:23
 */
public interface RecordService {

    /**
     * 教材预定
     *
     * @param reserveBookVO
     * @param token
     */
    void textBookReserve(ReserveBookVO reserveBookVO, Token token);

    /**
     * 教材退订
     *
     * @param reserveBookVO
     * @param token
     */
    void textBookUnSubscribe(ReserveBookVO reserveBookVO, Token token);
}
