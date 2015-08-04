package com.pactera.taobaoprogect.activity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;
import java.util.zip.Inflater;

import org.xmlpull.v1.XmlPullParser;

import com.pactera.taobaoprogect.R;
import com.pactera.taobaoprogect.adapter.CheckBoxAdapter;
import com.pactera.taobaoprogect.adapter.EvaluateAdapter;
import com.pactera.taobaoprogect.entity.ProductInfo;
import com.pactera.taobaoprogect.impl.CartProdcutHelperUtile;
import com.pactera.taobaoprogect.util.BadgeView;
import com.pactera.taobaoprogect.util.ListAdapter;
import com.pactera.taobaoprogect.util.MyDatabase;

import android.R.anim;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.Shader;
import android.graphics.Shader.TileMode;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ClipDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.RoundRectShape;
import android.util.AttributeSet;
import android.util.Xml;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.RatingBar.OnRatingBarChangeListener;
import android.widget.TextView;
import android.widget.Toast;

public class EvaluateActivity extends Activity implements
		OnRatingBarChangeListener, OnClickListener {
	public static Handler mHandler;
	private List<ProductInfo> mEvaluateList;
	private List<HashMap<String, Object>> mList;
	private SharedPreferences mPreferences;
	private ListView mEvaluateListView;
	private EvaluateAdapter mEvaluateAdapter;
	private CartProdcutHelperUtile mCartHelper;
	private MyDatabase mEvaluateDatabase;
	private Button mBackButton, mSubmitEvaluateButton;
	private RatingBar mAccountRatingBar, mExpressageRatingbar,
			mSellerServiceRatingbar;
	private TextView mProNameTextView, mPriceTextView;
	private ImageView mProImageView;
	private Intent intent;
	

	private Bitmap mBackGroupBitmap, mGoodBitmap, mBadBitmap;
	private Bitmap[] mGoodBitmaps, mBadBitmaps;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.evaluate);
		mAccountRatingBar = (RatingBar) findViewById(R.id.account_ratingbar2);
		mAccountRatingBar.setOnRatingBarChangeListener(this);
		mExpressageRatingbar = (RatingBar) findViewById(R.id.expressage_ratingbar);
		mExpressageRatingbar.setOnRatingBarChangeListener(this);
		mSellerServiceRatingbar = (RatingBar) findViewById(R.id.Seller_service_ratingbar);
		mSellerServiceRatingbar.setOnRatingBarChangeListener(this);
		mBackButton = (Button) findViewById(R.id.stay_evaluate_backbutton);
		mBackButton.setOnClickListener(this);
		mSubmitEvaluateButton = (Button) findViewById(R.id.submit_evaluate_button);
		mSubmitEvaluateButton.setOnClickListener(this);
		
		mProNameTextView = (TextView) findViewById(R.id.evaluate_name_textview);
		mPriceTextView = (TextView) findViewById(R.id.evaluate_price_textview);
		mProImageView = (ImageView) findViewById(R.id.evaluate_image_imageview);
		intent = getIntent();
		mPriceTextView.setText(intent.getStringExtra("price"));		
		mProNameTextView.setText(intent.getStringExtra("proName"));
		EvaluateAdapter.getUrlImage(mProImageView, intent.getStringExtra("imageUrl"));

		mGoodBitmap = BitmapFactory.decodeResource(getResources(),
				R.drawable.star_good);
		mBadBitmap = BitmapFactory.decodeResource(getResources(),
				R.drawable.star_bad);
		mBackGroupBitmap = BitmapFactory.decodeResource(getResources(),
				R.drawable.star_null);
		mGoodBitmaps = new Bitmap[] { mBackGroupBitmap, mBackGroupBitmap,
				mGoodBitmap };
		mBadBitmaps = new Bitmap[] { mBackGroupBitmap, mBackGroupBitmap,
				mBadBitmap };

	}

	@Override
	public void onRatingChanged(RatingBar ratingBar, float rating,
			boolean fromUser) {
		if (rating > 2) {
			ratingBar
					.setProgressDrawable(buildRatingBarDrawables(mGoodBitmaps));
		} else {
			ratingBar.setProgressDrawable(buildRatingBarDrawables(mBadBitmaps));
		}
		switch (ratingBar.getId()) {
		case R.id.Seller_service_ratingbar:

			break;

		default:
			break;
		}

	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.stay_evaluate_backbutton:
			finish();
			break;
			
		case R.id.submit_evaluate_button:
			StayEvaluateActivity.mCartHelper.updateComment(
					StayEvaluateActivity.mEvaluateDatabase.getReadableDatabase(), 
					StayEvaluateActivity.mPreferences.getInt("userId", 0)+"", 
					intent.getStringExtra("proId"));
			StayEvaluateActivity.mStayEHandler.sendEmptyMessage(0x767);
			finish();
			break;

		default:
			break;
		}

	}

	/**
	 * 评价星条状态图片改变，当两颗星以上是显示黄色星星， 当两颗星星一下显示灰色星星
	 * 
	 * @param Bitmap
	 *            [] images 星条状态的图片组
	 * */
	private Drawable buildRatingBarDrawables(Bitmap[] images) {
		final int[] requiredIds = { android.R.id.background,
				android.R.id.secondaryProgress, android.R.id.progress };
		final float[] roundedCorners = new float[] { 5, 5, 5, 5, 5, 5, 5, 5 };
		Drawable[] pieces = new Drawable[3];
		for (int i = 0; i < 3; i++) {
			ShapeDrawable sd = new ShapeDrawable(new RoundRectShape(
					roundedCorners, null, null));
			BitmapShader bitmapShader = new BitmapShader(images[i],
					Shader.TileMode.REPEAT, Shader.TileMode.CLAMP);
			sd.getPaint().setShader(bitmapShader);
			ClipDrawable cd = new ClipDrawable(sd, Gravity.LEFT,
					ClipDrawable.HORIZONTAL);
			if (i == 0) {
				pieces[i] = sd;
			} else {
				pieces[i] = cd;
			}
		}
		LayerDrawable ld = new LayerDrawable(pieces);
		for (int i = 0; i < 3; i++) {
			ld.setId(i, requiredIds[i]);
		}
		return ld;
	}

}
