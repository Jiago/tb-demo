package com.pactera.taobaoprogect.activity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.zip.Inflater;

import com.pactera.taobaoprogect.R;
import com.pactera.taobaoprogect.adapter.CheckBoxAdapter;
import com.pactera.taobaoprogect.adapter.EvaluateAdapter;
import com.pactera.taobaoprogect.entity.ProductInfo;
import com.pactera.taobaoprogect.impl.CartProdcutHelperUtile;
import com.pactera.taobaoprogect.util.BadgeView;
import com.pactera.taobaoprogect.util.ListAdapter;
import com.pactera.taobaoprogect.util.MyDatabase;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class StayEvaluateActivity extends Activity implements OnClickListener,OnItemClickListener{
	public static Handler mHandler;
	private List<ProductInfo> mEvaluateList;
	private List<HashMap<String, Object>> mList;	
	private ListView mEvaluateListView;
	private EvaluateAdapter mEvaluateAdapter;
    private Button mBackButton;
    public static SharedPreferences mPreferences;
    public static CartProdcutHelperUtile mCartHelper;
    public static MyDatabase mEvaluateDatabase;
    public static Handler mStayEHandler;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.stay_evaluate);
		initView();
		mStayEHandler = new Handler(){

			@Override
			public void handleMessage(Message msg) {
				super.handleMessage(msg);
				if(msg.what == 0x767){
					getEvaluateList();
				}
			}
	    	
	    };

	}

	public void initView() {
		mPreferences = getSharedPreferences("userInfo", MODE_APPEND);
		mEvaluateDatabase = new MyDatabase(this, "mytaobao2.db3", 1);
		mCartHelper = new CartProdcutHelperUtile();
		
		mEvaluateListView = (ListView) findViewById(R.id.stay_eveluate_listview);
		mEvaluateListView.setOnItemClickListener(this);
		
		mBackButton = (Button) findViewById(R.id.stay_evaluate_backbutton);
		mBackButton.setOnClickListener(this);
		
		
		getEvaluateList();
		
			
	}
	/**
	 * 获取待评价商品
	 * */
	public void getEvaluateList() {
		mEvaluateList = mCartHelper.findByCartId(
				mEvaluateDatabase.getReadableDatabase(), 
				mPreferences.getInt("userId", 0) + "", 1+"", 0+"");
		mList = new ArrayList<HashMap<String, Object>>();

		for (int i = 0; i < mEvaluateList.size(); i++) {
			addList(i);
		}
		if (mList.size() == 0) {
			mEvaluateListView.setVisibility(View.GONE);
			
		} else {
			mEvaluateAdapter = new EvaluateAdapter(this, mList);
			mEvaluateListView.setAdapter(mEvaluateAdapter);
		}

	}
	public void addList(int i) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("id", mEvaluateList.get(i).getId());
		map.put("imageUrl", mEvaluateList.get(i).getImageUrl());
		map.put("proName", mEvaluateList.get(i).getProductName());
		map.put("price", mEvaluateList.get(i).getPrice());
		map.put("payNum", mEvaluateList.get(i).getPayNum());
		map.put("strockNum", mEvaluateList.get(i).getNumber());
		mList.add(map);

	}


	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.stay_evaluate_backbutton:
			finish();
			break;

		default:
			break;
		}

	}
	

	/**
	 * @SuppressWarnings("deprecation")
	 * @Override
	 */
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		return super.onKeyDown(keyCode, event);
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		
		System.out.println(parent.getItemAtPosition(position));
	}

}
