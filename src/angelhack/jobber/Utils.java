package angelhack.jobber;

import java.util.regex.Pattern;

import android.app.AlertDialog;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;
import android.util.Patterns;
import android.widget.Toast;

public class Utils {

	public static final String TAG = "GETLOST";

	public static final void toastShort(Context ctx, String msg) {
		Toast.makeText(ctx, msg, Toast.LENGTH_SHORT).show();
	}

	public static final void toastLong(Context ctx, String msg) {
		Toast.makeText(ctx, msg, Toast.LENGTH_LONG).show();
	}

	public static final void log(String msg) {
		Log.d(Utils.TAG, msg);
	}


	public static AlertDialog.Builder buildIndefiniteDialog(Context ctx, String title, String msg, boolean cancelable) {
		AlertDialog.Builder popupBuilder = new AlertDialog.Builder(ctx);
		popupBuilder.setTitle(title);
		popupBuilder.setMessage(msg);
		popupBuilder.setCancelable(cancelable);

		return popupBuilder;
	}

	public static boolean isValidEmail(String email) {
		Pattern pattern = Patterns.EMAIL_ADDRESS;
		return pattern.matcher(email).matches();
	}

	public static boolean isConnectingToInternet(Context ctx) {
		ConnectivityManager cm = (ConnectivityManager) ctx.getSystemService(Context.CONNECTIVITY_SERVICE);
		if (cm != null) {
			NetworkInfo[] info = cm.getAllNetworkInfo();
			if (info != null) {
				for (int i = 0; i < info.length; i++) {
					if (info[i].getState() == NetworkInfo.State.CONNECTED) {
						return true;
					}
				}
			}
		}
		return false;
	}
}
