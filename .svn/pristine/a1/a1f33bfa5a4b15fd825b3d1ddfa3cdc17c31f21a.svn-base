package com.pactera.taobaoprogect.util;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class MyDatabase extends SQLiteOpenHelper {
	private String CREATE_USER_TABLE = "create table userInfo(_id integer primary "
			+ "key autoincrement , username , password, phoneNum, status, cartID)";
	private String CREATE_PRODUCT_TABLE = "create table productInfo(_id integer primary "
			+ "key autoincrement , id integer , imageUrl , productName , price double, number integer,type)";

	// ¹ºÎï³µ
	private String CATR_TABLE = "create table cart_product(_id integer "
			+ "primary key autoincrement, cartID integer, productID integer, payNum integer, payed integer, comment integer,isChecked integer)";

	public MyDatabase(Context context, String name, int version) {
		super(context, name, null, version);

	}

	@Override
	public void onCreate(android.database.sqlite.SQLiteDatabase db) {
		db.execSQL(CREATE_USER_TABLE);
		db.execSQL(CREATE_PRODUCT_TABLE);
		db.execSQL(CATR_TABLE);

	}

	@Override
	public void onUpgrade(android.database.sqlite.SQLiteDatabase db,
			int oldVersion, int newVersion) {

	}

}
