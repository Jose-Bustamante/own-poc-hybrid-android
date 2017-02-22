package com.mobgen.pochybridandroid;

import org.apache.cordova.CordovaActivity;
import org.apache.cordova.CordovaWebView;
import org.apache.cordova.CordovaWebViewImpl;
import org.apache.cordova.engine.SystemWebView;
import org.apache.cordova.engine.SystemWebViewEngine;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;

import java.util.Random;

public class MainActivity extends CordovaActivity implements View.OnClickListener {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		findViewById(R.id.js_execute).setOnClickListener(this);

		super.init();
		super.loadUrl(launchUrl);
	}

	@Override
	protected CordovaWebView makeWebView() {
		SystemWebView webView = (SystemWebView) findViewById(R.id.cordova_web_view);
		return new CordovaWebViewImpl(new SystemWebViewEngine(webView));
	}

	@Override
	protected void createViews() {
		appView.getView().requestFocusFromTouch();
	}


	@Override
	public void onClick(View view) {
		int id = view.getId();
		if (id == R.id.js_execute) {
			loadUrl("javascript:changeBackgroundColor('" + generateRandomColor() + "');");
		}
	}

	public void showAlertDialog(String text) {
		new AlertDialog.Builder(this)
				.setTitle(text)
				.setMessage(R.string.dialog_description)
				.setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int which) {
						dialog.dismiss();
					}
				}).show();
	}

	private String generateRandomColor() {
		Random rnd = new Random();
		int intColor = Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256));
		return String.format("#%06X", 0xFFFFFF & intColor);
	}
}