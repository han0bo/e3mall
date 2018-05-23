package net.togogo.service;

import net.togogo.common.pojo.EasyUITreeNode;

import java.util.List;

public interface ItemCatService {

    List<EasyUITreeNode> findItemCatList(long parentId);

}
