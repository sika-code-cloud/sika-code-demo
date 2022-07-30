package com.sika.code.demo.domain.business.testtemp.converter;
import com.sika.code.core.base.convert.BaseConverter;
import com.sika.code.demo.infrastructure.business.testtemp.pojo.dto.TestTempDTO;
import com.sika.code.demo.infrastructure.db.business.testtemp.po.TestTempPO;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;
import java.util.List;

/**
 * <p>
 * 测试tem表 转化类
 * </p>
 *
 * @author sikadai
 * @since 2022-07-30 12:58:11
 */
@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface TestTempConverter extends BaseConverter {
    /**
     * <p>
     * 转化类的单例对象
     * </p>
     */
    TestTempConverter INSTANCE = Mappers.getMapper(TestTempConverter.class);

    /**
     * 将DTO对象转化为PO对象
     */
    TestTempPO convertToPo(TestTempDTO dto);

    /**
     * 将PO对象转化为DTO对象
     */
    TestTempDTO convertToDto(TestTempPO po);

    /**
     * 将DTO对象列表转化为PO对象列表
     */
    List<TestTempPO> convertToPos(List<TestTempDTO> dtos);

    /**
     * 将PO对象列表转化为DTO对象列表
     */
    List<TestTempDTO> convertToDtos(List<TestTempPO> pos);

}
