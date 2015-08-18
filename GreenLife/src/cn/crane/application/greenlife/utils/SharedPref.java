package cn.crane.application.greenlife.utils;

import android.content.Context;
import android.content.SharedPreferences;
/**
 * 存取本地数据
 * @author Administrator
 *
 */
public class SharedPref {
	public static final String FIRST_USE = "first_use";
	public static final String LOGIN_ACCOUNT = "account";
	public static final String LOGIN_PSW = "psw";
	public static final String REMENBER_PSW = "remenber_psw";
	public static final String LOCALE = "Locale";
	
	public static final String USER_NOTICE_1 = "user_notice1";
	public static final String USER_NOTICE_2 = "user_notice2";
	
	private Context context;
	private SharedPreferences preferences;
	private static SharedPref instance;

	public static SharedPref getInstance() {
		if (instance == null)
			instance = new SharedPref();
		return instance;
	}

	private SharedPref() {
	}

	public void init(Context context) {
		this.context = context.getApplicationContext();
		preferences = this.context.getSharedPreferences(context.getPackageName(),
				Context.MODE_PRIVATE);
	}

	public boolean getSharedPreferenceAsBoolean(String name,
			boolean defaultValue) {
		return preferences.getBoolean(name, defaultValue);
	}

	public void setSharedPreferenceAsBoolean(String name, boolean value) {
		SharedPreferences.Editor editor = preferences.edit();
		editor.putBoolean(name, value);
		editor.commit();
	}

	public float getSharedPreferenceAsFloat(String name, float defaultValue) {
		return preferences.getFloat(name, defaultValue);
	}

	public void setSharedPreferenceAsFloat(String name, float value) {
		SharedPreferences.Editor editor = preferences.edit();

		editor.putFloat(name, value);
		editor.commit();
	}

	public int getSharedPreferenceAsInt(String name, int defaultValue) {
		return preferences.getInt(name, defaultValue);
	}

	public void setSharedPreferenceAsInt(String name, int value) {
		SharedPreferences.Editor editor = preferences.edit();

		editor.putInt(name, value);
		editor.commit();
	}

	public long getSharedPreferenceAsLong(String name, long defaultValue) {
		return preferences.getLong(name, defaultValue);
	}

	public void setSharedPreferenceAsLong(String name, long value) {
		SharedPreferences.Editor editor = preferences.edit();

		editor.putLong(name, value);
		editor.commit();
	}

	public String getSharedPreference(String name, String defaultValue) {
		return preferences.getString(name, defaultValue);
	}

	public void setSharedPreference(String name, String value) {
		SharedPreferences.Editor editor = preferences.edit();

		editor.putString(name, value);
		editor.commit();
	}
	
	public boolean isUserNotice1Visiable() {
		return getSharedPreferenceAsBoolean(USER_NOTICE_1, true);
	}
	
	public void setUserNotice1Invisiable() {
		setSharedPreferenceAsBoolean(USER_NOTICE_1, false);
	}
	
	public boolean isUserNotice2Visiable() {
		return getSharedPreferenceAsBoolean(USER_NOTICE_2, true);
	}
	
	public void setUserNotice2Invisiable() {
		setSharedPreferenceAsBoolean(USER_NOTICE_2, false);
	}
	
	
}
