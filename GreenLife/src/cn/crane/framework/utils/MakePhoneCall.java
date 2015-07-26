package cn.crane.framework.utils;

import cn.crane.application.greenlife.R;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;

public class MakePhoneCall {
	public static void call(final Context context,final String tel) {
		DialogSelector.showPhoneCall(context, context.getString(R.string.make_phone_call) + tel +"?", new DialogSelector.OnItemSelectListener() {
			
			@Override
			public void onItemSelected(int position) {
				Intent intent=new Intent();
				intent.setAction(Intent.ACTION_DIAL);   //android.intent.action.DIAL
				intent.setData(Uri.parse("tel:"+tel));
				context.startActivity(intent);
			}
		});
	}
}
