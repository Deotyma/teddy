package com.teddy_blue.Entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "purchase_methods")
public class Purchase_method {

    @Id
    @Column(name = "id_purchase_method")
    private String idPurchase_method;

    @Column(name = "purchase_method", updatable = false)
    private String purchase_method;

    public Purchase_method() {

    }

    public String getIdPurchase_method() {
	return idPurchase_method;
    }

    public void setIdPurchase_method(
	    String idPurchase_method) {
	this.idPurchase_method = idPurchase_method;
    }

    public String getPurchase_method() {
	return purchase_method;
    }

    public void setPurchase_method(String purchase_method) {
	this.purchase_method = purchase_method;
    }

    @Override
    public String toString() {
	return "Purchase_method [idPurchase_method="
		+ idPurchase_method + ", purchase_method="
		+ purchase_method + "]";
    }

}
