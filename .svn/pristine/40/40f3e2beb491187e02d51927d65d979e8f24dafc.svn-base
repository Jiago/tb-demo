package com.pactera.taobaoprogect.dao;

import java.util.List;

import com.pactera.taobaoprogect.entity.ProductInfo;

import android.database.sqlite.SQLiteDatabase;

public interface ProductInfoDAO {
	public void insertProduct(SQLiteDatabase db, List<ProductInfo> proiducts);

	public List<ProductInfo> getProductList(SQLiteDatabase db);

	public ProductInfo findByType(SQLiteDatabase db, String type);

	public List<ProductInfo> searchProductList(SQLiteDatabase db, String type);
}
