package com.pactera.taobaoprogect.activity;

import com.pactera.taobaoprogect.R;
import com.pactera.taobaoprogect.entity.UserInfo;
import com.pactera.taobaoprogect.impl.UserHelperUtil;
import com.pactera.taobaoprogect.util.BPUtil;
import com.pactera.taobaoprogect.util.MyDatabase;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class RegisterActivity extends Activity implements OnClickListener {
	private EditText mUsernameEditText, mPassWordEditText, mPhoneNumEditText;
	private Button mRegisterButton;
	private ImageView mTestButton;
	private String mUserName, mPassWord, mPhoneNum;
	private UserInfo userInfo;
	MyDatabase database;
	UserHelperUtil userHelper;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_register);
		database = new MyDatabase(this, "mytaobao2.db3", 1);
		userHelper = new UserHelperUtil();
		initView();
	}

	public void initView() {
		mUsernameEditText = (EditText) findViewById(R.id.regUsername_editText);
		mPhoneNumEditText = (EditText) findViewById(R.id.regphone_editText);
		mPassWordEditText = (EditText) findViewById(R.id.regpassword_editText);
		mRegisterButton = (Button) findViewById(R.id.register_button);
		mRegisterButton.setOnClickListener(this);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {

		case R.id.register_button:
			mUserName = mUsernameEditText.getText().toString();
			mPassWord = mPassWordEditText.getText().toString();
			mPhoneNum = mPhoneNumEditText.getText().toString();
			if ("".equals(mUserName) && "".equals(mPassWord)) {
				Toast.makeText(RegisterActivity.this, "请输入内容！",
						Toast.LENGTH_SHORT).show();
			} else {
				UserInfo user = userHelper.findByUserName(
						database.getReadableDatabase(), mUserName);
				if (user == null) {
					Toast.makeText(RegisterActivity.this, "注册成功，为你跳转页面！",
							Toast.LENGTH_LONG).show();
					userInfo = new UserInfo();
					userInfo.setUserName(mUserName);
					userInfo.setPassword(mPassWord);
					userInfo.setPhoneNum(mPhoneNum);
					userInfo.setStatus(0);
					userHelper.register(database.getReadableDatabase(),
							userInfo);
					Intent intent = new Intent(RegisterActivity.this,
							LogActivity.class);
					startActivity(intent);
					finish();
				} else {
					Toast.makeText(RegisterActivity.this, "该用户已存在，请选择别的用户名",
							Toast.LENGTH_LONG).show();
				}

			}
			break;

		default:
			break;
		}

	}

}
