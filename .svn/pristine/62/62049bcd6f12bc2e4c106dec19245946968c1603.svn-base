package com.pactera.taobaoprogect.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.R.integer;
import android.os.Parcel;
import android.os.Parcelable;

public class CartProductInfo implements Serializable,
		Comparable<CartProductInfo> {
	private int id;
	private int productId;
	private int payNum;
	private int payed;
	private int comment;
	private ArrayList<CartProductInfo> proGoCartList = new ArrayList<CartProductInfo>();

	public CartProductInfo(int id, int productId, int payNum, int payed,
			int comment) {
		this.id = id;
		this.productId = productId;
		this.payNum = payNum;
		this.payed = payed;
		this.comment = comment;
	}
	public CartProductInfo( int payNum, int productId) {
		this.productId = productId;
		this.payNum = payNum;

	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public int getPayNum() {
		return payNum;
	}

	public void setPayNum(int payNum) {
		this.payNum = payNum;
	}

	public int getPayed() {
		return payed;
	}

	public void setPayed(int payed) {
		this.payed = payed;
	}

	public int getComment() {
		return comment;
	}

	public void setComment(int comment) {
		this.comment = comment;
	}

	public ArrayList<CartProductInfo> getProGoCartList() {
		return proGoCartList;
	}

	public void setProGoCartList(ArrayList<CartProductInfo> proGoCartList) {
		this.proGoCartList = proGoCartList;
	}

	@Override
	public boolean equals(Object o) {
		// TODO Auto-generated method stub
		if (o instanceof CartProductInfo) {
			CartProductInfo a = (CartProductInfo) o;
			if (id == a.id)
				return true;
		}
		return false;
	}

	@Override
	public int compareTo(CartProductInfo another) {
		// TODO Auto-generated method stub
		return 0;
	}

}
