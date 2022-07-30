package com.sika.code.demo.application.business.testtemp.service.impl;

import com.sika.code.demo.infrastructure.business.testtemp.pojo.dto.TestTempDTO;
import com.sika.code.demo.infrastructure.db.business.testtemp.po.TestTempPO;
import com.sika.code.demo.domain.business.testtemp.repository.TestTempRepository;
import com.sika.code.demo.application.business.testtemp.service.TestTempService;
import com.sika.code.core.base.service.BaseServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 测试tem表 服务实现类
 * </p>
 *
 * @author sikadai
 * @since 2022-07-30 12:58:19
 */
@Service
public class TestTempServiceImpl extends BaseServiceImpl<Long, TestTempPO, TestTempDTO, TestTempRepository> implements TestTempService {

}
