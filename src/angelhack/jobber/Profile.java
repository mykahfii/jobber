package angelhack.jobber;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.FrameLayout;

public class Profile extends Activity {
	 private WebView mWebView;
	 private ProgressDialog progressDialog;
		
	 private static final LayoutParams ZOOM_PARAMS = 
	 new FrameLayout.LayoutParams(  
			 ViewGroup.LayoutParams.FILL_PARENT,  
			 ViewGroup.LayoutParams.WRAP_CONTENT,  
			 Gravity.BOTTOM);  
	 @Override
	 public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.profile);
	        mWebView = (WebView) findViewById(R.id.webview);
	        mWebView.getSettings().setJavaScriptEnabled(true);
	        mWebView.getSettings().setPluginsEnabled(true);
	        mWebView.getSettings().setAllowFileAccess(true);
	        mWebView.setInitialScale(100);
	        
	        final Activity activity = this;

	        mWebView.setWebViewClient(new WebViewClient() {
	            public boolean shouldOverrideUrlLoading(WebView view, String url) {              
	                view.loadUrl(url);
	                return true;
	            }
	            public void onLoadResource (WebView view, String url) {
	                if (progressDialog == null) {
	                    progressDialog = new ProgressDialog(activity);
	                    progressDialog.setMessage("Memanggil data . . .");
	                    progressDialog.show();
	                }
	            }
	            public void onPageFinished(WebView view, String url) {
	                if (progressDialog.isShowing()) {
	                    progressDialog.dismiss();
	                    progressDialog = null;
	                }
	            }
	        }); 
	        mWebView.loadUrl("http://facebook.com");
	        FrameLayout mContentView = (FrameLayout) getWindow().  
	        	    getDecorView().findViewById(android.R.id.content);  
	        	    final View zoom = this.mWebView.getZoomControls();  
	        	    mContentView.addView(zoom, ZOOM_PARAMS);  
	        	    zoom.setVisibility(View.GONE);  
	    }
	 @Override
	 public boolean onKeyDown(int keyCode, KeyEvent event) {
	     if ((keyCode == KeyEvent.KEYCODE_BACK) && mWebView.canGoBack()) {
	         mWebView.goBack();
	         return true;
	     }
	     return super.onKeyDown(keyCode, event);
	 }
}