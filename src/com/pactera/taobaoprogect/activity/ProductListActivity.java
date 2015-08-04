package com.pactera.taobaoprogect.activity;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import com.pactera.taobaoprogect.R;
import com.pactera.taobaoprogect.activity.XListView.IXListViewListener;
import com.pactera.taobaoprogect.entity.ProductInfo;
import com.pactera.taobaoprogect.fragment.Accountfragment;
import com.pactera.taobaoprogect.fragment.Cartfragment;
import com.pactera.taobaoprogect.fragment.Homefragment;
import com.pactera.taobaoprogect.fragment.Mintaofragment;
import com.pactera.taobaoprogect.fragment.Servicefragment;
import com.pactera.taobaoprogect.impl.ProdcutHelperUtile;
import com.pactera.taobaoprogect.util.BadgeView;
import com.pactera.taobaoprogect.util.ListAdapter;
import com.pactera.taobaoprogect.util.MyDatabase;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.RadioGroup.OnCheckedChangeListener;

public class ProductListActivity extends Activity implements IXListViewListener,OnItemClickListener{
	private XListView mListView ;
	private SharedPreferences mPreferences;
	private MyDatabase mDatabase;
	private ProdcutHelperUtile mHelper;
	private ArrayList<HashMap<String, Object>> mList;
	private ArrayList<ProductInfo> proGoCartList;
	//商品集
	private List<ProductInfo> proList;	
	//控制显示条数
	private int maxNum = 5;
	private ListAdapter mAdapter;
	public static Handler mHandler;
	private RadioGroup radioGroup;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.search_list);
		initView();
	}
	/***
	 * 初始化View
	 */
	public void initView(){		
		
		mPreferences = getSharedPreferences("userInfo", MODE_APPEND);
		mListView = (XListView) findViewById(R.id.product_listView);
		mDatabase = new MyDatabase(this, "mytaobao2.db3", 1);
		mHelper = new ProdcutHelperUtile();
		mListView.setPullLoadEnable(true);
		mListView.setXListViewListener(this);
		mListView.setOnItemClickListener(this);
		mHandler = new Handler();
		
		
		//判断是否第一次运行，如果是，向数据库添加数据
		if(mPreferences.getBoolean("isFirstRun", true)){
			insertData();
			mPreferences.edit().putBoolean("isFirstRun", false).commit();
			mPreferences.edit().putBoolean("auto_isChecked", false).commit();
		}

		
		
		//监听radiobutton
		radioGroup = (RadioGroup) findViewById(R.id.search_list_radiogroup);
		radioGroup.setOnCheckedChangeListener(checkedChangeListener);
		
		ImageButton search_button = (ImageButton) findViewById(R.id.search_button);
		search_button.setOnClickListener(new SearchListener());
		
		// 定义字符串数组作为提示的文本
		String[] types = new String[] { "服装", "手机", "家电", "服" };
		// 创建一个ArrayAdapter封装数组
		ArrayAdapter<String> av = new ArrayAdapter<String>(this,
		                                        android.R.layout.simple_dropdown_item_1line, types);
	    AutoCompleteTextView auto = (AutoCompleteTextView) findViewById(R.id.search_autocompletetextview);
		auto.setAdapter(av);


		
	}
	/**
	 *  获取商品列表
	 */
	
	public List<HashMap<String, Object>> getProductList(){

		proList = mHelper.getProductList(mDatabase.getReadableDatabase());
		mList = new ArrayList<HashMap<String, Object>>();
		for(int i = 0; i < maxNum; i++){
			addList(i);
		}
		return mList;
	}
	/**
	 * 用户搜索产品集
	 * @param type 
	 * */
	public List<HashMap<String, Object>> searchProductList(String type){
		proList =  mHelper.searchProductList(mDatabase.getReadableDatabase(), type);
		mList = new ArrayList<HashMap<String, Object>>();
		if(proList.size() < maxNum){
			for(int i = 0; i < proList.size(); i++){			
				addList(i);
			}
			return mList;
		}else{
		    for(int i = 0; i < maxNum; i++){			
			   addList(i);
		   }
		    return mList;
		}
	}
	
	public void addList(int i){
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("id", proList.get(i).getId());
		map.put("imageUrl", proList.get(i).getImageUrl());
		map.put("proName", proList.get(i).getProductName());
		map.put("price", proList.get(i).getPrice());
		mList.add(map);
	}
	
	private OnCheckedChangeListener checkedChangeListener = new OnCheckedChangeListener() {

		@Override
		public void onCheckedChanged(RadioGroup group, int checkedId) {
			switch (checkedId) {

			case R.id.radio_nomal:
				 
				break;
				
			case R.id.radio_credit:
				  
				
			        break;
			        
			case R.id.radio_price:
				Collections.sort(proList);
				mList = new ArrayList<HashMap<String, Object>>();
				for(int i = 0; i < proList.size(); i++){
					addList(i);
				}
				mAdapter = new ListAdapter(ProductListActivity.this, mList);
				mListView.setAdapter(mAdapter);
				break;
				
			case R.id.radio_sales:
				
				break;
			
			default:
				break;
			}
		}
	};
	
	class SearchListener implements OnClickListener{

		@Override
		public void onClick(View v) {
			AutoCompleteTextView editText = (AutoCompleteTextView) findViewById(R.id.search_autocompletetextview);
			mList = (ArrayList<HashMap<String, Object>>) searchProductList(editText.getText().toString());
			if(mList.size() == 0){
				Toast.makeText(ProductListActivity.this, "没有符合要求的商品", Toast.LENGTH_SHORT).show();
			}else{
				mAdapter = new ListAdapter(ProductListActivity.this, mList);
				mListView.setAdapter(mAdapter);
			}		
//			mListView.setPullLoadEnable(false);
		}
		
	}
	
	
	/**
	 *  初始化数据到数据库
	 */

		public void insertData(){
			List<ProductInfo> list = new ArrayList<ProductInfo>();
			ProductInfo p1 = new ProductInfo(00001, 
					"http://img.teapic.com/thumbs/201312/18/100123hmigmrbrqchqfghb.jpg.middle.jpg", 
					"苹果（APPLE）iPhone 5s 16G版 3G手机（金色）WCDMA/GSM", 4988.0, 10, "手机shouji");
			ProductInfo p2 = new ProductInfo(00002, 
					"http://img11.360buyimg.com/n1/g13/M06/15/1E/rBEhVFJ8mGAIAAAAAAHDlJmFATQAAFLqgM2tgsAAcOs407.jpg", 
					"HTC New One 802w 3G手机（冰川银）WCDMA/GSM 双卡双待双通", 2999.0, 10, "手机shouji");
			ProductInfo p3 = new ProductInfo(00003, 
					"http://img13.360buyimg.com/n1/g13/M00/08/1F/rBEhU1Kb7zAIAAAAAAHDGdy6f98AAGOMQGA34sAAcMx599.jpg", 
					"诺基亚（NOKIA） Lumia1320 3G手机 （黄色）WCDMA/GSM", 1999.0, 2, "手机shouji");
			ProductInfo p4 = new ProductInfo(00004, 
					"http://img10.360buyimg.com/n1/g13/M01/10/1A/rBEhU1LOGksIAAAAAAG2UOnKBvAAAHv7gBWWXoAAbZo699.jpg", 
					"华为 麦芒B199 电信3G手机（白色）CDMA2000/GSM 双模双待双通", 1999.0, 50, "手机shouji");
			ProductInfo p5 = new ProductInfo(00005, 
					"http://img11.360buyimg.com/n1/g14/M07/0C/0A/rBEhVVLPqr0IAAAAAAFBChIkAVwAAH5rwFuHMcAAUEi895.jpg", 
					"努比亚 (nubia）小牛2 Z5S mini 3G手机(前黑后白） WCDMA/TD-SCDMA/EVDO", 1499.0, 10, "手机shouji");
			ProductInfo p6 = new ProductInfo(00006, 
					"http://img10.360buyimg.com/n1/g13/M03/0E/15/rBEhVFK9GyMIAAAAAAGQIgLmt3kAAHU9wH406sAAZA6848.jpg", 
					"酷派 8720Q 3G手机（银）TD-SCDMA/GSM", 699.0, 10, "手机shouji");
			ProductInfo p7 = new ProductInfo(00007, 
					"http://img10.360buyimg.com/n1/g14/M04/13/05/rBEhVVJJSiYIAAAAAAGP9a30gNIAADvxwLalWgAAZAN345.jpg", 
					"三星 Galaxy Note 3 N9008 3G手机（简约白） TD-SCDMA/GSM（32G版）", 4188.0, 20, "手机shouji");
			ProductInfo p8 = new ProductInfo(10008, 
					"http://img14.360buyimg.com/n1/g14/M09/18/0F/rBEhVVJvkLQIAAAAAAK9rd1MRGYAAEyPQNpQO8AAr3F521.jpg", 
					"索尼（SONY） KLV-40R476A 40英寸 全高清 LED液晶电视)黑色)", 3099.0, 10, "家电jiadian");
			ProductInfo p9 = new ProductInfo(10009, 
					"http://img14.360buyimg.com/n1/g15/M03/12/15/rBEhWVJw2C8IAAAAAAECoQIMtkEAAEx_ALoZU0AAQK5870.jpg", 
					"TCL KFRd-52LW/FC23 大2匹 立柜式电辅型定频家用冷暖空调（白色）", 5399.0, 10, "家电jiadian");
			ProductInfo p10 = new ProductInfo(10010, 
					"http://img12.360buyimg.com/n1/g13/M02/12/1C/rBEhVFLgcRQIAAAAAAFlVvLA2WEAAIJ9AAQt-cAAWVu657.jpg", 
					"NIAN JEEP吉普盾2014后春季男装纯棉水洗休闲裤 3709 军绿 38(2.9尺)", 138.0, 100, "服装fuzhuang");
			ProductInfo p11 = new ProductInfo(10011, 
					"http://img10.360buyimg.com/n1/g13/M02/16/00/rBEhUlJ8uXYIAAAAAAN35xfbX28AAFMVQPHZqEAA3f_075.jpg", 
					"热卖年货 苏醒的乐园 2013冬新款羽绒服女款短款 加厚修身羽绒服 女 YRF236 红色 m", 399.0, 100, "服装fuzhuang");
			ProductInfo p12 = new ProductInfo(10012, 
					"http://img14.360buyimg.com/n1/g15/M0A/18/1A/rBEhWVKXBPwIAAAAAAC0a3v-9cAAAGDlwD1OHgAALSD667.jpg", 
					"京品年货 2014年春装新款韩版打底杉 女 套头针织衫桃心图案拼接宽松毛衣", 79.0, 100, "服装fuzhuang");
			ProductInfo p13 = new ProductInfo(10013, 
					"http://img11.360buyimg.com/n1/g14/M07/15/06/rBEhVlJbXIwIAAAAAAX5HI40BdUAAEJDgL2DeEABfk0775.jpg", 
					"帕博雅 2013冬季新款女装外套 双排扣御寒大衣 中长款工装羽绒服 黑色 M", 499.0, 100, "服装fuzhuang");
			list.add(p1);
			list.add(p2);
			list.add(p3);
			list.add(p10);
			list.add(p11);
			list.add(p4);
			list.add(p5);
			list.add(p6);
			list.add(p7);
			list.add(p8);
			list.add(p9);			
			list.add(p12);
			list.add(p13);


			mHelper.insertProduct(mDatabase.getReadableDatabase(), list);
		}
		/**
		 * 监听listview每个条目
		 * */
	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
		Intent intent = new Intent(ProductListActivity.this, DetailsActivity.class);
		intent.putExtra("proName", proList.get(position - 1).getProductName());
		intent.putExtra("proPrice", proList.get(position - 1).getPrice());
		intent.putExtra("proImage", proList.get(position-1).getImageUrl());
		intent.putExtra("proId", proList.get(position-1).getId());
		startActivity(intent);
		
	}
	@Override
	public void onRefresh() {
		mHandler.postDelayed(new Runnable() {
			@Override
			public void run() {
				loadProductList();
			}
		}, 1000);			
	}

	@Override
	public void onLoadMore() {
		mHandler.postDelayed(new Runnable() {
			@Override
			public void run() {
				initList();
				mAdapter.notifyDataSetChanged();
				loadProductList();
			}
		}, 1000);			
	}
	
	public void loadProductList(){
		if(mList.size() == proList.size()){
			Toast.makeText(ProductListActivity.this, "已无更多产品", 500).show();
			mListView.setPullLoadEnable(false);
		}
		mListView.stopRefresh();
		mListView.stopLoadMore();
		mListView.setRefreshTime(new SimpleDateFormat("MM-dd HH:mm:ss")
				.format(new Date()));
	}
	
	public List<HashMap<String, Object>> initList() {
		int count = mAdapter.getCount();
		if (count + maxNum < proList.size()) {
			for (int i = count; i <= count + maxNum; i++) {
				addList(i);
			}
		} else {
			for (int i = count; i < proList.size(); i++) {
				addList(i);
			}
		}
		return mList;

	}
	

}
