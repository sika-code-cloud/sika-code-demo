package com.sika.code.demo.domain.common.base.repository;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.sika.code.db.repository.BaseRepositoryMybatisPlus;
import com.sika.code.demo.infrastructure.db.common.mapper.BaseDemoMapper;
import com.sika.code.demo.infrastructure.db.common.po.BaseDemoPO;

import java.util.List;

public interface BaseDemoRepository<PO extends BaseDemoPO, Mapper extends BaseDemoMapper<PO>> extends BaseRepositoryMybatisPlus<PO, Long, Mapper> {

    default int logicDeleteById(Long id) {
        UpdateWrapper updateWrapper = new UpdateWrapper();
        updateWrapper.eq(ID_KEY, id);
        return logicDelete(updateWrapper);
    }

    default int logicDeleteByIds(List<Long> ids) {
        UpdateWrapper updateWrapper = new UpdateWrapper();
        updateWrapper.in(ID_KEY, ids);
        return logicDelete(updateWrapper);
    }
}
