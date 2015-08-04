package com.pactera.taobaoprogect.impl;

import java.util.ArrayList;
import java.util.List;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.pactera.taobaoprogect.dao.ProductInfoDAO;
import com.pactera.taobaoprogect.entity.ProductInfo;
import com.pactera.taobaoprogect.entity.UserInfo;

public class ProdcutHelperUtile implements ProductInfoDAO {

	@Override
	public void insertProduct(SQLiteDatabase db, List<ProductInfo> proiducts) {
		for (ProductInfo product : proiducts) {
			db.execSQL(
					new String(
							"insert into productinfo(id,imageUrl,productName,price, number, type) values(?,?,?,?,?,?)"),
					new Object[] { product.getId(), product.getImageUrl(),
							product.getProductName(), product.getPrice(),
							product.getNumber(), product.getType() });
		}
		db.close();
	}

	@Override
	public List<ProductInfo> getProductList(SQLiteDatabase db) {
		Cursor cursor = db.rawQuery("select * from productinfo", null);
		List<ProductInfo> list = new ArrayList<ProductInfo>();
		while (cursor.moveToNext()) {
			ProductInfo product = new ProductInfo();
			product.setId(cursor.getInt(cursor.getColumnIndex("id")));
			product.setImageUrl(cursor.getString(cursor
					.getColumnIndex("imageUrl")));
			product.setProductName(cursor.getString(cursor
					.getColumnIndex("productName")));
			product.setPrice(cursor.getDouble(cursor.getColumnIndex("price")));
			product.setNumber(cursor.getInt(cursor.getColumnIndex("number")));
			product.setType(cursor.getString(cursor.getColumnIndex("type")));
			list.add(product);
		}
		db.close();
		cursor.close();
		return list;
	}

	@Override
	public List<ProductInfo> searchProductList(SQLiteDatabase db, String type) {
		Cursor cursor = db.rawQuery(
				"select * from productinfo where type like ?",
				new String[] { "%" + type + "%" });
		List<ProductInfo> list = new ArrayList<ProductInfo>();
		while (cursor.moveToNext()) {
			ProductInfo product = new ProductInfo();
			product.setId(cursor.getInt(cursor.getColumnIndex("id")));
			product.setImageUrl(cursor.getString(cursor
					.getColumnIndex("imageUrl")));
			product.setProductName(cursor.getString(cursor
					.getColumnIndex("productName")));
			product.setPrice(cursor.getDouble(cursor.getColumnIndex("price")));
			product.setNumber(cursor.getInt(cursor.getColumnIndex("number")));
			product.setType(cursor.getString(cursor.getColumnIndex("type")));
			list.add(product);
		}
		db.close();
		cursor.close();
		return list;
	}

	@Override
	public ProductInfo findByType(SQLiteDatabase db, String type) {
		// TODO Auto-generated method stub
		return null;
	}

}
