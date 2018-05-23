package net.togogo.pojo;

import net.togogo.common.utils.E3Result;

import java.io.Serializable;
import java.util.Date;

/**
 * @author 
 */
public class TbItemDesc implements Serializable {
    /**
     * 商品ID
     */
    private Long itemId;

    /**
     * 创建时间
     */
    private Date created;

    /**
     * 更新时间
     */
    private Date updated;

    /**
     * 商品描述
     */
    private String itemDesc;

    private static final long serialVersionUID = 1L;

    private E3Result e3Result;

    public E3Result getE3Result() {
        return e3Result;
    }

    public void setE3Result(E3Result e3Result) {
        this.e3Result = e3Result;
    }

    public Long getItemId() {
        return itemId;
    }

    public void setItemId(Long itemId) {
        this.itemId = itemId;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Date getUpdated() {
        return updated;
    }

    public void setUpdated(Date updated) {
        this.updated = updated;
    }

    public String getItemDesc() {
        return itemDesc;
    }

    public void setItemDesc(String itemDesc) {
        this.itemDesc = itemDesc;
    }
}