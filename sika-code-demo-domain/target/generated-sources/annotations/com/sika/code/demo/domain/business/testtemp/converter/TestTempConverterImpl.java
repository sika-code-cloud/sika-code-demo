package com.sika.code.demo.domain.business.testtemp.converter;

import com.sika.code.demo.infrastructure.business.testtemp.pojo.dto.TestTempDTO;
import com.sika.code.demo.infrastructure.db.business.testtemp.po.TestTempPO;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-07-30T13:43:57+0800",
    comments = "version: 1.3.1.Final, compiler: javac, environment: Java 1.8.0_161 (Oracle Corporation)"
)
public class TestTempConverterImpl implements TestTempConverter {

    @Override
    public TestTempPO convertToPo(TestTempDTO dto) {
        if ( dto == null ) {
            return null;
        }

        TestTempPO testTempPO = new TestTempPO();

        testTempPO.setCreateDate( dto.getCreateDate() );
        testTempPO.setUpdateDate( dto.getUpdateDate() );
        testTempPO.setVersion( dto.getVersion() );
        testTempPO.setAvailable( dto.getAvailable() );
        testTempPO.setIsDeleted( dto.getIsDeleted() );
        testTempPO.setRemark( dto.getRemark() );
        testTempPO.setId( dto.getId() );
        testTempPO.setCreateBy( dto.getCreateBy() );
        testTempPO.setUpdateBy( dto.getUpdateBy() );
        testTempPO.setTestNo( dto.getTestNo() );
        testTempPO.setTestName( dto.getTestName() );
        testTempPO.setRecordDate( dto.getRecordDate() );

        return testTempPO;
    }

    @Override
    public TestTempDTO convertToDto(TestTempPO po) {
        if ( po == null ) {
            return null;
        }

        TestTempDTO testTempDTO = new TestTempDTO();

        testTempDTO.setId( po.getId() );
        testTempDTO.setCreateDate( po.getCreateDate() );
        testTempDTO.setUpdateDate( po.getUpdateDate() );
        testTempDTO.setVersion( po.getVersion() );
        testTempDTO.setAvailable( po.getAvailable() );
        testTempDTO.setIsDeleted( po.getIsDeleted() );
        testTempDTO.setRemark( po.getRemark() );
        testTempDTO.setCreateBy( po.getCreateBy() );
        testTempDTO.setUpdateBy( po.getUpdateBy() );
        testTempDTO.setTestNo( po.getTestNo() );
        testTempDTO.setTestName( po.getTestName() );
        testTempDTO.setRecordDate( po.getRecordDate() );

        return testTempDTO;
    }

    @Override
    public List<TestTempPO> convertToPos(List<TestTempDTO> dtos) {
        if ( dtos == null ) {
            return null;
        }

        List<TestTempPO> list = new ArrayList<TestTempPO>( dtos.size() );
        for ( TestTempDTO testTempDTO : dtos ) {
            list.add( convertToPo( testTempDTO ) );
        }

        return list;
    }

    @Override
    public List<TestTempDTO> convertToDtos(List<TestTempPO> pos) {
        if ( pos == null ) {
            return null;
        }

        List<TestTempDTO> list = new ArrayList<TestTempDTO>( pos.size() );
        for ( TestTempPO testTempPO : pos ) {
            list.add( convertToDto( testTempPO ) );
        }

        return list;
    }
}
