package net.togogo.service.impl;

import net.togogo.common.pojo.EasyUITreeNode;
import net.togogo.mapper.TbItemCatMapper;
import net.togogo.pojo.TbItemCat;
import net.togogo.pojo.TbItemCatExample;
import net.togogo.pojo.TbItemCatExample.Criteria;
import net.togogo.service.ItemCatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 商品分类列表service
 */
@Service
public class ItemCatServiceImpl implements ItemCatService {

    @Autowired
    private TbItemCatMapper itemCatMapper;

    @Override
    public List<EasyUITreeNode> findItemCatList(long parentId) {
        //根据parentId查询子节点数
        //设置查询条件
        //执行查询
        TbItemCatExample example = new TbItemCatExample();
        Criteria criteria = example.createCriteria();
        criteria.andParentIdEqualTo(parentId);
        List<TbItemCat> itemCats = this.itemCatMapper.selectByExample(example);

        //创建返回结果
        List<EasyUITreeNode> list = new ArrayList<EasyUITreeNode>();

        //把列表转换成EasyUITreeNode列表
        for (TbItemCat itemCat : itemCats) {
            EasyUITreeNode node = new EasyUITreeNode();

            //设置属性
            node.setId(itemCat.getId());
            node.setText(itemCat.getName());
            node.setState(itemCat.getIsParent() ?"closed":"open");

            //添加到结果列表
            list.add(node);
        }
        return list;
    }
}
