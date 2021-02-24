package org.youyuan.jwt.service;

import org.youyuan.jwt.domain.UserPO;
import org.youyuan.jwt.utils.jwt.Token;
import org.youyuan.jwt.vo.request.BindEmailVO;
import org.youyuan.jwt.vo.request.EmailCodeVO;
import org.youyuan.jwt.vo.request.UpdatePasswordByCodeVO;
import org.youyuan.jwt.vo.request.UpdatePasswordByOldPwdVO;
import org.youyuan.jwt.vo.response.UserAccountVO;
import org.youyuan.jwt.vo.response.UserInfo;

import java.util.List;

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
     * @param username 账号
     * @param password 密码
     * @return
     */
    UserPO verifyUserNameAndPassword(String username, String password);

    /**
     * 退出账号
     *
     * @param username
     */
    void logoutAccount(String username);

    /**
     * 修改用户名
     *
     * @param usernameOld 旧名称
     * @param usernameNew 新名称
     */
    void updateAccountUserName(String usernameOld, String usernameNew);

    /**
     * 获取邮箱Code码
     *
     * @param emailCodeVO
     */
    void getEmailCode(EmailCodeVO emailCodeVO);

    /**
     * 绑定邮箱
     *
     * @param bindEmailVO
     */
    void bindEmail(BindEmailVO bindEmailVO);

    /**
     * 使用旧密码修改密码
     *
     * @param updatePasswordByOldPwdVO
     */
    void updateAccountPasswordByOldPwd(UpdatePasswordByOldPwdVO updatePasswordByOldPwdVO);

    /**
     * 使用邮箱验证码进行验证
     *
     * @param updatePasswordByCodeVO
     */
    void updateAccountPasswordByCode(UpdatePasswordByCodeVO updatePasswordByCodeVO);

    /**
     * 用户列表
     *
     * @param page
     * @param size
     * @return
     */
    List<UserInfo> getUserList(Integer page, Integer size);
}
