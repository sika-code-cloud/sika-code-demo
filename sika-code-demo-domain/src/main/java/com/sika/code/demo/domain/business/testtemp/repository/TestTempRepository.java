package com.sika.code.demo.domain.business.testtemp.repository;

import com.sika.code.demo.infrastructure.db.business.testtemp.po.TestTempPO;
import com.sika.code.core.base.repository.BaseRepository;

/**
 * <p>
 * 测试tem表 持久化操作类
 * </p>
 *
 * @author sikadai
 * @since 2022-07-30 12:58:08
 */
public interface TestTempRepository extends BaseRepository<TestTempPO, Long> {
    /**
     * 校验ID对应的协作器是否不存在-不存在抛出异常
     * @param id : 主键ID
     */
     void verifyTestTempUnExistById(Long id);
}
