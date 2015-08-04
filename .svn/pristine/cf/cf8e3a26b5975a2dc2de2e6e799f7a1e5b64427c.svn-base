package com.pactera.taobaoprogect.adapter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CountDownLatch;

import com.pactera.taobaoprogect.R;
import com.pactera.taobaoprogect.activity.EvaluateActivity;
import com.pactera.taobaoprogect.util.AsyncBitmapLoader;
import com.pactera.taobaoprogect.util.AsyncBitmapLoader.ImageCallBack;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.sax.StartElementListener;
import android.text.Layout;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class EvaluateAdapter extends BaseAdapter {

	private List<HashMap<String, Object>> mlist;
	private Activity mActivity;
	private static AsyncBitmapLoader asyncBitmapLoader;
	private Button EvaluateButton;
	private Map<String, Object> map;
	public EvaluateAdapter(Activity activity, List<HashMap<String, Object>> list) {
		this.mActivity = activity;
		this.mlist = list;
		asyncBitmapLoader = new AsyncBitmapLoader();
		
	}

	public EvaluateAdapter(Activity activity) {
		this.mActivity = activity;
	}

	public EvaluateAdapter() {
		asyncBitmapLoader = new AsyncBitmapLoader();
	}

	public int getCount() {
		return mlist.size();
	}

	public Object getItem(int position) {
		return mlist.get(position);
	}

	public long getItemId(int position) {
		return position;
	}

	public View getView(final int position, View convertView, ViewGroup parent) {
		map = mlist.get(position);
		if (convertView == null) {
			convertView = mActivity.getLayoutInflater().inflate(R.layout.evaluate_item,
					null);
		}

		ImageView imageView = (ImageView) convertView
				.findViewById(R.id.prod_image);
		TextView name = (TextView) convertView.findViewById(R.id.prod_name);
		TextView productprice = (TextView) convertView
				.findViewById(R.id.prod_price);
		EvaluateButton = (Button) convertView.findViewById(R.id.evaluate_button);

	    name.setText(map.get("proName").toString());
		productprice.setText("￥" + map.get("price"));
		
		EvaluateButton.setOnClickListener(new EvaluateButtonListener(position));

		getUrlImage(imageView, map.get("imageUrl").toString());
		return convertView;
	}
	
	class EvaluateButtonListener implements OnClickListener{
		int position;
		public EvaluateButtonListener(int pos){
			position = pos;
		}
		@Override
		public void onClick(View v) {
			Intent intent  = new Intent(mActivity, EvaluateActivity.class);
			intent.putExtra("proName", mlist.get(position).get("proName").toString());
			intent.putExtra("price", mlist.get(position).get("price").toString());
			intent.putExtra("imageUrl", mlist.get(position).get("imageUrl").toString());
			intent.putExtra("proId", mlist.get(position).get("id").toString());
			mActivity.startActivity(intent);
		
		}
			
		}
	
	/**
	 * 获取网络图片
	 * 
	 * @param imageView
	 *            显示图片的控件
	 * @param 第二个参数
	 *            图片地址
	 */
	public static void getUrlImage(ImageView imageview, String imageUrl){
		
		Bitmap bitmap = asyncBitmapLoader.loadBitmap(imageview,
				imageUrl, new ImageCallBack() {

					@Override
					public void imageLoad(ImageView imageView, Bitmap bitmap) {
						imageView.setImageBitmap(bitmap);
					}
				});
		if (bitmap == null) {
			imageview.setImageResource(R.drawable.downloading);
		} else {
			imageview.setImageBitmap(bitmap);
		}
	}
		
	}
	
