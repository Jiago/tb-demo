package com.pactera.taobaoprogect.entity;

import android.os.Parcel;
import android.os.Parcelable;

public class UserInfo implements Parcelable {
	private int id;
	private String userName;
	private String password;
	private int status;
	private int catrId;
	private String phoneNum;

	public UserInfo() {

	}

	public UserInfo(int id, String userName, String password, int status,
			int catrId, String phoneNum) {
		this.id = id;
		this.userName = userName;
		this.password = password;
		this.status = status;
		this.catrId = catrId;
		this.phoneNum = phoneNum;
	}

	public String getPhoneNum() {
		return phoneNum;
	}

	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}

	public int getCatrId() {
		return catrId;
	}

	public void setCatrId(int catrId) {
		this.catrId = catrId;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public int describeContents() {

		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeInt(id);
		dest.writeString(userName);
		dest.writeString(password);
		dest.writeInt(status);
		dest.writeInt(catrId);
		dest.writeString(phoneNum);

	}

	private static final Parcelable.Creator<UserInfo> CREATOR = new Parcelable.Creator<UserInfo>() {

		@Override
		public UserInfo createFromParcel(Parcel source) {

			return new UserInfo(source.readInt(), source.readString(),
					source.readString(), source.readInt(), source.readInt(),
					source.readString());
		}

		@Override
		public UserInfo[] newArray(int size) {

			return new UserInfo[size];
		}
	};

}
