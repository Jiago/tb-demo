package com.pactera.taobaoprogect.activity;

import com.pactera.taobaoprogect.R;
import com.pactera.taobaoprogect.R.id;
import com.pactera.taobaoprogect.R.layout;
import com.pactera.taobaoprogect.entity.UserInfo;
import com.pactera.taobaoprogect.impl.UserHelperUtil;
import com.pactera.taobaoprogect.util.MyDatabase;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;
import android.widget.Toast;

public class LogActivity extends Activity implements OnClickListener {
	public static final int UPDATE_MAIN_PAGE = 0x123;
	private EditText mLogUserNameEdit, mLogPassEdit;
	private Button mLogButton, mLogToRegButton;
	private Intent mIntent;
	private String mLogUserName, mLogPassWord;
	
	SharedPreferences preferences;
	MyDatabase database;
	UserHelperUtil userHelper;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_log);
		preferences = getSharedPreferences("userInfo", MODE_APPEND);
		mIntent = new Intent();
		database = new MyDatabase(this, "mytaobao2.db3", 1);
		userHelper = new UserHelperUtil();

		mLogPassEdit = (EditText) findViewById(R.id.logpassword_editText);

		mLogUserNameEdit = (EditText) findViewById(R.id.logUsername_editText);

		mLogButton = (Button) findViewById(R.id.log_button);
		mLogButton.setOnClickListener(this);
		mLogToRegButton = (Button) findViewById(R.id.log_reg_button);
		mLogToRegButton.setOnClickListener(this);
	

	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.log_button:
			mLogUserName = mLogUserNameEdit.getText().toString();
			mLogPassWord = mLogPassEdit.getText().toString();
			
			UserInfo user = userHelper.findByUserName(
					database.getReadableDatabase(), mLogUserName);
			if(TextUtils.isEmpty(mLogUserName)){
				mLogUserNameEdit.requestFocus();
				Toast.makeText(LogActivity.this, "用户名不能为空", Toast.LENGTH_SHORT)
				.show();
			}
			else if (!mLogUserName.equals("") && mLogPassWord.equals("")) {
				Toast.makeText(LogActivity.this, "请输入用户名密码", Toast.LENGTH_SHORT)
						.show();
			} else if (user == null) {
				Toast.makeText(LogActivity.this, "用户名不存在，请注册！",
						Toast.LENGTH_SHORT).show();
			} else if (mLogUserName.equals(user.getUserName())
					&& mLogPassWord.equals(user.getPassword())) {
				Toast.makeText(LogActivity.this, "登陆成功！为您跳转",
						Toast.LENGTH_SHORT).show();
				preferences.edit().putString("userName", mLogUserName).commit();
				preferences.edit().putInt("userId", user.getId()).commit();
				MainActivity.mMainHandler.sendEmptyMessage(UPDATE_MAIN_PAGE);
				finish();

			} else if (!mLogPassWord.equals(user.getPassword())) {
				Toast.makeText(LogActivity.this, "密码有误，请重新输入！",
						Toast.LENGTH_SHORT).show();
			}
			break;
		case R.id.log_reg_button:
			mIntent.setClass(LogActivity.this, RegisterActivity.class);
			startActivity(mIntent);
			finish();
		default:
			break;
		}

	}

	@Override
	protected void onStop() {
		super.onStop();
		finish();

	}

}
