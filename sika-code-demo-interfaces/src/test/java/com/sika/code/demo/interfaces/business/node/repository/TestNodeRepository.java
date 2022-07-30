package com.sika.code.demo.interfaces.business.node.repository;


import com.sika.code.core.base.test.BaseTestRepository;
import com.google.common.collect.Lists;
import com.sika.code.demo.domain.business.node.repository.NodeRepository;
import com.sika.code.demo.infrastructure.db.business.node.po.NodePO;
import com.sika.code.demo.infrastructure.business.node.pojo.query.NodeQuery;
import com.sika.code.demo.interfaces.SikaCodeDemoApplication;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;
import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 节点表持久化操作测试类
 * </p>
 *
 * @author sikadai
 * @since 2022-07-30 13:45:32
 */
@SpringBootTest(classes = SikaCodeDemoApplication.class)
public class TestNodeRepository extends BaseTestRepository {
    @Resource
    private NodeRepository nodeRepository;

    @Test
    public void testFindByPrimaryKey() {
        Long primaryKey = 1L;
        NodePO nodePO = nodeRepository.findByPrimaryKey(primaryKey);
        Assert.assertNotNull(nodePO);
    }

    @Test
    public void testUpdateSelectiveByPrimaryKey() {
        NodePO nodePO = buildNodePO();
        nodePO.setId(null);
        int count = nodeRepository.save(nodePO);
        Assert.assertTrue(count > 0);
    }

    @Test
    public void testInsertSelectiveRetPrimaryKey() {
        NodePO nodePO = buildNodePO();
        Long primaryKey = nodeRepository.saveRetId(nodePO);
        Assert.assertTrue(primaryKey > 0);
    }

    @Test
    public void testInsertSelective() {
        NodePO nodePO = buildNodePO();
        int count = nodeRepository.save(nodePO);
        Assert.assertTrue(count > 0);
    }

    @Test
    public void testInsertBatchSelective() {
        List<NodePO> pos = Lists.newArrayList();
        for (int i = 0; i < 10; ++i) {
            NodePO nodePO = buildNodePO();
            pos.add(nodePO);
        }
        int count = nodeRepository.saveBatch(pos);
        Assert.assertTrue(count > 0);
    }


    @Test
    public void testUpdateBatchSelectiveByPrimaryKey() {
        List<NodePO> pos = Lists.newArrayList();
        for (int i = 0; i < 10; ++i) {
            NodePO nodePO = buildNodePO();
            pos.add(nodePO);
        }
        int count = nodeRepository.saveBatch(pos);
        Assert.assertTrue(count > 0);
    }

    @Test
    public void testFind() {
        NodeQuery nodeQuery = buildNodeQuery();
        NodePO po = nodeRepository.find(nodeQuery);
        Assert.assertNotNull(po);
    }

    @Test
    public void testFindId() {
        NodeQuery nodeQuery = buildNodeQuery();
        Long primaryKey = nodeRepository.findId(nodeQuery);
        Assert.assertTrue(primaryKey > 0);
    }

    @Test
    public void testList() {
        NodeQuery nodeQuery = buildNodeQuery();
        List<NodePO> pos = nodeRepository.list(nodeQuery);
        Assert.assertTrue(pos.size() > 0);
    }

    @Test
    public void testListId() {
        NodeQuery nodeQuery = buildNodeQuery();
        List<Long> primarys = nodeRepository.listId(nodeQuery);
        Assert.assertTrue(primarys.size() > 0);
    }

    @Test
    public void testPage() {
        NodeQuery nodeQuery = buildNodeQuery();
        List<NodePO> pos = nodeRepository.page(nodeQuery);
        Assert.assertTrue(pos.size() > 0);
    }

    @Test
    public void testCount() {
        NodeQuery nodeQuery = buildNodeQuery();
        int count = nodeRepository.count(nodeQuery);
        Assert.assertTrue(count > 0);
    }


    private NodePO buildNodePO() {
        NodePO nodePO = new NodePO();
        nodePO.setId(null);
        nodePO.setCreateBy(null);
        nodePO.setUpdateBy(null);
        nodePO.setNodeNo(null);
        nodePO.setNodeName(null);
        nodePO.setNodeClazz(null);
        nodePO.setNodeType(null);
        nodePO.setNodeScript(null);
        nodePO.setTenantId(null);
        nodePO.setApplicationId(null);
        return nodePO;
    }
    
    private NodeQuery buildNodeQuery() {
        NodeQuery nodeQuery = new NodeQuery();
        nodeQuery.setCreateBy(null);
        nodeQuery.setUpdateBy(null);
        nodeQuery.setNodeNo(null);
        nodeQuery.setNodeName(null);
        nodeQuery.setNodeClazz(null);
        nodeQuery.setNodeType(null);
        nodeQuery.setNodeScript(null);
        nodeQuery.setTenantId(null);
        nodeQuery.setApplicationId(null);
        return nodeQuery;
    }
}