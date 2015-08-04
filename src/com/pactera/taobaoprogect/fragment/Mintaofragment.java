package com.pactera.taobaoprogect.fragment;

import com.pactera.taobaoprogect.R;
import com.pactera.taobaoprogect.adapter.MinTaoAdapter;

import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

public class Mintaofragment extends Fragment {
	private ListView mListView;

	@Override
	public void onCreate(Bundle savedInstanceState) {

		Log.v("Contentfragment", "Contentfragment_onCreate");
		super.onCreate(savedInstanceState);
	}

	@Override
	public void onDestroy() {

		super.onDestroy();
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View EntryView = inflater.inflate(R.layout.fragment_mintao, container,
				false);
		mListView = (ListView) EntryView.findViewById(R.id.mintao_listView);
		mListView.setAdapter(new MinTaoAdapter(getActivity()));
		return EntryView;
	}

}
