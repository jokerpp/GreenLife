package cn.crane.framework.utils.stat;

import android.app.Activity;
import android.content.Context;


import cn.crane.application.greenlife.Constant;
import cn.crane.framework.utils.ChannelUtil;

import com.baidu.mobstat.SendStrategyEnum;
import com.baidu.mobstat.StatService;

/**
 * @author Ruifeng.yu E-mail:xyyh0116@aliyun.com
 * @version Create Time：May 19, 2015 12:29:18 AM
 * 
 */
public class BaiduStatUtil {
	public static boolean enableStat = Constant.enableBaiduStat;

	public static void initBaiduStatData(Context context) {
		if (enableStat) {
			StatService.setAppKey("1d8f812109");
//			StatService.setAppChannel("Baidu Market");
			StatService.setAppChannel(ChannelUtil.getChannel(context, "GreenLife"));
			StatService.setLogSenderDelayed(30);
			StatService.setOn(context, StatService.EXCEPTION_LOG);
			StatService.setSendLogStrategy(context, SendStrategyEnum.APP_START,
					30, false);
			StatService.setSessionTimeOut(30);
		}
	}

	public static void onActivityPause(Activity activity) {
		if (enableStat) {
			StatService.onPause(activity);
		}
	}

	public static void onActivityResume(Activity activity) {
		if (enableStat) {
			StatService.onResume(activity);
		}
	}

}
