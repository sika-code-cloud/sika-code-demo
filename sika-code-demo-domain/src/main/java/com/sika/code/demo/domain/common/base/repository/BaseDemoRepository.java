package com.sika.code.demo.domain.common.base.repository;

import com.sika.code.core.base.repository.BaseRepository;
import com.sika.code.demo.infrastructure.db.common.po.BaseDemoPO;

public interface BaseDemoRepository<PO extends BaseDemoPO> extends BaseRepository<PO, Long> {

}
