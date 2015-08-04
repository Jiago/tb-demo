package com.pactera.taobaoprogect.fragment;

import com.pactera.taobaoprogect.R;
import com.pactera.taobaoprogect.activity.MainActivity;
import com.pactera.taobaoprogect.activity.ProductListActivity;
import com.pactera.taobaoprogect.adapter.GridViewAdapter;
import com.pactera.taobaoprogect.widget.MyGridView;

import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

public class Homefragment extends Fragment implements OnClickListener {
	private EditText mSearchEditText;
	private MyGridView mHotgridview;
	private MyGridView mCatgridview;
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
		View EntryView = inflater.inflate(R.layout.fragment_home, container, false);
		mSearchEditText = (EditText) EntryView.findViewById(R.id.home_search_edittext);
		mSearchEditText.setOnClickListener(new SearchListener());		
		// »î¶¯×é

		mHotgridview = (MyGridView) EntryView.findViewById(R.id.hotgridview);
			int[] images = new int[] { R.drawable.t5, R.drawable.t6, R.drawable.t7,
					R.drawable.t8, R.drawable.t9, R.drawable.t10 };
			mHotgridview.setAdapter(new GridViewAdapter(getActivity(), images));

			mCatgridview = (MyGridView) EntryView.findViewById(R.id.catgridview);
			int[] images2 = new int[] { R.drawable.t11, R.drawable.t12,
					R.drawable.t15, R.drawable.t14 };
			mCatgridview.setAdapter(new GridViewAdapter(getActivity(), images2));

		return EntryView;
	}
    class SearchListener implements OnClickListener{

		@Override
		public void onClick(View v) {
			Intent intent = new Intent(getActivity(), ProductListActivity.class);
			startActivity(intent);
			
		}
    	
    } 
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.home_search_edittext:		
			break;

		default:
			break;
		}
		
	}

	

}
