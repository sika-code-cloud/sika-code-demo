package com.sika.code.demo.domain.business.testtemp.repository.impl;

import com.sika.code.demo.infrastructure.db.business.testtemp.po.TestTempPO;
import com.sika.code.demo.infrastructure.db.business.testtemp.mapper.TestTempMapper;
import com.sika.code.demo.domain.business.testtemp.repository.TestTempRepository;
import com.sika.code.db.repository.impl.BaseRepositoryMyBatisPlusImpl;
import org.springframework.stereotype.Repository;
import cn.hutool.core.lang.Assert;

/**
 * <p>
 * 测试tem表 持久化操作实现类
 * </p>
 *
 * @author sikadai
 * @since 2022-07-30 12:58:10
 */
@Repository
public class TestTempRepositoryImpl extends BaseRepositoryMyBatisPlusImpl<TestTempPO, Long, TestTempMapper> implements TestTempRepository {

    @Override
    public void verifyTestTempUnExistById(Long id) {
        Assert.notNull(id, "测试tem表主键ID不能为空");
        TestTempPO po = findByPrimaryKey(id);
        Assert.notNull(po, "主键【{}】对应的测试tem表数据不存在，请核实", id);
    }
}

