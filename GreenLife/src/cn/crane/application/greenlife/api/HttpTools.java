package cn.crane.application.greenlife.api;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpEntityEnclosingRequestBase;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

/**
 * Connect to the internet
 * 
 * @author yurf
 * 
 */
public class HttpTools {
	/**
	 * Post data to server
	 * 
	 * @param sUrl
	 * @param entity
	 * @return
	 */
	public static String postData(String sUrl, MultipartEntity entity) {
		String destUrl = "";
		destUrl = sUrl;
		String sResult = "";
		// instantiate HttpPost object from the url address
		HttpEntityEnclosingRequestBase httpRequest = new HttpPost(destUrl);
		try {
			httpRequest.setEntity(entity);
			// execute the post and get the response from servers
			HttpResponse httpResponse = new DefaultHttpClient()
					.execute(httpRequest);
			int code = httpResponse.getStatusLine().getStatusCode();
			if (httpResponse.getStatusLine().getStatusCode() == 200) {
				// get the result
				String strResult = EntityUtils.toString(httpResponse
						.getEntity());
				sResult = strResult;
				System.out.println(strResult);
			} else {
				System.out.println("Error Response"
						+ httpResponse.getStatusLine().toString());
			}
		} catch (Exception e) {
			System.out.println("error occurs");
		}
		return sResult;
	}

	/**
	 * Get data from server 
	 * 
	 * @param urlPath
	 * @return
	 */
	public static String getData(String sUrl) {
		String urlPath = sUrl;
		ByteArrayOutputStream outStream = null;
		String sResult = "";
		try {
			outStream = new ByteArrayOutputStream();
			byte[] data = new byte[1024];
			int len = 0;
			URL url = new URL(urlPath);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			InputStream inStream = conn.getInputStream();
			while ((len = inStream.read(data)) != -1) {
				outStream.write(data, 0, len);
			}
			inStream.close();
			sResult = new String(outStream.toByteArray()).trim();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return sResult;
	}

	public static String post(String sUrl, List<NameValuePair> parameters) {
		HttpClient httpClient = new DefaultHttpClient();
		HttpPost httpPost = new HttpPost(sUrl);
		String sResult = "";

		try {
			HttpEntity entity = new UrlEncodedFormEntity(parameters, HTTP.UTF_8); // 设置编码，防止中文乱码
			httpPost.setEntity(entity);
			// httpClient执行httpPost表单提交
			HttpResponse response = httpClient.execute(httpPost);
			// 得到服务器响应实体对象
			HttpEntity responseEntity = response.getEntity();
			if (responseEntity != null) {
				sResult = EntityUtils.toString(responseEntity, "utf-8");
				System.out.println("表单上传成功！");
			} else {
				System.out.println("服务器无响应！");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// 释放资源
			httpClient.getConnectionManager().shutdown();
		}
		return sResult.replace("，", ",");
	}

}
