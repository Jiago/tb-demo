package com.pactera.taobaoprogect.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.R.integer;
import android.os.Parcel;
import android.os.Parcelable;

public class ProductInfo implements Serializable, Comparable<ProductInfo> {
	private int id;
	private String imageUrl;
	private String productName;
	private double price;
	private int number;
	private String type;
	private ProductInfo product;
	private int payNum;
	private ArrayList<ProductInfo> proGoCartList = new ArrayList<ProductInfo>();

	public ProductInfo(int id, String imageUrl, String productName,
			double price, int number, String type) {
		this.id = id;
		this.imageUrl = imageUrl;
		this.productName = productName;
		this.price = price;
		this.number = number;
		this.type = type;
	}

	@Override
	public String toString() {
		return "ProductInfo [id=" + id + "]";
	}

	public int getPayNum() {
		return payNum;
	}

	public void setPayNum(int payNum) {
		this.payNum = payNum;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public ProductInfo() {

	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public ProductInfo getProduct() {
		return product;
	}

	public void setProduct(ProductInfo product) {
		this.product = product;
	}

	public ArrayList<ProductInfo> getProGoCartList() {
		return proGoCartList;
	}

	public void setProGoCartList(ArrayList<ProductInfo> proGoCartList) {
		this.proGoCartList = proGoCartList;
	}

	@Override
	public boolean equals(Object o) {
		// TODO Auto-generated method stub
		if (o instanceof ProductInfo) {
			ProductInfo a = (ProductInfo) o;
			if (id == a.id)
				return true;
		}
		return false;
	}

	@Override
	public int compareTo(ProductInfo another) {

		return (int) (price - another.price);
	}

}
