package com.globant.backplugin;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaInterface;
import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CordovaWebView;
import org.apache.cordova.PluginResult;
import org.json.JSONArray;
import org.json.JSONException;

import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnKeyListener;

public class BackPlugin extends CordovaPlugin {

    public static final String ACTION_ADD_CALLBACK = "addCallback";
    public static final String ACTION_REMOVE_CALLBACK = "removeCallback";
    private CallbackContext callbackContext;

    @Override
    public void initialize(final CordovaInterface cordova, CordovaWebView webView) {
        super.initialize(cordova, webView);
        webView.setOnKeyListener(new OnKeyListener() {

            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {

                if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0
                        && event.getAction() == KeyEvent.ACTION_UP) {
                    if (callbackContext != null) {
                        cordova.getThreadPool().execute(new Runnable() {
                            public void run() {
                                // Main Code goes here
                                PluginResult result = new PluginResult(PluginResult.Status.OK);
                                result.setKeepCallback(true);
                                callbackContext.sendPluginResult(result);
                            }
                        });
                    }
                    return true;
                }
                return false;
            }
        });
    }

    @Override
    public boolean execute(String action, JSONArray args,
            CallbackContext callbackContext) throws JSONException {
        try {
            if (ACTION_ADD_CALLBACK.equals(action)) {
                this.callbackContext = callbackContext;
                return true;
            }
            if (ACTION_REMOVE_CALLBACK.equals(action)) {
                this.callbackContext = null;
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
