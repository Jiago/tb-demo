package com.pactera.taobaoprogect.fragment;

import java.util.List;

import com.pactera.taobaoprogect.R;
import com.pactera.taobaoprogect.activity.EvaluateActivity;
import com.pactera.taobaoprogect.activity.StayEvaluateActivity;
import com.pactera.taobaoprogect.activity.MainActivity;
import com.pactera.taobaoprogect.activity.ProductListActivity;
import com.pactera.taobaoprogect.entity.ProductInfo;
import com.pactera.taobaoprogect.impl.CartProdcutHelperUtile;
import com.pactera.taobaoprogect.util.BadgeView;
import com.pactera.taobaoprogect.util.MyDatabase;
import com.pactera.taobaoprogect.util.RoundImageView;

import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.view.animation.BounceInterpolator;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

public class Accountfragment extends Fragment implements OnClickListener {
	private RoundImageView mHeadImageView;
	private TextView mUserNameTextView;
	private SharedPreferences mPreferences;
	private BadgeView  mStayEvaluateBadge;
	public Button mStayEvaluateButton;
	private List<ProductInfo> mStayEvaluateList;
	private CartProdcutHelperUtile mCartHelper;
	private MyDatabase mCartDatabase;
	private SharedPreferences mCartPreferences;
	private int cartId;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);

	}

	@Override
	public void onDestroy() {

		super.onDestroy();
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		mPreferences = getActivity().getSharedPreferences("userInfo",
				getActivity().MODE_APPEND);
		View EntryView = inflater.inflate(R.layout.fragment_account, container,
				false);
		mHeadImageView = (RoundImageView) EntryView
				.findViewById(R.id.touxiang_roundimage);
		mUserNameTextView = (TextView) EntryView
				.findViewById(R.id.username_textveiw);
		mHeadImageView.setImageResource(R.drawable.girl);
		mUserNameTextView.setText(mPreferences.getString("userName", null));
		mStayEvaluateButton = (Button) EntryView.findViewById(R.id.tb_icon_all_evaluate);
		mStayEvaluateButton.setOnClickListener(this);
		
        mCartHelper = new CartProdcutHelperUtile();		
		mCartDatabase = new MyDatabase(getActivity(), "mytaobao2.db3", 1);
		mCartPreferences = getActivity().getSharedPreferences("userInfo",
				getActivity().MODE_APPEND);
		cartId = mCartPreferences.getInt("userId", 0);
		mStayEvaluateList = mCartHelper.findByCartId(
				mCartDatabase.getReadableDatabase(), cartId + "", 1+"", 0+"");
		
		mStayEvaluateBadge = new BadgeView(getActivity(), mStayEvaluateButton);
	    if(mStayEvaluateList.size() > 0){
	    	showBage(mStayEvaluateList.size());
	    }
	    StayEvaluateActivity.mStayEHandler = new Handler(){
			@Override
			public void handleMessage(Message msg) {
				super.handleMessage(msg);
				if(msg.what == 0x767){
					if(mStayEvaluateList.size() > 0){
				    	showBage(mStayEvaluateList.size());
				    }else{
				    	mStayEvaluateBadge.hide();
				    }
				}
			}
	    	
	    }; 
		
		
		return EntryView;
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.tb_icon_all_evaluate:
			Intent intent = new Intent(getActivity(), StayEvaluateActivity.class);
			startActivity(intent);
			break;

		default:
			break;
		}
		
	}
	
	/**
	 *显示提示信息
	 *@param button 信息显示在的控件
	 *@param num 信息数量
	 *
	 * */
	public void showBage(int num){
		//创建一个BadgeView对象，view为你需要显示提醒信息的控件			
		
		mStayEvaluateBadge.setText(num + ""); 	 
		mStayEvaluateBadge.setBadgePosition(BadgeView.POSITION_TOP_RIGHT);
		mStayEvaluateBadge.setTextSize(12); 
		mStayEvaluateBadge.toggle(); 
	}

}
