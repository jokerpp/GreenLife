package com.modian.app.wxapi;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import cn.crane.application.greenlife.Constant;
import cn.crane.application.greenlife.utils.RefreshUtils;

import com.tencent.mm.sdk.constants.ConstantsAPI;
import com.tencent.mm.sdk.modelbase.BaseReq;
import com.tencent.mm.sdk.modelbase.BaseResp;
import com.tencent.mm.sdk.openapi.IWXAPI;
import com.tencent.mm.sdk.openapi.IWXAPIEventHandler;
import com.tencent.mm.sdk.openapi.WXAPIFactory;

public class WXPayEntryActivity extends Activity implements IWXAPIEventHandler {
	private IWXAPI wxApi;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		wxApi = WXAPIFactory.createWXAPI(this, Constant.WX_APP_ID, false);
		wxApi.handleIntent(getIntent(), this);
	}

	@Override
	protected void onNewIntent(Intent intent) {
		super.onNewIntent(intent);

		setIntent(intent);
		wxApi.handleIntent(intent, this);
	}

	// 微信发送请求到第三方应用时，会回调到该方法
	@Override
	public void onReq(BaseReq req) {
		// TODO Auto-generated method stub

	}

	// 第三方应用发送到微信的请求处理后的响应结果，会回调到该方法
	@Override
	public void onResp(BaseResp resp) {
		// TODO Auto-generated method stub
		switch (resp.errCode) {
		case BaseResp.ErrCode.ERR_OK:
			if (resp.getType() == ConstantsAPI.COMMAND_PAY_BY_WX) { // 微信支付
				RefreshUtils.sendRefreshPayWechatSuccess(this);
			}
			break;
		default:
			if (resp.getType() == ConstantsAPI.COMMAND_PAY_BY_WX) { // 微信支付
				RefreshUtils.sendRefreshPayWechatFail(this);
			}
			break;
		}
		this.finish();
	}
}
