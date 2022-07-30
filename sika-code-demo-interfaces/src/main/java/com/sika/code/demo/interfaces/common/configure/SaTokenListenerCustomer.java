package com.sika.code.demo.interfaces.common.configure;

import cn.dev33.satoken.listener.SaTokenListener;
import cn.dev33.satoken.stp.SaLoginModel;
import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;

/**
 * <pre>
 *
 * </pre>
 *
 * @author sikadai
 * @version 1.0
 * @since 2022/7/17 21:27
 */
@Slf4j
public class SaTokenListenerCustomer implements SaTokenListener {
    @Override
    public void doLogin(String loginType, Object loginId, String tokenValue, SaLoginModel loginModel) {
        log.info("doLogin-登录类型【{}】，登录ID：【{}】，Token值：【{}】,SaLoginModel【{}】", loginType, loginId, tokenValue, JSON.toJSONString(loginModel));
    }

    @Override
    public void doLogout(String loginType, Object loginId, String tokenValue) {
        log.info("doLogout-登录类型【{}】，登录ID：【{}】，Token值：【{}】", loginType, loginId, tokenValue);
    }

    @Override
    public void doKickout(String loginType, Object loginId, String tokenValue) {
        log.info("doKickout-登录类型【{}】，登录ID：【{}】，Token值：【{}】", loginType, loginId, tokenValue);
    }

    @Override
    public void doReplaced(String loginType, Object loginId, String tokenValue) {
        log.info("doReplaced-登录类型【{}】，登录ID：【{}】，Token值：【{}】", loginType, loginId, tokenValue);
    }

    @Override
    public void doDisable(String loginType, Object loginId, long disableTime) {
        log.info("doDisable-登录类型【{}】，登录ID：【{}】，disableTime值：【{}】", loginType, loginId, disableTime);
    }

    @Override
    public void doUntieDisable(String loginType, Object loginId) {
        log.info("doUntieDisable-登录类型【{}】，登录ID：【{}】", loginType, loginId);
    }

    @Override
    public void doCreateSession(String id) {
        log.info("doCreateSession-id【{}】", id);
    }

    @Override
    public void doLogoutSession(String id) {
        log.info("doLogoutSession-id【{}】", id);
    }
}
