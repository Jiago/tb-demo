package com.pactera.taobaoprogect.impl;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.pactera.taobaoprogect.dao.UserInfoDAO;
import com.pactera.taobaoprogect.entity.UserInfo;

public class UserHelperUtil implements UserInfoDAO{

	@Override
	public void register(SQLiteDatabase db, UserInfo user) {
		db.execSQL("insert into userInfo(username, password, " +
				"phoneNum, status) values(?, ?, ?, ?)",
				new Object[]{user.getUserName(), user.getPassword(), 
				user.getPhoneNum(), user.getStatus()});
		db.close();
	}

	@Override
	public UserInfo findByUserName(SQLiteDatabase db, String userName) {
		Cursor cursor = db.query("userInfo", new String[]{"_id", "username", "password"}, 
				"username=?", new String[]{userName}, null, null, null);
		while(cursor.moveToNext()){
			UserInfo user = new UserInfo();
			user.setId(cursor.getInt(cursor.getColumnIndex("_id")));
			user.setUserName(cursor.getString(cursor.getColumnIndex("username")));
			user.setPassword(cursor.getString(cursor.getColumnIndex("password")));
			cursor.close();
			db.close();
			return user;
		}
		return null;
	}

	@Override
	public UserInfo findUserName(SQLiteDatabase db) {		
		Cursor cursor = db.query("userInfo", new String[]{"username"}, 
				null, null, null, null, null);
		while(cursor.moveToNext()){
			UserInfo user = new UserInfo();
			user.setUserName(cursor.getString(cursor.getColumnIndex("username")));
			cursor.close();
			db.close();
			return user;
		}
		return null;
	}

	@Override
	public void updateCartIdByUsername(SQLiteDatabase db, String userName,
			int cartId) {
		db.execSQL("update userInfo set cartID=? where username=?",
				new Object[]{cartId,userName});
		db.close();
		
	}



}
