package com.tikrosoft.resturantapp.pojo;

public class ModelCustomAdminOrder {
    String oid;
    String uid;
    String[] items;
    String[] qty;
    String[] unitPrice;
    String Status;
    String paymentType;
    String review;

    public String getOid() {
        return oid;
    }

    public void setOid(String oid) {
        this.oid = oid;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public void setUnitPrice(String[] unitPrice) {
        this.unitPrice = unitPrice;
    }

    public String[] getUnitPrice() {
        return unitPrice;
    }

    public void setItems(String[] items) {
        this.items = items;
    }

    public String[] getItems() {
        return items;
    }

    public void setQty(String[] qty) {
        this.qty = qty;
    }

    public String[] getQty() {
        return qty;
    }

    public void setPaymentType(String paymentType) {
        this.paymentType = paymentType;
    }

    public void setStatus(String status) {
        Status = status;
    }

    public String getPaymentType() {
        return paymentType;
    }

    public String getStatus() {
        return Status;
    }

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }
}
