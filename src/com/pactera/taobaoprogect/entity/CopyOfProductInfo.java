package com.pactera.taobaoprogect.entity;

import java.util.ArrayList;
import java.util.List;

import android.R.integer;
import android.os.Parcel;
import android.os.Parcelable;

public class CopyOfProductInfo implements Parcelable,
		Comparable<CopyOfProductInfo> {
	private int id;
	private String imageUrl;
	private String productName;
	private double price;
	private CopyOfProductInfo product;
	private ArrayList<CopyOfProductInfo> proGoCartList = new ArrayList<CopyOfProductInfo>();

	public CopyOfProductInfo(int id, String imageUrl, String productName,
			double price) {
		this.id = id;
		this.imageUrl = imageUrl;
		this.productName = productName;
		this.price = price;
	}

	public static Parcelable.Creator<CopyOfProductInfo> getCreator() {
		return CREATOR;
	}

	public CopyOfProductInfo() {

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

	@Override
	public int describeContents() {

		return 0;
	}

	public CopyOfProductInfo getProduct() {
		return product;
	}

	public void setProduct(CopyOfProductInfo product) {
		this.product = product;
	}

	public ArrayList<CopyOfProductInfo> getProGoCartList() {
		return proGoCartList;
	}

	public void setProGoCartList(ArrayList<CopyOfProductInfo> proGoCartList) {
		this.proGoCartList = proGoCartList;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeInt(this.id);
		dest.writeString(this.imageUrl);
		dest.writeString(this.productName);
		dest.writeDouble(this.price);
		dest.writeParcelable(this.product, flags);
		dest.writeList(this.proGoCartList);
	}

	@SuppressWarnings("unused")
	private static final Parcelable.Creator<CopyOfProductInfo> CREATOR = new Creator<CopyOfProductInfo>() {

		@Override
		public CopyOfProductInfo[] newArray(int size) {

			return new CopyOfProductInfo[size];
		}

		@SuppressWarnings("unchecked")
		@Override
		public CopyOfProductInfo createFromParcel(Parcel source) {
			CopyOfProductInfo product = new CopyOfProductInfo();
			product.id = source.readInt();
			product.imageUrl = source.readString();
			product.productName = source.readString();
			product.price = source.readDouble();
			product.product = source.readParcelable(CopyOfProductInfo.class
					.getClassLoader());
			// ±ØÐëÊµÀý»¯
			product.proGoCartList = source
					.readArrayList(CopyOfProductInfo.class.getClassLoader());
			return product;

		}
	};

	@Override
	public int compareTo(CopyOfProductInfo another) {

		return (int) (price - another.price);
	}

}
