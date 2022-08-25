package ${package.Entity};

import ${sikaPackage.Entity}.${sikaEntityBodyName}PO;
import ${sikaPackage.Mapper}.${sikaEntityBodyName}Mapper;
import com.sika.code.demo.domain.common.base.repository.BaseDemoRepository;

/**
 * <p>
 * ${table.comment!} 持久化操作类
 * </p>
 *
 * @author ${author}
 * @since ${date}
 */
public interface ${entity} extends BaseDemoRepository<${sikaEntityBodyName}PO${r","} ${sikaEntityBodyName}Mapper> {

}
