package com.sika.code.demo.domain.business.node.converter;

import com.sika.code.demo.infrastructure.business.node.pojo.dto.NodeDTO;
import com.sika.code.demo.infrastructure.db.business.node.po.NodePO;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-07-30T13:46:31+0800",
    comments = "version: 1.3.1.Final, compiler: javac, environment: Java 1.8.0_161 (Oracle Corporation)"
)
public class NodeConverterImpl implements NodeConverter {

    @Override
    public NodePO convertToPo(NodeDTO dto) {
        if ( dto == null ) {
            return null;
        }

        NodePO nodePO = new NodePO();

        nodePO.setCreateDate( dto.getCreateDate() );
        nodePO.setUpdateDate( dto.getUpdateDate() );
        nodePO.setVersion( dto.getVersion() );
        nodePO.setAvailable( dto.getAvailable() );
        nodePO.setIsDeleted( dto.getIsDeleted() );
        nodePO.setRemark( dto.getRemark() );
        nodePO.setId( dto.getId() );
        nodePO.setCreateBy( dto.getCreateBy() );
        nodePO.setUpdateBy( dto.getUpdateBy() );
        nodePO.setNodeNo( dto.getNodeNo() );
        nodePO.setNodeName( dto.getNodeName() );
        nodePO.setNodeClazz( dto.getNodeClazz() );
        nodePO.setNodeType( dto.getNodeType() );
        nodePO.setNodeScript( dto.getNodeScript() );
        nodePO.setTenantId( dto.getTenantId() );
        nodePO.setApplicationId( dto.getApplicationId() );

        return nodePO;
    }

    @Override
    public NodeDTO convertToDto(NodePO po) {
        if ( po == null ) {
            return null;
        }

        NodeDTO nodeDTO = new NodeDTO();

        nodeDTO.setId( po.getId() );
        nodeDTO.setCreateDate( po.getCreateDate() );
        nodeDTO.setUpdateDate( po.getUpdateDate() );
        nodeDTO.setVersion( po.getVersion() );
        nodeDTO.setAvailable( po.getAvailable() );
        nodeDTO.setIsDeleted( po.getIsDeleted() );
        nodeDTO.setRemark( po.getRemark() );
        nodeDTO.setCreateBy( po.getCreateBy() );
        nodeDTO.setUpdateBy( po.getUpdateBy() );
        nodeDTO.setNodeNo( po.getNodeNo() );
        nodeDTO.setNodeName( po.getNodeName() );
        nodeDTO.setNodeClazz( po.getNodeClazz() );
        nodeDTO.setNodeType( po.getNodeType() );
        nodeDTO.setNodeScript( po.getNodeScript() );
        nodeDTO.setTenantId( po.getTenantId() );
        nodeDTO.setApplicationId( po.getApplicationId() );

        return nodeDTO;
    }

    @Override
    public List<NodePO> convertToPos(List<NodeDTO> dtos) {
        if ( dtos == null ) {
            return null;
        }

        List<NodePO> list = new ArrayList<NodePO>( dtos.size() );
        for ( NodeDTO nodeDTO : dtos ) {
            list.add( convertToPo( nodeDTO ) );
        }

        return list;
    }

    @Override
    public List<NodeDTO> convertToDtos(List<NodePO> pos) {
        if ( pos == null ) {
            return null;
        }

        List<NodeDTO> list = new ArrayList<NodeDTO>( pos.size() );
        for ( NodePO nodePO : pos ) {
            list.add( convertToDto( nodePO ) );
        }

        return list;
    }
}
