package com.sika.code.demo.domain.business.user.repository;

import com.sika.code.demo.infrastructure.db.business.user.po.UserPO;
import com.sika.code.core.base.repository.BaseRepository;

/**
 * <p>
 * 用户表 持久化操作类
 * </p>
 *
 * @author sikadai
 * @since 2022-07-30 12:59:41
 */
public interface UserRepository extends BaseRepository<UserPO, Long> {
    /**
     * 校验ID对应的协作器是否不存在-不存在抛出异常
     * @param id : 主键ID
     */
     void verifyUserUnExistById(Long id);
}
