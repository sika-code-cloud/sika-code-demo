package com.sika.code.demo.domain.business.user.converter;
import com.sika.code.core.base.convert.BaseConverter;
import com.sika.code.demo.infrastructure.business.user.pojo.dto.UserDTO;
import com.sika.code.demo.infrastructure.db.business.user.po.UserPO;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;
import java.util.List;

/**
 * <p>
 * 用户表 转化类
 * </p>
 *
 * @author sikadai
 * @since 2022-07-30 12:59:43
 */
@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserConverter extends BaseConverter {
    /**
     * <p>
     * 转化类的单例对象
     * </p>
     */
    UserConverter INSTANCE = Mappers.getMapper(UserConverter.class);

    /**
     * 将DTO对象转化为PO对象
     */
    UserPO convertToPo(UserDTO dto);

    /**
     * 将PO对象转化为DTO对象
     */
    UserDTO convertToDto(UserPO po);

    /**
     * 将DTO对象列表转化为PO对象列表
     */
    List<UserPO> convertToPos(List<UserDTO> dtos);

    /**
     * 将PO对象列表转化为DTO对象列表
     */
    List<UserDTO> convertToDtos(List<UserPO> pos);

}
