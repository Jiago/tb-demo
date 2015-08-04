package com.pactera.taobaoprogect.fragment;

import com.pactera.taobaoprogect.R;
import com.pactera.taobaoprogect.activity.MainActivity;
import com.pactera.taobaoprogect.activity.ProductListActivity;

import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.webkit.WebView.FindListener;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

public class Servicefragment extends Fragment implements OnClickListener {
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
		View EntryView = inflater.inflate(R.layout.fragment_service, container,
				false);

		return EntryView;
	}

	class SearchListener implements OnClickListener {

		@Override
		public void onClick(View v) {
			System.out.println("edti");
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
