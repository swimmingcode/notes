package org.youyuan.jwt.service;

import org.youyuan.jwt.domain.UserPO;
import org.youyuan.jwt.utils.jwt.Token;
import org.youyuan.jwt.vo.response.UserAccountVO;

/**
 * @Describe: #请描述当前类的功能#
 * @Author: youjiancheng
 * @Date: 2021/2/2 14:59
 */
public interface UserService {

    /**
     * 登录
     *
     * @param token
     * @return
     */
    String login(Token token);

    /**
     * 创建用户账号
     *
     * @return
     */
    UserAccountVO createAccount();


    /**
     *  验证用户名密码
     *
     * @param username
     * @param password
     * @return
     */
    UserPO verifyUserNameAndPassword(String username, String password);

    /**
     * 退出账号
     *
     * @param username
     */
    void logoutAccount(String username);
}
