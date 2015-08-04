package com.pactera.taobaoprogect.activity;

import java.util.ArrayList;
import java.util.List;

import com.pactera.taobaoprogect.R;
import com.pactera.taobaoprogect.entity.CartProductInfo;
import com.pactera.taobaoprogect.entity.UserInfo;
import com.pactera.taobaoprogect.impl.CartProdcutHelperUtile;
import com.pactera.taobaoprogect.impl.UserHelperUtil;
import com.pactera.taobaoprogect.util.AsyncBitmapLoader;
import com.pactera.taobaoprogect.util.MyDatabase;
import com.pactera.taobaoprogect.util.AsyncBitmapLoader.ImageCallBack;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class DetailsActivity extends Activity implements OnClickListener{
	private TextView mProNameTextView, mProPriceTextView;
	private Button mGoCartButton, mBuyNowButton, mAddButton, mSubtractButton;
	private EditText mPayNumEditText;
	private Intent mIntent;
	private ImageView mProductImageView;
	private AsyncBitmapLoader asyncBitmapLoader; 
	private SharedPreferences mPreferences;
	private MyDatabase mDatabase;
	private CartProdcutHelperUtile mHelper;
	private UserHelperUtil mUserHelper;
	private UserInfo mUser;
	private int payNum = 1;
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.product_details);
		initElement();
	}

	
	/**
	 * 初始化组件
	 * */
	public void initElement(){
		mUser = new UserInfo();
		mIntent = getIntent();
		mDatabase = new MyDatabase(this, "mytaobao2.db3", 1);
		mPreferences = getSharedPreferences("userInfo", MODE_APPEND);
		mHelper = new CartProdcutHelperUtile();
		mUserHelper = new UserHelperUtil();
		asyncBitmapLoader = new AsyncBitmapLoader();
		mProductImageView = (ImageView) findViewById(R.id.imageView1);
		mProNameTextView = (TextView) findViewById(R.id.pd_name);
		mProPriceTextView = (TextView) findViewById(R.id.pd_price);
		mPayNumEditText = (EditText) findViewById(R.id.paynum_editview);
		mPayNumEditText.setText(payNum+"");
		
		mGoCartButton = (Button) findViewById(R.id.go_cart);
		mGoCartButton.setOnClickListener(this);
		mBuyNowButton = (Button) findViewById(R.id.buy_now);
		mBuyNowButton.setOnClickListener(this);
		mAddButton = (Button) findViewById(R.id.add_button);
		mAddButton.setOnClickListener(this);
		mSubtractButton = (Button) findViewById(R.id.subtract_button);
		mSubtractButton.setOnClickListener(this);
		
		mProNameTextView.setText(mIntent.getStringExtra("proName"));
		mProPriceTextView.setText("￥" + mIntent.getDoubleExtra("proPrice", 0.0) );
		String imageUrl = mIntent.getStringExtra("proImage");
		Bitmap bitmap = asyncBitmapLoader.loadBitmap(mProductImageView, imageUrl, new ImageCallBack(){

			@Override
			   public void imageLoad(ImageView imageView, Bitmap bitmap) {
				   imageView.setImageBitmap(bitmap);			
			   }});
    	   if(bitmap == null){
    		   mProductImageView.setImageResource(R.drawable.downloading);
    	   }else{
    		   mProductImageView.setImageBitmap(bitmap);
    	   }
	
	}	
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.go_cart:
			insertCartProduct();
			finish();
			break;
			
		case R.id.add_button:
			payNum ++;
			mPayNumEditText.setText(payNum+"");
			mSubtractButton.setVisibility(View.VISIBLE);
		    break;
		case R.id.subtract_button:
			if(payNum == 1){
				mSubtractButton.setVisibility(View.GONE);
			}else{
				payNum --;
				mPayNumEditText.setText(payNum+"");			
			}
		    break;

		default:
			break;
		}
		
	}
	/**
	 * 为购物车添加数据
	 * */
	public void insertCartProduct(){
		List<CartProductInfo> list = new ArrayList<CartProductInfo>();
		int cartId = mPreferences.getInt("userId", 0);
		int proId = mIntent.getIntExtra("proId",0);
		String userName = mPreferences.getString("userName", null);
		CartProductInfo c = new CartProductInfo(cartId, proId, payNum, 0, 0);
		list.add(c);
		mHelper.insertCart(mDatabase.getReadableDatabase(), list);
		mUserHelper.updateCartIdByUsername(mDatabase.getReadableDatabase(), userName, cartId);
	}

}
