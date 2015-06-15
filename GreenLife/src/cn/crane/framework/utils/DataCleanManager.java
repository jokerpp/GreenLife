package cn.crane.framework.utils;

import java.io.File;
import android.content.Context;
import android.os.Environment;

/**
 * 清除缓存数据
 * @author Ruifeng.yu  Email:xyyh0116@aliyun.com
 *
 * @date 2014-10-29
 */
public class DataCleanManager {
	/**
	 * 清除缓存(/data/data/com.xxx.xxx/cache)
	 * @param context
	 */
	public static void cleanInternalCache(Context context) {
		deleteFilesByDirectory(context.getCacheDir());
	}

	/**
	 * 清除所有数据库(/data/data/com.xxx.xxx/databases)
	 * 
	 * @param context
	 */
	public static void cleanDatabases(Context context) {
		deleteFilesByDirectory(new File("/data/data/"
				+ context.getPackageName() + "/databases"));
	}

	/**
	 * 清除SharedPreference(/data/data/com.xxx.xxx/shared_prefs) 
	 * @param context
	 */
	public static void cleanSharedPreference(Context context) {
		deleteFilesByDirectory(new File("/data/data/"
				+ context.getPackageName() + "/shared_prefs"));
	}

	/**
	 * 按名字清除本应用数据
	 * @param context
	 * @param dbName
	 */
	public static void cleanDatabaseByName(Context context, String dbName) {
		context.deleteDatabase(dbName);
	}

	/**
	 * 清除/data/data/com.xxx.xxx/files下的内容
	 * @param context
	 */
	public static void cleanFiles(Context context) {
		deleteFilesByDirectory(context.getFilesDir());
	}

	/**
	 * 清除外部cache下的内容(/mnt/sdcard/android/data/com.xxx.xxx/cache) 
	 * @param context
	 */
	public static void cleanExternalCache(Context context) {
		if (Environment.getExternalStorageState().equals(
				Environment.MEDIA_MOUNTED)) {
			deleteFilesByDirectory(context.getExternalCacheDir());
		}
	}

	/**
	 * 清除自定义路径下的文件
	 * @param filePath
	 */
	public static void cleanCustomCache(String filePath) {
		deleteFilesByDirectory(new File(filePath));
	}

	/**
	 * 清除本应用所有的数据
	 * @param context
	 * @param filepath
	 */
	public static void cleanApplicationData(Context context, String... filepath) {
		cleanInternalCache(context);
		cleanExternalCache(context);
		cleanDatabases(context);
		cleanSharedPreference(context);
		cleanFiles(context);
		for (String filePath : filepath) {
			cleanCustomCache(filePath);
		}
	}
	/**
	 * 清除本应用所有的数据
	 * @param context
	 * @param filepath
	 */
	public static void cleanApplicationData(Context context) {
		cleanInternalCache(context);
		cleanExternalCache(context);
		cleanDatabases(context);
		cleanSharedPreference(context);
		cleanFiles(context);
	}

	/**
	 * 删除某个文件夹下的文件，如果传入的directory是个文件，将不做处理 
	 * @param directory  文件夹
	 * 
	 */
	private static void deleteFilesByDirectory(File directory) {
		if (directory != null && directory.exists() && directory.isDirectory()) {
			for (File item : directory.listFiles()) {
				item.delete();
			}
		}
	}
}