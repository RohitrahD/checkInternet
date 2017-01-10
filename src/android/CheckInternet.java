package cordova-plugin-check-internet;
import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CallbackContext;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Handler;
import android.content.Context;

public class CheckInternet extends CordovaPlugin {
    @Override
    public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
        if (action.equals("checkIntConn")) {
            String message = args.getString(0);
            this.checkIntConn(message, callbackContext);
            return true;
        }
        return false;
    }
	/*Check Internet Connection*/
    private void checkIntConn(String message, CallbackContext callbackContext) {
		if(!hasActiveInternetConnection(this.cordova.getActivity().getApplicationContext()))
        {
            callbackContext.error("Failure");
		}else{
			callbackContext.success("Success");
        }
    }
	private boolean isNetworkAvailable() {
		ConnectivityManager connectivityManager = (ConnectivityManager) this.cordova.getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
		return activeNetworkInfo != null && activeNetworkInfo.isConnected();
	}
	public boolean hasActiveInternetConnection(Context context) {
		if (isNetworkAvailable()) {
			try {
				HttpURLConnection urlc = (HttpURLConnection) (new URL("http://www.yahoo.com").openConnection());
				urlc.setRequestProperty("User-Agent", "Test");
				urlc.setRequestProperty("Connection", "close");
				urlc.setConnectTimeout(1500);
				urlc.connect();
				return true;
			} catch (IOException e) {
			}
		} else {
		}
		return false;
	}
}
