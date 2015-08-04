package com.pactera.taobaoprogect.adapter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CountDownLatch;

import com.pactera.taobaoprogect.R;
import com.pactera.taobaoprogect.util.AsyncBitmapLoader;
import com.pactera.taobaoprogect.util.AsyncBitmapLoader.ImageCallBack;

import android.app.Activity;
import android.graphics.Bitmap;
import android.text.Layout;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class CheckBoxAdapter extends BaseAdapter {

	private List<HashMap<String, Object>> mlist;
	private Activity mActivity;
	private AsyncBitmapLoader asyncBitmapLoader;
	public HashMap<Integer, Boolean> isSelected;

	public CheckBoxAdapter(Activity activity, List<HashMap<String, Object>> list) {
		this.mActivity = activity;
		this.mlist = list;
		asyncBitmapLoader = new AsyncBitmapLoader();
		isSelected = new HashMap<Integer, Boolean>();
		for (int i = 0; i < mlist.size(); i++) {
			isSelected.put(i, false);
		}
	}

	public CheckBoxAdapter(Activity activity) {
		this.mActivity = activity;
	}

	public CheckBoxAdapter() {
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
		Map<String, Object> map = mlist.get(position);
		if (convertView == null) {
			convertView = mActivity.getLayoutInflater().inflate(R.layout.cart_item,
					null);
		}

		CheckBox checkBox = (CheckBox) convertView.findViewById(R.id.checkbox);
		ImageView imageView = (ImageView) convertView
				.findViewById(R.id.prod_image);
		TextView name = (TextView) convertView.findViewById(R.id.prod_name);
		TextView productprice = (TextView) convertView
				.findViewById(R.id.prod_price);
		TextView payNumber = (TextView) convertView.findViewById(R.id.cart_paynum_textView);
		TextView strockNumber = (TextView) convertView.findViewById(R.id.stock_number_textview);
	    name.setText(map.get("proName").toString());
		productprice.setText("￥" + map.get("price"));
		payNumber.setText(map.get("payNum").toString());
		strockNumber.setText(map.get("strockNum").toString());
		checkBox.setChecked(isSelected.get(position));

		/**
		 * 获取网络图片
		 * 
		 * @param imageView
		 *            显示图片
		 * @param 第二个参数
		 *            图片地址
		 * @param 第三个参数
		 *            回调函数读取位图
		 */
		Bitmap bitmap = asyncBitmapLoader.loadBitmap(imageView,
				map.get("imageUrl").toString(), new ImageCallBack() {

					@Override
					public void imageLoad(ImageView imageView, Bitmap bitmap) {
						imageView.setImageBitmap(bitmap);
					}
				});
		if (bitmap == null) {
			imageView.setImageResource(R.drawable.downloading);
		} else {
			imageView.setImageBitmap(bitmap);
		}
		return convertView;
	}

}