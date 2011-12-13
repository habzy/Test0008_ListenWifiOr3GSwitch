package com.habzy.networkswitch;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.util.Log;

public class NetworkSwitchListener extends PhoneStateListener {

	private static final String TAG = "NetworkSwitchListener";
	
	public NetworkSwitchListener()
	{
		
	}

	@Override
	public void onDataConnectionStateChanged(int state) {
		switch (state) {
		case TelephonyManager.DATA_DISCONNECTED: {
			Log.d(TAG, "DATA_DISCONNECTED");
			break;
		}
		case TelephonyManager.DATA_CONNECTING: {
			Log.d(TAG, "DATA_CONNECTING");
			break;
		}
		case TelephonyManager.DATA_CONNECTED: {
			Log.d(TAG, "DATA_CONNECTED");
			break;
		}
		}

	}

	public static class  NetCheckReceiver extends BroadcastReceiver {

		public static final String netACTION = "android.net.conn.CONNECTIVITY_CHANGE";

		@Override
		public void onReceive(Context context, Intent intent) {

			checkNetWork(context);

		}
	}

	private static boolean checkNetWork(Context context) {

		ConnectivityManager connect = (ConnectivityManager) context
				.getSystemService(Context.CONNECTIVITY_SERVICE);

		if (connect != null) {
			NetworkInfo mobile = connect
					.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);

			NetworkInfo wifi = connect
					.getNetworkInfo(ConnectivityManager.TYPE_WIFI);

			if (mobile != null
					&& mobile.getState() == NetworkInfo.State.CONNECTED) {
				Log.d(TAG, "###########3G");
				return true;
			} else if (wifi != null
					&& wifi.getState() == NetworkInfo.State.CONNECTED) {
				Log.d(TAG, "###########Wifi");
				return true;
			}
		}
		Log.d(TAG, "###########dis...");
		return false;

	}

}
