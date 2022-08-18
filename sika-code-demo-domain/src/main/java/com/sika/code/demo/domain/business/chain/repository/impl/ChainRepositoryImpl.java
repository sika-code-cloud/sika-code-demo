package com.sika.code.demo.domain.business.chain.repository.impl;

import com.sika.code.demo.infrastructure.db.business.chain.po.ChainPO;
import com.sika.code.demo.infrastructure.db.business.chain.mapper.ChainMapper;
import com.sika.code.demo.domain.business.chain.repository.ChainRepository;
import  com.sika.code.demo.domain.common.base.repository.impl.BaseDemoRepositoryImpl;
import org.springframework.stereotype.Repository;
import cn.hutool.core.lang.Assert;

/**
 * <p>
 * 流程表 持久化操作实现类
 * </p>
 *
 * @author sikadai
 * @since 2022-08-19 00:12:15
 */
@Repository
public class ChainRepositoryImpl extends BaseDemoRepositoryImpl<ChainPO, ChainMapper> implements ChainRepository {

}

