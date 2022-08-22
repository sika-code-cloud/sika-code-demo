package com.sika.code.demo.domain.common.base.service;

import com.sika.code.core.base.service.BaseService;
import com.sika.code.demo.infrastructure.common.pojo.dto.BaseDemoDTO;

/**
 * <pre>
 *  基础示例服务接口
 * </pre>
 *
 * @author daiqi
 * @version 1.0
 * @since 2022/8/10 10:47
 */
public interface BaseDemoService<DTO extends BaseDemoDTO> extends BaseService<Long, DTO> {

}