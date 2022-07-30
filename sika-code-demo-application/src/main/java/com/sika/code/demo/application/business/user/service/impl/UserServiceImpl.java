package com.sika.code.demo.application.business.user.service.impl;

import com.sika.code.demo.infrastructure.business.user.pojo.dto.UserDTO;
import com.sika.code.demo.infrastructure.db.business.user.po.UserPO;
import com.sika.code.demo.domain.business.user.repository.UserRepository;
import com.sika.code.demo.application.business.user.service.UserService;
import com.sika.code.core.base.service.BaseServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author sikadai
 * @since 2022-07-30 12:59:52
 */
@Service
public class UserServiceImpl extends BaseServiceImpl<Long, UserPO, UserDTO, UserRepository> implements UserService {

}
