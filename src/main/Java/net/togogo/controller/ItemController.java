package net.togogo.controller;

import net.togogo.common.pojo.EasyUIDataGridResult;
import net.togogo.common.utils.E3Result;
import net.togogo.pojo.TbItem;
import net.togogo.pojo.TbItemDesc;
import net.togogo.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * 商品管理controller
 */
@Controller
public class ItemController {

    @Autowired
    private ItemService itemService;

    @Value("${NORMAL}")
    private Integer NORMAL;
    @Value("${SOLD_OUT}")
    private Integer SOLD_OUT;

    @RequestMapping(value = "/item/list", method = RequestMethod.GET)
    @ResponseBody
    public EasyUIDataGridResult findItemList(Integer page,Integer rows){
        EasyUIDataGridResult result = this.itemService.findItemList(page, rows);
        return result;
    }

    @RequestMapping("/item/save")
    @ResponseBody
    public E3Result addItem(TbItem item,String desc){
        E3Result result = this.itemService.addItem(item, desc);
        return result;
    }

    @RequestMapping(value = "/rest/item/instock" , method = RequestMethod.POST)
    @ResponseBody
    public E3Result instockItem(@RequestParam("ids") long[] itemIds){
        E3Result result = this.itemService.updateStatus(itemIds,SOLD_OUT);
        return result;
    }

    @RequestMapping(value = "/rest/item/reshelf" , method = RequestMethod.POST)
    @ResponseBody
    public E3Result reshelf(@RequestParam("ids") long[] itemIds){
        E3Result result = this.itemService.updateStatus(itemIds,NORMAL);
        return result;
    }

    @RequestMapping(value = "/rest/item/delete",method = RequestMethod.POST)
    @ResponseBody
    public E3Result deleteItem(@RequestParam("ids") long[] itemIds){
        E3Result result = this.itemService.deleteItem(itemIds);
        return result;
    }

    @RequestMapping("/rest/item/query/item/desc/{itemId}")
    @ResponseBody
    public TbItemDesc findItemDesc(@PathVariable long itemId){
        TbItemDesc itemdesc = this.itemService.findItemDescById(itemId);
        return itemdesc;
    }

    @RequestMapping("/rest/item/update")
    @ResponseBody
    public E3Result updateItem(TbItem item,String desc){
        E3Result result = this.itemService.updateItem(item, desc);
        return result;
    }
}
