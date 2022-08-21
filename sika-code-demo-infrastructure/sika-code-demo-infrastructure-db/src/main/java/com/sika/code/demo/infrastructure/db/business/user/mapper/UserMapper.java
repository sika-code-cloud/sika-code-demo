package com.sika.code.demo.infrastructure.db.business.user.mapper;

import com.sika.code.demo.infrastructure.business.user.pojo.query.UserQuery;
import com.sika.code.demo.infrastructure.db.business.user.po.UserPO;
import com.sika.code.db.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 用户表 Mapper 接口
 * </p>
 *
 * @author sikadai
 * @since 2022-07-30 12:59:38
 */
public interface UserMapper extends BaseMapper<UserPO, Long> {
    List<UserPO> listAsc(@Param(value = "query") UserQuery query);
}
