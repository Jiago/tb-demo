//package com.pactera.taobaoprogect.service;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Timer;
//import java.util.TimerTask;
//
//import com.pactera.taobaoprogect.R;
//import com.pactera.taobaoprogect.entity.ProductInfo;
//
//import android.app.ActivityManager;
//import android.app.AlertDialog;
//import android.app.Notification;
//import android.app.NotificationManager;
//import android.app.PendingIntent;
//import android.app.Service;
//import android.app.ActivityManager.RunningTaskInfo;
//import android.content.BroadcastReceiver;
//import android.content.Context;
//import android.content.Intent;
//import android.content.IntentFilter;
//import android.net.ConnectivityManager;
//import android.net.NetworkInfo;
//import android.os.CountDownTimer;
//import android.os.Handler;
//import android.os.IBinder;
//import android.os.Message;
//import android.provider.Settings;
//import android.widget.Toast;
//
//public class MyService extends Service {
//	private ConnectivityManager mConnManage;
//	private NetworkInfo mInfo;
//	private ActivityManager mActivityManager;
//	List<RunningTaskInfo> tasksInfo;
//	TimeCount time;
//	NotificationManager nm;
//	Notification notify;
//	private BroadcastReceiver mReceiver = new BroadcastReceiver() {
//		@Override
//		public void onReceive(Context context, Intent intent) {
//			String action = intent.getAction();
//			if (action.equals(mConnManage.CONNECTIVITY_ACTION)) {
//				mConnManage = (ConnectivityManager) getSystemService(context.CONNECTIVITY_SERVICE);
//				mInfo = mConnManage.getActiveNetworkInfo();
//				if (mInfo != null && mInfo.isAvailable()) {
//				} else {
//					intent = new Intent(Settings.ACTION_WIRELESS_SETTINGS);
//					PendingIntent pi = PendingIntent.getActivity(context, 0,
//							intent, 0);
//					NotificationManager nm = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
//					Notification notify = new Notification.Builder(
//							MyService.this).setAutoCancel(true)
//							.setTicker("网络连接异常")
//							.setSmallIcon(R.drawable.warning)
//							.setContentTitle("网络不可用")
//							.setContentText("当前网络不可用，请重新设置！")
//							.setWhen(System.currentTimeMillis())
//							.setContentIntent(pi).build();
//					nm.notify(0x234, notify);
//				}
//			}
//
//		}
//	};
//
//	@Override
//	public IBinder onBind(Intent intent) {
//		return null;
//	}
//
//	@Override
//	public void onCreate() {
//		super.onCreate();
//		mActivityManager = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
//		tasksInfo = mActivityManager.getRunningTasks(1);
//		IntentFilter mFilter = new IntentFilter();
//		mFilter.addAction(ConnectivityManager.CONNECTIVITY_ACTION);
//		registerReceiver(mReceiver, mFilter);
//		registerReceiver();
//	}
//
//	public void registerReceiver() {
//		IntentFilter filter = new IntentFilter(ShoppingCart.CTL_ACTION);
//		MyReceiver myReceiver = new MyReceiver();
//		registerReceiver(myReceiver, filter);
//	}
//
//	public class MyReceiver extends BroadcastReceiver {
//
//		@Override
//		public void onReceive(Context context, Intent intent) {
//			time = new TimeCount(10000, 1000);
//			time.start();
//			Intent intent1 = new Intent(context, ShoppingCart.class);
//			intent1.putExtra("proList", intent.getSerializableExtra("proList"));
//			PendingIntent pi = PendingIntent.getActivity(context, 0, intent1,
//					PendingIntent.FLAG_UPDATE_CURRENT);
//			nm = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
//			notify = new Notification.Builder(MyService.this)
//					.setAutoCancel(true).setTicker("请速购")
//					.setSmallIcon(R.drawable.shoppcartback)
//					.setContentTitle("有商品需要付款").setContentText("请在一分钟内付款！")
//					.setWhen(System.currentTimeMillis()).setContentIntent(pi)
//					.build();
//			nm.notify(0x234, notify);
//
//		}
//	}
//
//	class TimeCount extends CountDownTimer {
//
//		public TimeCount(long millisInFuture, long countDownInterval) {
//			super(millisInFuture, countDownInterval);
//
//		}
//
//		@Override
//		public void onFinish() {
//			nm.cancel(0x234);
//
//		}
//
//		@Override
//		public void onTick(long millisUntilFinished) {
//
//		}
//	}
//
//	@Override
//	public void onDestroy() {
//		super.onDestroy();
//	}
//
//	@Override
//	public int onStartCommand(Intent intent, int flags, int startId) {
//		return super.onStartCommand(intent, flags, startId);
//	}
//
//}
