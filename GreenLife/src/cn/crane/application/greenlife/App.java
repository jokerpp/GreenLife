package cn.crane.application.greenlife;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import cn.crane.application.greenlife.utils.SharedPref;

import android.app.ActivityManager;
import android.app.Application;
import android.app.ActivityManager.RunningTaskInfo;
import android.content.ComponentName;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.telephony.TelephonyManager;
import android.widget.Toast;
/**
 * 
 * @author Ruifeng.yu  Email:xyyh0116@aliyun.com
 *
 * @date Jun 8, 2015
 */
public class App extends Application {
	public static final String ACTION_LOGIN_CHANGED = "action_login_changed";
	public static float fDensity;
	private static App instance;
	public static  String packageName = "";
	private static String uniqueId;
	private Toast toast;
	

	@Override
	public void onCreate() {
		super.onCreate();
		instance = this;
		packageName = getPackageName();
		fDensity = getResources().getDisplayMetrics().density;
		SharedPref.getInstance().init(this);
//		UpLoadGPS.startAction(this);
		
	}
	
	

		
	/**
	 * 判断当前应用程序处于前台还是后台
	 */
	public static boolean isApplicationBroughtToBackground(final Context context) {
		ActivityManager am = (ActivityManager) context
				.getSystemService(Context.ACTIVITY_SERVICE);
		List<RunningTaskInfo> tasks = am.getRunningTasks(1);
		if (!tasks.isEmpty()) {
			ComponentName topActivity = tasks.get(0).topActivity;
			if (!topActivity.getPackageName().equals(context.getPackageName())) {
				return true;
			}
		}
		return false;
	}


	/**
	 * 获取设备号
	 * 
	 * @return
	 */
	public static String getDeviceNo() {
		try {
			final TelephonyManager tm = (TelephonyManager) instance
					.getSystemService(Context.TELEPHONY_SERVICE);

			final String tmDevice, tmSerial, tmPhone, androidId;
			tmDevice = "" + tm.getDeviceId();
			tmSerial = "" + tm.getSimSerialNumber();
			androidId = ""
					+ android.provider.Settings.Secure.getString(
							instance.getContentResolver(),
							android.provider.Settings.Secure.ANDROID_ID);

			UUID deviceUuid = new UUID(androidId.hashCode(),
					((long) tmDevice.hashCode() << 32) | tmSerial.hashCode());
			uniqueId = deviceUuid.toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return uniqueId;
	}

	/**
	 * Show Toast
	 * 
	 * @param msg
	 *            the message to show
	 */
	private void showTextToast(String msg) {
		if (toast == null) {
			toast = android.widget.Toast.makeText(getApplicationContext(), msg,
					android.widget.Toast.LENGTH_SHORT);
		} else {
			toast.setText(msg);
		}
		toast.show();
	}

	/**
	 * Show Toast
	 * 
	 * @param resId
	 *            the message resources id in string.xml
	 */
	private void showTextToast(int resId) {
		showTextToast(getString(resId));
	}

	/**
	 * Show Toast
	 * 
	 * @param msg
	 */
	public static void showToast(String msg) {
		instance.showTextToast(msg);
	}

	/**
	 * Show Toast
	 * 
	 * @param resId
	 */
	public static void showToast(int resId) {
		instance.showTextToast(resId);
	}

	/**
	 * Check network available
	 * 
	 * @return
	 */
	public static boolean checkNetwork() {
		NetworkInfo info = ((ConnectivityManager) instance
				.getSystemService(Context.CONNECTIVITY_SERVICE))
				.getActiveNetworkInfo();

		return (info != null) && (info.isConnected());
	}

	public static App getInstance() {
		return instance;
	}

	/**
	 * get resId by drawable name
	 * 
	 * @param drawableName
	 *            drawable name
	 * @return resId
	 */
	public static int getDrawableId(String drawableName) {
		int iRes = 0;
		if (instance != null) {
			iRes = instance.getResources().getIdentifier(drawableName,
					"drawable", instance.getPackageName());

		}
		return iRes;

	}

	/**
	 * get resId by string name
	 * 
	 * @param stringName
	 *            string name
	 * @return resId
	 */
	public static int getStringId(String stringName) {
		int iRes = 0;
		if (instance != null) {
			iRes = instance.getResources().getIdentifier(stringName, "string",
					instance.getPackageName());
		}
		return iRes;

	}

	public static String getStr(int resid) {
		if (instance != null) {
			return instance.getString(resid);
		}
		return "";
	}

	
	/**
	 * 获取版本号
	 * 
	 * @return 当前应用的版本号
	 */
	public static String getVersion() {
		try {
			PackageManager manager = instance.getPackageManager();
			PackageInfo info = manager.getPackageInfo(instance.getPackageName(), 0);
			return instance.getString(R.string.app_name) + " V" + info.versionName;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}
	
	/**
	 * 获取版本号
	 * 
	 * @return 当前应用的版本号
	 */
	public static String getVersionName() {
		try {
			PackageManager manager = instance.getPackageManager();
			PackageInfo info = manager.getPackageInfo(instance.getPackageName(), 0);
			return info.versionName;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}
	/**
	 * 获取版本号
	 * 
	 * @return 当前应用的版本号
	 */
	public static int getVersionCode() {
		try {
			PackageManager manager = instance.getPackageManager();
			PackageInfo info = manager.getPackageInfo(instance.getPackageName(), 0);
			return info.versionCode;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 1;
	}
	
	/**
	 * get current time
	 * 
	 * @return yyyy-mm-dd
	 */
	public static String getCurrentTime() {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String sTime = format.format(new Date());
		System.out.println("CurrentTime:" + sTime);
		return sTime;
	}

	

}
