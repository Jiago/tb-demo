package com.pactera.taobaoprogect.activity;

import com.pactera.taobaoprogect.R;
import com.pactera.taobaoprogect.fragment.Accountfragment;
import com.pactera.taobaoprogect.fragment.Cartfragment;
import com.pactera.taobaoprogect.fragment.Homefragment;
import com.pactera.taobaoprogect.fragment.Mintaofragment;
import com.pactera.taobaoprogect.fragment.Servicefragment;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;
import android.widget.RadioGroup.OnCheckedChangeListener;

public class MainActivity extends Activity {

	private FragmentTransaction transaction;
	private Homefragment mHomeFragment;
	private SharedPreferences mPreferences;
	private RadioGroup mMainRadioGroup;
	private Mintaofragment mMinTaoFragment;
	private Accountfragment mAccountFragment;
	private Cartfragment mShoppingCart;
	private Servicefragment mServicefragment;
	private long exitTime = 0;
	public static Handler mMainHandler;
	public View mMainView;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);		
		setContentView(R.layout.activity_main1);
		mMainView = new View(this);
		updatePage();
		// 开启线程监听网络
		// Intent intent = new Intent(this, MyService.class);
		// startService(intent);
		transaction = getFragmentManager().beginTransaction();
		mHomeFragment = new Homefragment();
		transaction.add(android.R.id.tabcontent, mHomeFragment);
		// Commit the transaction
		transaction.commit();
		mPreferences = getSharedPreferences("userInfo", MODE_APPEND);
		mMainRadioGroup = (RadioGroup) findViewById(R.id.radiogroup);
		mMainRadioGroup.setOnCheckedChangeListener(checkedChangeListener);
	}
	
	/***
	 * 用户登录成功后更新页面
	 * */
	public void updatePage(){
		mMainHandler = new Handler(){

			@Override
			public void handleMessage(Message msg) {
				super.handleMessage(msg);
				if(msg.what == LogActivity.UPDATE_MAIN_PAGE){
					if (null == mAccountFragment) {
						mAccountFragment = new Accountfragment();
					}
					transaction = getFragmentManager().beginTransaction();
					transaction.replace(android.R.id.tabcontent, mAccountFragment);
					// Commit the transaction
					transaction.commitAllowingStateLoss();
					
				}
			}
			
		};
	}

	private OnCheckedChangeListener checkedChangeListener = new OnCheckedChangeListener() {

		@Override
		public void onCheckedChanged(RadioGroup group, int checkedId) {
			switch (checkedId) {

			case R.id.radio_home:
				if (null == mHomeFragment) {
					mHomeFragment = new Homefragment();
				}
				replaceFragment(mHomeFragment);
				break;

			case R.id.radio_mintao:

				if (null == mMinTaoFragment) {
					mMinTaoFragment = new Mintaofragment();
				}
				replaceFragment(mMinTaoFragment);
				break;

			case R.id.radio_service:
				if (null == mServicefragment) {
					mServicefragment = new Servicefragment();
				}
				replaceFragment(mServicefragment);
				break;

			case R.id.radio_cart:
				if (null == mShoppingCart) {
					mShoppingCart = new Cartfragment();
				}
				replaceFragment(mShoppingCart);
				break;

			case R.id.radio_account:
				if (mPreferences.getString("userName", null) == null) {
					Intent intent = new Intent(MainActivity.this,
							LogActivity.class);
					startActivity(intent);
				} else {
					if (null == mAccountFragment) {
						mAccountFragment = new Accountfragment();
					}
					replaceFragment(mAccountFragment);
				}
				break;
			default:
				break;
			}
		}
	};

	public void replaceFragment(Fragment fragment) {
		transaction = getFragmentManager().beginTransaction();
		transaction.replace(android.R.id.tabcontent, fragment);
		// Commit the transaction
		transaction.commit();
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK
				&& event.getAction() == KeyEvent.ACTION_DOWN) {
			if ((System.currentTimeMillis() - exitTime) > 2000) {

				Toast.makeText(getApplicationContext(), "再按一次退出程序",
						Toast.LENGTH_SHORT).show();
				exitTime = System.currentTimeMillis();
			} else {
				mPreferences.edit().putString("userName", null).commit();
				mPreferences.edit().putInt("userId", 0).commit();
				finish();
				System.exit(0);
			}
			return true;
		}
		return super.onKeyDown(keyCode, event);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
