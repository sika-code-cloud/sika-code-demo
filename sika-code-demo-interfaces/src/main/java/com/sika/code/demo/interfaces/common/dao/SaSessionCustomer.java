package com.sika.code.demo.interfaces.common.dao;

import cn.dev33.satoken.session.SaSession;
import cn.dev33.satoken.session.TokenSign;
import org.assertj.core.util.Lists;

import java.util.List;

/**
 * <pre>
 *
 * </pre>
 *
 * @author sikadai
 * @version 1.0
 * @since 2022/7/13 0:24
 */
public class SaSessionCustomer extends SaSession {

    public void setTokenSignList(List<TokenSignCustomer> tokenSignList) {
        List<TokenSign> tokenSigns = Lists.newArrayList();
        for (TokenSignCustomer tokenSignCustomer : tokenSignList) {
            tokenSigns.add(new TokenSign(tokenSignCustomer.getValue(), tokenSignCustomer.getDevice()));
        }
        getTokenSignList().addAll(tokenSigns);
    }
}
