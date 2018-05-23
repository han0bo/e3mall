package net.togogo.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import net.togogo.common.pojo.EasyUIDataGridResult;
import net.togogo.common.utils.E3Result;
import net.togogo.common.utils.IDUtils;
import net.togogo.mapper.TbItemDescMapper;
import net.togogo.mapper.TbItemMapper;
import net.togogo.pojo.TbItem;
import net.togogo.pojo.TbItemDesc;
import net.togogo.pojo.TbItemExample;
import net.togogo.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * 商品管理service
 */
@Service
public class ItemServiceImpl implements ItemService {

    @Autowired
    private TbItemMapper itemMapper;
    @Autowired
    private TbItemDescMapper itemDescMapper;

    @Override
    public EasyUIDataGridResult findItemList(int page, int rows) {
        //设置分页信息
        PageHelper.startPage(page,rows);
        //执行查询
        TbItemExample example = new TbItemExample();
        List<TbItem> list = this.itemMapper.selectByExample(example);
        //创建返回值类型
        EasyUIDataGridResult result = new EasyUIDataGridResult();
        result.setRows(list);
        //获取分页信息
        PageInfo<TbItem> info = new PageInfo<>(list);
        long total = info.getTotal();
        result.setTotal(total);
        return result;
    }

    @Override
    public E3Result addItem(TbItem item, String itemDesc) {
        //生成商品ID
        long id = IDUtils.genItemId();
        //补全item属性
        item.setId(id);
        //1-正常，2-下架，3-删除
        item.setStatus((byte) 1);
        item.setCreated(new Date());
        item.setUpdated(new Date());
        //向商品表中插入数据
        this.itemMapper.insert(item);

        //创建一个商品描述表对应的pojo对象
        TbItemDesc tbItemDesc = new TbItemDesc();
        //补全属性
        tbItemDesc.setItemDesc(itemDesc);
        tbItemDesc.setItemId(id);
        tbItemDesc.setCreated(new Date());
        tbItemDesc.setUpdated(new Date());

        //向商品描述表中插入数据
        this.itemDescMapper.insert(tbItemDesc);
        //返回结果

        return E3Result.ok();
    }

    @Override
    public E3Result updateStatus(long[] itemIds, int status) {
        //创建一个商品pojo类
        TbItem item = new TbItem();
        //遍历itemIds,获取id
        for (long itemId : itemIds) {
            //查询商品
            item = this.itemMapper.selectByPrimaryKey(itemId);
            //判断状态
            //1-正常，2-下架，3-删除
            if (status == 1){
                item.setStatus((byte) 1);
            }else {
                item.setStatus((byte) 2);
            }
            //更新商品状态
            this.itemMapper.updateByPrimaryKeySelective(item);
        }
        return E3Result.ok();
    }

    @Override
    public E3Result deleteItem(long[] itemIds) {
        //遍历itemIds
        for (long itemId : itemIds) {
            this.itemMapper.deleteByPrimaryKey(itemId);
            this.itemDescMapper.deleteByPrimaryKey(itemId);
        }
        return E3Result.ok();
    }

    @Override
    public TbItemDesc findItemDescById(long itemId) {
        TbItemDesc itemDesc = this.itemDescMapper.selectByPrimaryKey(itemId);
        itemDesc.setE3Result(new E3Result(null));
        return itemDesc;
    }

    @Override
    public E3Result updateItem(TbItem item, String desc) {
        //补全商品属性
        item.setUpdated(new Date());

        //执行更新
        this.itemMapper.updateByPrimaryKeySelective(item);

        //创建商品描述pojo
        TbItemDesc itemDesc = new TbItemDesc();

        //补全属性
        itemDesc.setItemDesc(desc);
        itemDesc.setItemId(item.getId());
        itemDesc.setUpdated(new Date());

        //执行更新
        this.itemDescMapper.updateByPrimaryKeySelective(itemDesc);

        return E3Result.ok();
    }
}
