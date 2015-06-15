package cn.crane.framework.activity;

import java.util.HashMap;
import java.util.Iterator;
import android.app.Activity;
/**
 * 
 * @author Ruifeng.yu  Email:xyyh0116@aliyun.com
 *
 * @date 2014-10-29
 */
public class ActivityMannager {
	private HashMap<String, Activity> mapActivities = new HashMap<String, Activity>();
	private static ActivityMannager instance = null;

	public static ActivityMannager getInstance() {
		if (instance == null)
			instance = new ActivityMannager();
		return instance;
	}

	public ActivityMannager() {
		mapActivities.clear();
	}

	public void addActivity(BaseActivity activity) {
		try {
			if (activity != null) {
				mapActivities.put(activity.TAG, activity);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void removeActivity(BaseActivity activity) {
		try {
			if (activity != null) {
				mapActivities.remove(activity.TAG);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void clearActivity() {
		try {
			Iterator iter = mapActivities.keySet().iterator();
			while (iter.hasNext()) {
				Object key = iter.next();
				Activity val = mapActivities.get(key);
				if (val != null)
					val.finish();
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
