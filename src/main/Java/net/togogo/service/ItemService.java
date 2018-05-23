package net.togogo.service;

import net.togogo.common.pojo.EasyUIDataGridResult;
import net.togogo.common.utils.E3Result;
import net.togogo.pojo.TbItem;
import net.togogo.pojo.TbItemDesc;

public interface ItemService {

    /**
     * 查找所有Item
     * @param page
     * @param rows
     * @return
     */
    EasyUIDataGridResult findItemList(int page,int rows);

    /**
     * 添加商品
     * @param item
     * @param itemDesc
     * @return
     */
    E3Result addItem(TbItem item, String itemDesc);

    /**
     * 更改商品状态
     * @param itemIds
     * @param status
     * @return
     */
    E3Result updateStatus(long[] itemIds,int status);

    /**
     * 删除商品
     * @param itemId
     * @return
     */
    E3Result deleteItem(long[] itemIds);

    /**
     * 根据ID查询商品描述
     * @param itemId
     * @return
     */
    TbItemDesc findItemDescById(long itemId);

    /**
     * 更新商品信息
     * @param item
     * @param desc
     * @return
     */
    E3Result updateItem(TbItem item,String desc);
}
