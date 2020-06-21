package com.tikrosoft.resturantapp.pojo.OrdersModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Order {

    @SerializedName("orderid")
    @Expose
    private String orderid;
    @SerializedName("oid")
    @Expose
    private String oid;
    @SerializedName("userid")
    @Expose
    private String userid;
    @SerializedName("itemid")
    @Expose
    private String itemid;
    @SerializedName("itemname")
    @Expose
    private String itemname;
    @SerializedName("qty")
    @Expose
    private String qty;
    @SerializedName("unitprice")
    @Expose
    private String unitprice;
<<<<<<< HEAD
    @SerializedName("payment_type")
    @Expose
    private String paymentType;
    @SerializedName("order_status")
    @Expose
    private String orderStatus;
    @SerializedName("review")
    @Expose
    private String review;
=======
>>>>>>> 407e44f3e95d06ba4bae8f675a99d4fc9322fc6d

    public String getOrderid() {
        return orderid;
    }

    public void setOrderid(String orderid) {
        this.orderid = orderid;
    }

    public String getOid() {
        return oid;
    }

    public void setOid(String oid) {
        this.oid = oid;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getItemid() {
        return itemid;
    }

    public void setItemid(String itemid) {
        this.itemid = itemid;
    }

    public String getItemname() {
        return itemname;
    }

    public void setItemname(String itemname) {
        this.itemname = itemname;
    }

    public String getQty() {
        return qty;
    }

    public void setQty(String qty) {
        this.qty = qty;
    }

    public String getUnitprice() {
        return unitprice;
    }

    public void setUnitprice(String unitprice) {
        this.unitprice = unitprice;
    }

<<<<<<< HEAD
    public String getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(String paymentType) {
        this.paymentType = paymentType;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }

=======
>>>>>>> 407e44f3e95d06ba4bae8f675a99d4fc9322fc6d
}
