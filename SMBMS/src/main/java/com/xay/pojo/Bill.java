package com.xay.pojo;

import java.util.Date;

public class Bill {
    private Integer id; //id
    private String billCode; //账单编号
    private String productName; //商品名称
    private String productDesc; //商品描述
    private String productUnit; //商品单位
    private Double productCount; //商品数量
    private Double totalPrice; //总金额
    private Integer isPayment; //是否支付
    private Integer createdBy; //创建者
    private Integer modifyBy; //更新者
    private Date creationDate; //创建时间
    private Date modifyDate; //更新时间
    private Integer providerId; //供应商ID

    private String providerName; //供应商名称

    public Bill() {
    }

    public Bill(Integer id, String billCode, String productName, String productDesc, String productUnit, Double productCount, Double totalPrice, Integer isPayment, Integer createdBy, Integer modifyBy, Date creationDate, Date modifyDate, Integer providerId, String providerName) {
        this.id = id;
        this.billCode = billCode;
        this.productName = productName;
        this.productDesc = productDesc;
        this.productUnit = productUnit;
        this.productCount = productCount;
        this.totalPrice = totalPrice;
        this.isPayment = isPayment;
        this.createdBy = createdBy;
        this.modifyBy = modifyBy;
        this.creationDate = creationDate;
        this.modifyDate = modifyDate;
        this.providerId = providerId;
        this.providerName = providerName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getBillCode() {
        return billCode;
    }

    public void setBillCode(String billCode) {
        this.billCode = billCode;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductDesc() {
        return productDesc;
    }

    public void setProductDesc(String productDesc) {
        this.productDesc = productDesc;
    }

    public String getProductUnit() {
        return productUnit;
    }

    public void setProductUnit(String productUnit) {
        this.productUnit = productUnit;
    }

    public Double getProductCount() {
        return productCount;
    }

    public void setProductCount(Double productCount) {
        this.productCount = productCount;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Integer getIsPayment() {
        return isPayment;
    }

    public void setIsPayment(Integer isPayment) {
        this.isPayment = isPayment;
    }

    public Integer getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(Integer createdBy) {
        this.createdBy = createdBy;
    }

    public Integer getModifyBy() {
        return modifyBy;
    }

    public void setModifyBy(Integer modifyBy) {
        this.modifyBy = modifyBy;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public Date getModifyDate() {
        return modifyDate;
    }

    public void setModifyDate(Date modifyDate) {
        this.modifyDate = modifyDate;
    }

    public Integer getProviderId() {
        return providerId;
    }

    public void setProviderId(Integer providerId) {
        this.providerId = providerId;
    }

    public String getProviderName() {
        return providerName;
    }

    public void setProviderName(String providerName) {
        this.providerName = providerName;
    }
}
