package com.pactera.taobaoprogect.activity;


import com.pactera.taobaoprogect.R;
import com.pactera.taobaoprogect.R.anim;
import com.pactera.taobaoprogect.R.id;
import com.pactera.taobaoprogect.R.layout;
import com.pactera.taobaoprogect.fragment.Cartfragment;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class PayingDialog extends Activity{
	public static final int PAY_SUCCEED= 0x234;
	TextView payText;
	Handler handler = new Handler(){

		@Override
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			if(msg.what == 0x123){
				payText.setText("¸¶¿î³É¹¦£¡");
				payText.setTextColor(Color.RED);
				Cartfragment.mCartHandler.sendEmptyMessage(PAY_SUCCEED);
				finish();
				
			}
		}	
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.paying);
		final ImageView flower = (ImageView) findViewById(R.id.pay_imageView);
		final Animation anim = AnimationUtils.loadAnimation(this, R.anim.translate);
		anim.setFillAfter(true);		
		flower.setAnimation(anim);
		handler.postDelayed(new Thread(new MyThread()), 10000);
	}
	class MyThread implements Runnable{

		@Override
		public void run() {
			payText = (TextView) findViewById(R.id.pay_textView);
			Message msg = handler.obtainMessage();
			msg.what = 0x123;
			handler.sendEmptyMessage(msg.what);
			
		}
		
	}

}
