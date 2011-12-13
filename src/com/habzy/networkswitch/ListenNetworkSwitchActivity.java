package com.habzy.networkswitch;

import com.habzy.networkswitch.NetworkSwitchListener.NetCheckReceiver;

import android.app.Activity;
import android.content.Context;
import android.content.IntentFilter;
import android.os.Bundle;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;

public class ListenNetworkSwitchActivity extends Activity {
	private NetCheckReceiver mReceiver;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		final TelephonyManager mTelephonyMgr = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
		mTelephonyMgr.listen(new NetworkSwitchListener(),
				PhoneStateListener.LISTEN_DATA_CONNECTION_STATE);

		mReceiver = new NetCheckReceiver();
		IntentFilter filter = new IntentFilter();
		filter.addAction(NetCheckReceiver.netACTION);
		registerReceiver(mReceiver, filter);

	}

	@Override
	protected void onDestroy() {
		unregisterReceiver(mReceiver);
		super.onDestroy();
	}

}