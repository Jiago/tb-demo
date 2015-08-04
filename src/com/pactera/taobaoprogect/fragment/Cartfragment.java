package com.pactera.taobaoprogect.fragment;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import com.pactera.taobaoprogect.R;
import com.pactera.taobaoprogect.activity.PayingDialog;
import com.pactera.taobaoprogect.activity.XListView;
import com.pactera.taobaoprogect.activity.XListView.IXListViewListener;
import com.pactera.taobaoprogect.adapter.CheckBoxAdapter;
import com.pactera.taobaoprogect.entity.CartProductInfo;
import com.pactera.taobaoprogect.entity.ProductInfo;
import com.pactera.taobaoprogect.impl.CartProdcutHelperUtile;
import com.pactera.taobaoprogect.util.MyDatabase;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class Cartfragment extends Fragment implements
		OnItemClickListener, OnClickListener {
	// 商品集
	private List<ProductInfo> mCartProductList;
	private CartProdcutHelperUtile mCartHelper;
	private MyDatabase mCartDatabase;
	private SharedPreferences mCartPreferences;
	private ArrayList<HashMap<String, Object>> mList;
	private CheckBoxAdapter mCartAdapter;
	private ListView mCartListView;
	private Homefragment mHomeFragment;
	private FragmentTransaction transaction;
	private LinearLayout mCartButton;
	private Button mCartDeleteButton, mPayButton;
	private TextView mTotalPricesTextView;
	public static Handler mCartHandler;
	private int cartId;
	// 控制显示条数
	private int maxNum = 5;
	private View EntryView;
	private ArrayList<CartProductInfo> positions;

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
		EntryView = inflater.inflate(R.layout.fragment_shoppcart, container,
				false);
		mCartButton = (LinearLayout) getActivity().findViewById(
				R.id.cartbutton_layout);
		mCartDeleteButton = (Button) getActivity().findViewById(
				R.id.cart_delect_button);
		mPayButton = (Button) getActivity().findViewById(R.id.cart_pay_button);
		
		mPayButton.setOnClickListener(this);
		mCartDeleteButton.setOnClickListener(this);
		
		positions = new ArrayList<CartProductInfo>();

		mTotalPricesTextView = (TextView) getActivity().findViewById(
				R.id.totalprices_textview);

		mCartListView = (ListView) EntryView
				.findViewById(R.id.shoppcatr_listview);
		mCartHelper = new CartProdcutHelperUtile();
		
		mCartDatabase = new MyDatabase(getActivity(), "mytaobao2.db3", 1);
		mCartPreferences = getActivity().getSharedPreferences("userInfo",
				getActivity().MODE_APPEND);
		cartId = mCartPreferences.getInt("userId", 0);
		getProductList();
		updatePage();
		return EntryView;

	}
	
	/**
	 * 当用户购买商品成功后刷新页面
	 * */
	public void updatePage(){
		mCartHandler = new Handler(){

			@Override
			public void handleMessage(Message msg) {
				super.handleMessage(msg);
				if(msg.what == PayingDialog.PAY_SUCCEED){
						mCartHelper.updateStrockNum(mCartDatabase.getReadableDatabase(), positions);
						getProductList();	
						mCartButton.setVisibility(View.GONE);
				}
			}
			
		};
		
	}

	/**
	 * 获取购物车商品
	 * */
	public void getProductList() {
		countPrices = 0;
		mCartProductList = mCartHelper.findByCartId(
				mCartDatabase.getReadableDatabase(), cartId + "", 0+"" ,0+"");
		mList = new ArrayList<HashMap<String, Object>>();

		for (int i = 0; i < mCartProductList.size(); i++) {
			addList(i);
		}
		if (mList.size() == 0) {
			EntryView.findViewById(R.id.shoppcart_noproduct).setVisibility(
					View.VISIBLE);
			mCartListView.setVisibility(View.GONE);
			
		} else {
			mCartAdapter = new CheckBoxAdapter(getActivity(), mList);
			mCartListView.setAdapter(mCartAdapter);
			mCartListView.setOnItemClickListener(this);
		}

	}
	
	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		
		/**
		 * 点击勾选checkbox解决listview滑动时勾选框改变
		 * 
		 * @param view
		 *            当前view
		 * @param position
		 *            当前点击的位置
		 * */
		checkBoxCheck(view, position);
		CartProductInfo c = new CartProductInfo(mCartProductList.get(position ).getPayNum(), 
				mCartProductList.get(position ).getId());
		positions.add(c);

		// 改变应用布局，但购物车有勾选商品改变按钮布局
		if (mCartAdapter.isSelected.containsValue(true)) {
			mCartButton.setVisibility(View.VISIBLE);
		} else {
			mCartButton.setVisibility(View.GONE);
		}
	}

	/**
	 * 点击勾选checkbox解决listview滑动时勾选框改变
	 * 
	 * @param view
	 *            当前view
	 * @param position
	 *            当前点击的位置
	 * */
	double countPrices;

	public void checkBoxCheck(View view, int position) {
		CheckBox checkBox = (CheckBox) view.findViewById(R.id.checkbox);
		checkBox.toggle();
		mCartAdapter.isSelected.put(position, checkBox.isChecked());

		// 更新购物车商品是否选择状态,选择了计算总价
		if (mCartAdapter.isSelected.get(position)) {
			mCartHelper.updateIsChecked(mCartDatabase.getReadableDatabase(),
					1 + "", mCartProductList.get(position).getId() + "");
			countPrices = countPrices
					+ mCartProductList.get(position).getPrice()
					*mCartProductList.get(position ).getPayNum();
			mTotalPricesTextView.setText("￥" + countPrices);
		} else {
			mCartHelper.updateIsChecked(mCartDatabase.getReadableDatabase(),
					0 + "", mCartProductList.get(position ).getId() + "");
			countPrices = countPrices
					- mCartProductList.get(position ).getPrice()
					*mCartProductList.get(position ).getPayNum();
			mTotalPricesTextView.setText("￥" + countPrices);
		}
	}

	public void addList(int i) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("id", mCartProductList.get(i).getId());
		map.put("imageUrl", mCartProductList.get(i).getImageUrl());
		map.put("proName", mCartProductList.get(i).getProductName());
		map.put("price", mCartProductList.get(i).getPrice());
		map.put("payNum", mCartProductList.get(i).getPayNum());
		map.put("strockNum", mCartProductList.get(i).getNumber());
		mList.add(map);

	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.cart_delect_button:
			deleteCartProduct();
			break;

		case R.id.cart_pay_button:
			Intent intent = new Intent(getActivity(), PayingDialog.class);
			startActivity(intent);
		default:
			break;
		}
	}

	/**
	 * 删除勾选商品
	 * */
	public void deleteCartProduct(){
		mCartHelper.deleteCartProduct(mCartDatabase.getReadableDatabase());
		getProductList();
		mCartButton.setVisibility(View.GONE);
	}

}
