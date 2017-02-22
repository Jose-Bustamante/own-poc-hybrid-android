package com.mobgen.pochybridandroid;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaPlugin;
import org.json.JSONArray;
import org.json.JSONException;

public class HybridBridge extends CordovaPlugin {

	private static final String ACTION = "showAlert";

	public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
		if (ACTION.equals(action)) {
			showAlert(args, callbackContext);
			return true;
		}
		callbackContext.error("Invalid action");
		return false;
	}

	private void showAlert(final JSONArray args, final CallbackContext ctx) {
		final MainActivity mainActivity = (MainActivity) this.cordova.getActivity();
		mainActivity.runOnUiThread(new Runnable() {
			public void run() {
				try {
					mainActivity.showAlertDialog(args.getString(0));
					ctx.success();
				} catch (JSONException e) {
					String errorMessage = "An error occurred";
					ctx.error(errorMessage);
				}
			}
		});
	}
}
