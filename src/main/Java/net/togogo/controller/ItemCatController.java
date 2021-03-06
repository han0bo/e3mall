package net.togogo.controller;

import net.togogo.common.pojo.EasyUITreeNode;
import net.togogo.service.ItemCatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * 商品分类controller
 */
@Controller
public class ItemCatController {

    @Autowired
    private ItemCatService itemCatService;

    @RequestMapping(value = "/item/cat/list" , method = RequestMethod.POST)
    @ResponseBody
    public List findItemCatType(@RequestParam(value = "id",defaultValue = "0") Long parentId){
        List<EasyUITreeNode> list = this.itemCatService.findItemCatList(parentId);
        return list;
    }

}
