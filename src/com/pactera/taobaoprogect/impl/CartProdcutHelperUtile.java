package com.pactera.taobaoprogect.impl;

import java.util.ArrayList;
import java.util.List;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.pactera.taobaoprogect.dao.CartProductInfoDAO;
import com.pactera.taobaoprogect.dao.ProductInfoDAO;
import com.pactera.taobaoprogect.entity.CartProductInfo;
import com.pactera.taobaoprogect.entity.ProductInfo;
import com.pactera.taobaoprogect.entity.UserInfo;

public class CartProdcutHelperUtile implements CartProductInfoDAO {
	@Override
	public void insertCart(SQLiteDatabase db, List<CartProductInfo> proiducts) {
		for (CartProductInfo product : proiducts) {
			db.execSQL(
					new String(
							"insert into cart_product(cartID, productID, payNum, payed, comment) values(?,?,?,?,?)"),
					new Object[] { product.getId(), product.getProductId(),
							product.getPayNum(), product.getPayed(),
							product.getComment() });
		}
		db.close();

	}

	@Override
	public List<ProductInfo> findByCartId(SQLiteDatabase db, String cartId, String payed, String comment) {
		Cursor cursor = db
				.rawQuery(
						"SELECT * FROM productInfo INNER JOIN  "
								+ "cart_product on productInfo.id = cart_product.productID where "
								+ "cart_product.cartID=? and cart_product.payed = ? and  cart_product.comment=?",
						new String[] { cartId, payed, comment });
		List<ProductInfo> cartProductList = new ArrayList<ProductInfo>();
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
			product.setPayNum(cursor.getInt(cursor.getColumnIndex("payNum")));
			cartProductList.add(product);
		}
		db.close();
		cursor.close();
		return cartProductList;
	}

	@Override
	public void updateIsChecked(SQLiteDatabase db, String status, String proId) {
		db.execSQL("update cart_product set isChecked=? where productID=?",
				new String[] { status, proId });
		db.close();

	}

	@Override
	public void deleteCartProduct(SQLiteDatabase db) {
		db.execSQL("delete from cart_product where isChecked=1");
		db.close();

	}

	@Override
	public int findStrockNumByCartId(SQLiteDatabase db, String cartId) {
		Cursor cursor = db
				.rawQuery(
						"SELECT * FROM productInfo INNER JOIN  "
								+ "cart_product on productInfo.id = cart_product.productID where "
								+ "cart_product.cartID=?",
						new String[] { cartId });
		int strockNum = 0;
		while (cursor.moveToNext()) {
			strockNum = cursor.getInt(cursor.getColumnIndex("number"));
		}
		db.close();
		cursor.close();
		return strockNum;
	}

	@Override
	public void updateStrockNum(SQLiteDatabase db, List<CartProductInfo> proiducts) {
		for (CartProductInfo product : proiducts) {
			db.beginTransaction();
			try {
				db.execSQL(
						new String(
								"update productInfo set number=number-? where id=?"),
						new Object[] { product.getPayNum(), product.getProductId() });
				db.execSQL("update cart_product set payed=1 where productID=?", 
						new Object[] { product.getProductId() });
				db.setTransactionSuccessful();
			} catch (Exception e) {
				e.printStackTrace();
			}finally{
				db.endTransaction();
				}
			
		}
		db.close();
		
	}

	@Override
	public List<ProductInfo> findStayCommentByCartId(SQLiteDatabase db,
			String cartId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void updateComment(SQLiteDatabase db, String cartId, String proId) {
		db.execSQL("update cart_product set comment=1 where cartID=? and productID=?",
				new String[] { cartId, proId });
		db.close();
		
	}

}
