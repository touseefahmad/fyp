package com.tikrosoft.resturantapp.pojo.item;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Item implements Serializable {

    @SerializedName("menu_id")
    @Expose
    private String menuId;
    @SerializedName("rest_id")
    @Expose
    private String restId;
    @SerializedName("menu_item_name")
    @Expose
    private String menuItemName;
    @SerializedName("menu_item_type")
    @Expose
    private String menuItemType;
    @SerializedName("menu_item_price")
    @Expose
    private String menuItemPrice;
    @SerializedName("menu_item_description")
    @Expose
    private String menuItemDescription;

    public String getMenuId() {
        return menuId;
    }

    public void setMenuId(String menuId) {
        this.menuId = menuId;
    }

    public String getRestId() {
        return restId;
    }

    public void setRestId(String restId) {
        this.restId = restId;
    }

    public String getMenuItemName() {
        return menuItemName;
    }

    public void setMenuItemName(String menuItemName) {
        this.menuItemName = menuItemName;
    }

    public String getMenuItemType() {
        return menuItemType;
    }

    public void setMenuItemType(String menuItemType) {
        this.menuItemType = menuItemType;
    }

    public String getMenuItemPrice() {
        return menuItemPrice;
    }

    public void setMenuItemPrice(String menuItemPrice) {
        this.menuItemPrice = menuItemPrice;
    }

    public String getMenuItemDescription() {
        return menuItemDescription;
    }

    public void setMenuItemDescription(String menuItemDescription) {
        this.menuItemDescription = menuItemDescription;
    }

}
