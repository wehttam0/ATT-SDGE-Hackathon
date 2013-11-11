package com.cognition;

import org.scribe.builder.ServiceBuilder;
import org.scribe.builder.api.GoogleApi;
import org.scribe.model.OAuthRequest;
import org.scribe.model.Response;
import org.scribe.model.Token;
import org.scribe.model.Verb;
import org.scribe.model.Verifier;
import org.scribe.oauth.OAuthService;

import com.cognition.model.AuthPreferences;

import android.app.Activity;
import android.content.SharedPreferences.Editor;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class OAuth extends Activity{
	WebView webView;
	private static String CALLBACK_URL = "www.google.com";
	AuthPreferences ap;
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.oauthlayout);
		ap = new AuthPreferences(this);
		
		new OAuthLoginAsync().execute(); 
		
		webView = (WebView) findViewById(R.id.webview); 
        // change some webView settings 
        webView.getSettings().setJavaScriptEnabled(false); 
        webView.getSettings().setSavePassword(false); 
        webView.clearCache(true); 
        webView.clearFormData(); 
        webView.setWebViewClient(new WebViewClient() { 
            public boolean shouldOverrideUrlLoading(WebView view, String url) { 
                if (url.startsWith(CALLBACK_URL)) { 
                    Log.e("DebugTag", "Callback URL: >" + url); 
                    // fire off AsyncTask 
                    //runUserLogin(url); 
                    return true; 
                } 
                return false; 
            } 
        }); 
	  }
	
	private class OAuthLoginAsync extends AsyncTask<String, Void, String> { 
		
		@Override
        protected void onPreExecute() { 
        } 
        @Override
        protected String doInBackground(String... url) { 
            // oAuth verifier 
            // GRAB URI! 
        	
            try { 
                OAuthService service = new ServiceBuilder().provider(GoogleApi.class)
        				.apiKey("GetYourOwn!")
        				.apiSecret("GetYourOwn!")
        				.callback("http://www.google.com")
        				.build();
        		
        		Token requestToken = service.getRequestToken();
        		String authUrl = service.getAuthorizationUrl(requestToken);
        		Verifier v = new Verifier("verifier you got from the user");
        		Token accessToken = service.getAccessToken(requestToken, v); // the requestToken you had from step 2
        		OAuthRequest request = new OAuthRequest(Verb.GET, "https://www.google.com/accounts/OAuthAuthorizeToken");
        		service.signRequest(accessToken, request); // the access token from step 4
        		Response response = request.send();
        		
        		ap.setToken(accessToken.getToken());
                   
            } catch (Exception e) { 
                // Check log for login errors 
                Log.e("Google Login Error", "> " + e.getMessage()); 
                e.printStackTrace(); 
            } 
  
            return ""; 
        } 
//        @Override
//        protected void onPostExecute(AccessToken accessToken) { 
//            Log.d("DebugTag", "Time to change"); 
//            openDrawer(); 
//        } 
    }
}
