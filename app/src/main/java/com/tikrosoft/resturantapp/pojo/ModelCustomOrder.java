package com.tikrosoft.resturantapp.pojo;

public class ModelCustomOrder {
    String oid;
    String uid;
    String[] items;
    String[] qty;
    String[] unitPrice;
<<<<<<< HEAD
    String Status;
    String paymentType;
    String review;
=======
>>>>>>> 407e44f3e95d06ba4bae8f675a99d4fc9322fc6d

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
<<<<<<< HEAD

    public void setStatus(String status) {
        Status = status;
    }

    public String getStatus() {
        return Status;
    }

    public void setReview(String review) {
        this.review = review;
    }

    public String getReview() {
        return review;
    }

    public void setPaymentType(String paymentType) {
        this.paymentType = paymentType;
    }

    public String getPaymentType() {
        return paymentType;
    }
=======
>>>>>>> 407e44f3e95d06ba4bae8f675a99d4fc9322fc6d
}
