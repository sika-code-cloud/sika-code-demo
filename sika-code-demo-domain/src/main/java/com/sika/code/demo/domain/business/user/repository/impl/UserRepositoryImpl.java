package com.sika.code.demo.domain.business.user.repository.impl;

import com.sika.code.demo.infrastructure.db.business.user.po.UserPO;
import com.sika.code.demo.infrastructure.db.business.user.mapper.UserMapper;
import com.sika.code.demo.domain.business.user.repository.UserRepository;
import com.sika.code.db.repository.impl.BaseRepositoryMyBatisPlusImpl;
import org.springframework.stereotype.Repository;
import cn.hutool.core.lang.Assert;

/**
 * <p>
 * 用户表 持久化操作实现类
 * </p>
 *
 * @author sikadai
 * @since 2022-07-30 12:59:42
 */
@Repository
public class UserRepositoryImpl extends BaseRepositoryMyBatisPlusImpl<UserPO, Long, UserMapper> implements UserRepository {

    @Override
    public void verifyUserUnExistById(Long id) {
        Assert.notNull(id, "用户表主键ID不能为空");
        UserPO po = findByPrimaryKey(id);
        Assert.notNull(po, "主键【{}】对应的用户表数据不存在，请核实", id);
    }
}

