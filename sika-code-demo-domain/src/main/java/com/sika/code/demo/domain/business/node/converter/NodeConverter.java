package com.sika.code.demo.domain.business.node.converter;
import com.sika.code.core.base.convert.BaseConverter;
import com.sika.code.demo.infrastructure.business.node.pojo.dto.NodeDTO;
import com.sika.code.demo.infrastructure.db.business.node.po.NodePO;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;
import java.util.List;

/**
 * <p>
 * 节点表 转化类
 * </p>
 *
 * @author sikadai
 * @since 2022-07-30 13:45:21
 */
@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface NodeConverter extends BaseConverter {
    /**
     * <p>
     * 转化类的单例对象
     * </p>
     */
    NodeConverter INSTANCE = Mappers.getMapper(NodeConverter.class);

    /**
     * 将DTO对象转化为PO对象
     */
    NodePO convertToPo(NodeDTO dto);

    /**
     * 将PO对象转化为DTO对象
     */
    NodeDTO convertToDto(NodePO po);

    /**
     * 将DTO对象列表转化为PO对象列表
     */
    List<NodePO> convertToPos(List<NodeDTO> dtos);

    /**
     * 将PO对象列表转化为DTO对象列表
     */
    List<NodeDTO> convertToDtos(List<NodePO> pos);

}
