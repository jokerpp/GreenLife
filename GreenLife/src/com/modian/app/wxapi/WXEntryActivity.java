package com.modian.app.wxapi;


import cn.crane.application.greenlife.Constant;
import cn.crane.application.greenlife.utils.RefreshUtils;

import com.tencent.mm.sdk.constants.ConstantsAPI;
import com.tencent.mm.sdk.modelbase.BaseReq;
import com.tencent.mm.sdk.modelbase.BaseResp;
import com.tencent.mm.sdk.modelmsg.SendAuth;
import com.tencent.mm.sdk.openapi.IWXAPI;
import com.tencent.mm.sdk.openapi.IWXAPIEventHandler;
import com.tencent.mm.sdk.openapi.WXAPIFactory;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;


public class WXEntryActivity extends Activity implements IWXAPIEventHandler {
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
		if (resp.getType() == ConstantsAPI.COMMAND_SENDAUTH) { // 微信登陆
			if (BaseResp.ErrCode.ERR_OK == resp.errCode) {
				SendAuth.Resp sendResp = (SendAuth.Resp) resp;
				String code = sendResp.code;
				if (code != null && code.length() > 0) {
//					TalkingDataHelper.onEvent(this,
//							TalkingData.EVENT_ID_BUNDLE_SUCCESS,
//							TalkingData.EVENT_LABEL_BUNDLE_WECHAT);

					RefreshUtils.sendRefreshWechatChange(this, code);
				}
			}
		} else if (resp.getType() == ConstantsAPI.COMMAND_SENDMESSAGE_TO_WX) {
			if (BaseResp.ErrCode.ERR_OK == resp.errCode) {
//				TalkingDataHelper.onEvent(this,
//						TalkingData.EVENT_ID_SHARE_SUCCESS,
//						TalkingData.EVENT_LABEL_SHARE_WECHAT);
//
//				DialogUtils.getInstance().showTips(this,
//						getString(R.string.wechat_share_success));
				RefreshUtils.sendRefreshShareSuccess(this);
			} else {
				if (BaseResp.ErrCode.ERR_USER_CANCEL != resp.errCode) {
//					DialogUtils.getInstance().showTips(this,
//							getString(R.string.wechat_share_fail));
				}
			}
		}
		this.finish();
	}
}
