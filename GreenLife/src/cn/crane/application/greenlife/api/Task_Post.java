package cn.crane.application.greenlife.api;

import java.io.ByteArrayOutputStream;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.protocol.HTTP;

import cn.crane.application.greenlife.App;
import cn.crane.application.greenlife.R;
import cn.crane.application.greenlife.TestConfig;


import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.text.TextUtils;
import android.util.Base64;

/**
 * 
 * @author Administrator
 * 
 */
public class Task_Post extends AsyncTask<String, String, String> {
	private OnPostEndListener onPostEndListener;
	private HashMap<String, String> map = new HashMap<String, String>();
	private HashMap<String, String[]> mapArr = new HashMap<String, String[]>();
	private HashMap<String, Bitmap> hashMapBitmap = new HashMap<String, Bitmap>();
	private String apiName;

	public Task_Post(HashMap<String, String> map, String apiName,
			OnPostEndListener onPostEndListener) {
		super();
		this.apiName = apiName;
		this.onPostEndListener = onPostEndListener;
		this.map = map;
	}
	public Task_Post(HashMap<String, String> map,HashMap<String, String[]> mapArr, HashMap<String, Bitmap> hashMapBitmap,String apiName,
			OnPostEndListener onPostEndListener) {
		super();
		this.apiName = apiName;
		this.hashMapBitmap = hashMapBitmap;
		this.mapArr = mapArr;
		this.onPostEndListener = onPostEndListener;
		this.map = map;
	}

	public Task_Post(HashMap<String, String> map,
			HashMap<String, Bitmap> hashMapBitmap, String apiName,
			OnPostEndListener onPostEndListener) {
		super();
		this.onPostEndListener = onPostEndListener;
		this.map = map;
		this.hashMapBitmap = hashMapBitmap;
		this.apiName = apiName;
	}

	@Override
	protected void onPreExecute() {
		super.onPreExecute();
	}

	@Override
	protected String doInBackground(String... params) {
		String sResult = "";
		// List<NameValuePair> parameters = new ArrayList<NameValuePair>();
		// Iterator iter = map.entrySet().iterator();
		// while (iter.hasNext()) {
		// Map.Entry entry = (Map.Entry) iter.next();
		// Object key = entry.getKey();
		// Object val = entry.getValue();
		// parameters.add(new BasicNameValuePair(entry.getKey() + "", entry
		// .getValue() + ""));
		// }
		// sResult = HttpTools.post(API.getApiUrl(apiName), parameters);

		map.putAll(API_Contant.getCommonParameter());
		if (TestConfig.TEST) {
			try {
//				sResult = AssetsUtils.getStrFromAssetByApiName(
//						App.getInstance(), apiName);
			} catch (Exception e) {
				e.printStackTrace();
			}
			if (!TextUtils.isEmpty(sResult)) {
				return sResult;
			}
		}
		
		if(!App.checkNetwork())
		{
			return "{\"resultMessage\":\""+App.getStr(R.string.network_error)+"\",\"resultNumber\":1}";
		}
		MultipartEntity entity = new MultipartEntity();
		try {
			if (map != null) {
				Iterator iter = map.entrySet().iterator();
				while (iter.hasNext()) {
					Map.Entry entry = (Map.Entry) iter.next();
					Object key = entry.getKey();
					Object val = entry.getValue();
					if(val != null && val instanceof String [])
					{
						for(String v:(String [])val)
						{
							entity.addPart(entry.getKey() + "",new StringBody(entry.getValue() + "", Charset.forName(HTTP.UTF_8)));
						}
					}else
					{
						entity.addPart(entry.getKey() + "",new StringBody(entry.getValue() + "", Charset.forName(HTTP.UTF_8)));
					}
				}
			}
			if(mapArr != null)
			{
				Iterator iter = mapArr.entrySet().iterator();
				while (iter.hasNext()) {
					Map.Entry entry = (Map.Entry) iter.next();
					Object key = entry.getKey();
					Object val = entry.getValue();
					if(val != null && val instanceof String [])
					{
						for(String v:(String [])val)
						{
							entity.addPart(entry.getKey() + "",new StringBody(v + "", Charset.forName(HTTP.UTF_8)));
						}
					}
				}
			}
			if (hashMapBitmap != null) {
				Iterator iter = hashMapBitmap.entrySet().iterator();
				while (iter.hasNext()) {
					Map.Entry entry = (Map.Entry) iter.next();
					Object key = entry.getKey();
					Object val = entry.getValue();
					if (val != null && val instanceof Bitmap) {
						ByteArrayOutputStream of = new ByteArrayOutputStream();
						((Bitmap) val).compress(Bitmap.CompressFormat.JPEG, 60,of);
						// entity.addPart(entry.getKey() + "", new
						// ByteArrayBody(
						// of.toByteArray(), "image.jpg"));
						entity.addPart(entry.getKey() + "",
								new StringBody(Base64.encodeToString(of.toByteArray(),
										Base64.DEFAULT).toString(), Charset
										.forName(HTTP.UTF_8)));
//						 result = Base64.encodeToString(bitmapBytes, Base64.DEFAULT);  
					}
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		sResult = HttpTools.postData(API.getApiUrl(apiName), entity);
		return sResult;
	}

	@Override
	protected void onPostExecute(String result) {
		super.onPostExecute(result);
		if(TextUtils.isEmpty(result))
		{
			App.showToast(R.string.connect_server_fail);
		}
		if (onPostEndListener != null) {
			onPostEndListener.onPostEnd(result);
		}
	}

	public interface OnPostEndListener {
		public void onPostEnd(String sResult);
	}

	public static void clearTask(Task_Post post) {
		if (post != null && !post.isCancelled()) {
			post.cancel(true);
		}
	}

}
