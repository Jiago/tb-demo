package com.pactera.taobaoprogect.dao;

import java.util.List;

import com.pactera.taobaoprogect.entity.CartProductInfo;
import com.pactera.taobaoprogect.entity.ProductInfo;

import android.database.sqlite.SQLiteDatabase;

public interface CartProductInfoDAO {
	public void insertCart(SQLiteDatabase db, List<CartProductInfo> proiducts);
	public List<ProductInfo> findByCartId(SQLiteDatabase db, String cartId,String payed, String comment);
	public void deleteCartProduct(SQLiteDatabase db);
	public void updateIsChecked(SQLiteDatabase db, String status, String proId);
	public int findStrockNumByCartId(SQLiteDatabase db, String cartId);
	public void updateStrockNum(SQLiteDatabase db, List<CartProductInfo> proiducts);
	public List<ProductInfo> findStayCommentByCartId(SQLiteDatabase db, String cartId);
	public void updateComment(SQLiteDatabase db, String cartId, String proId);
}
