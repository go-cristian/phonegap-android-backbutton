package com.globant.backplugin;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaInterface;
import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CordovaWebView;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.annotation.SuppressLint;
import android.app.FragmentManager.OnBackStackChangedListener;
import android.content.Intent;
import android.widget.Toast;

public class BackPlugin extends CordovaPlugin {

	public static final String ACTION_ADD_CALENDAR_ENTRY = "addCalendarEntry";

	private CallbackContext callbackContext;

	@SuppressLint("NewApi")
	@Override
	public void initialize(CordovaInterface cordova, CordovaWebView webView) {
		// TODO Auto-generated method stub
		OnBackStackChangedListener onBackStackChangedListener = new OnBackStackChangedListener() {

			@Override
			public void onBackStackChanged() {
				Toast.makeText(BackPlugin.this.cordova.getActivity(),
						"back called", Toast.LENGTH_LONG).show();
				if (callbackContext != null) {
					callbackContext.success();
				}
			}
		};
		this.cordova.getActivity().getFragmentManager()
				.addOnBackStackChangedListener(onBackStackChangedListener);
		Toast.makeText(this.cordova.getActivity(), "Init", Toast.LENGTH_LONG)
				.show();
		super.initialize(cordova, webView);
	}

	@Override
	public boolean execute(String action, JSONArray args,
			CallbackContext callbackContext) throws JSONException {
		try {
			if (ACTION_ADD_CALENDAR_ENTRY.equals(action)) {
				this.callbackContext = callbackContext;
				return true;
			}
			callbackContext.error("Invalid action");
			return false;
		} catch (Exception e) {
			System.err.println("Exception: " + e.getMessage());
			callbackContext.error(e.getMessage());
			return false;
		}
	}

}
