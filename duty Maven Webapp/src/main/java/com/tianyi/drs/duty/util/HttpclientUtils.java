package com.tianyi.drs.duty.util;

import java.io.IOException; 
import java.util.ArrayList; 
import java.util.List;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.HttpVersion;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.client.params.CookiePolicy;
import org.apache.http.conn.HttpHostConnectException;
import org.apache.http.conn.scheme.PlainSocketFactory;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.params.HttpProtocolParams;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils; 

public class HttpclientUtils { 
	private static HttpParams httpParams;
	private static ThreadSafeClientConnManager cm;
	static {
		httpParams = new BasicHttpParams();
		HttpConnectionParams.setConnectionTimeout(httpParams, 100000);
		HttpConnectionParams.setSoTimeout(httpParams, 100000);
		HttpProtocolParams.setVersion(httpParams, HttpVersion.HTTP_1_1);
		SchemeRegistry schemeRegistry = new SchemeRegistry();
		schemeRegistry.register(new Scheme("http", 80, PlainSocketFactory
				.getSocketFactory()));
		schemeRegistry.register(new Scheme("https", 443, PlainSocketFactory
				.getSocketFactory()));
		cm = new ThreadSafeClientConnManager(schemeRegistry);
		cm.setMaxTotal(250);
		cm.setDefaultMaxPerRoute(50);
	}

	public static void release() {
		if (cm != null) {
			cm.shutdown();
		}
	}

	public static HttpClient getClient() {
		HttpClient client = new DefaultHttpClient(cm, httpParams);
		client.getParams().setParameter("http.conn-manager.timeout", 100000L);
		client.getParams().setParameter("http.protocol.wait-for-continue",
				100000L);
		client.getParams().setParameter("http.protocol.cookie-policy",
				CookiePolicy.BROWSER_COMPATIBILITY);
		return client;
	}

	public static HttpResponse getResponse(Map<String, String> map,
			HttpRequestBase req) {
		try {
			return getClient().execute(req);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	private static HttpPost getHttpPost(String url) {
		HttpPost httpPost = new HttpPost(url);
		httpPost.addHeader("Content-Type", "text/xml");
		return httpPost;
	}

	/**
	 * 发送xml体到视频处理单元
	 * 
	 * @param url
	 * @param param
	 * @return result
	 * @throws Exception
	 * @throws Exception
	 * @throws ClientProtocolException
	 * @throws IOException
	 */
	public static String postXML(String url, String param) throws Exception {
		HttpClient httpClient = getClient();
		String result = "";
		if (httpClient != null) {
			HttpPost post = getHttpPost(url);
			try {
				if (param != null) {
					StringEntity entity = new StringEntity(param, HTTP.UTF_8);
					entity.setContentType("text/xml");
					entity.setContentEncoding(HTTP.UTF_8);
					post.setEntity(entity);
				}
				HttpResponse response = httpClient.execute(post);
				int statusCode = response.getStatusLine().getStatusCode();
				if (statusCode == HttpStatus.SC_OK) {
					result = EntityUtils.toString(response.getEntity());
				} else {

				}
			} catch (HttpHostConnectException e) {
				
				post.abort();
				throw e;
			}
		}
		return result;
	}

 

	public static void main1(String[] args) throws ClientProtocolException,
			IOException {

		HttpClient httpclient = getClient();
		HttpPost httpPost = new HttpPost(
				"http://localhost:8181/logSearch/home/aggregate");
		List<NameValuePair> nvps = new ArrayList<NameValuePair>();
		nvps.add(new BasicNameValuePair("cmsId", "123456"));
		nvps.add(new BasicNameValuePair("beginTime", "2012-01-05 10:10:00"));
		nvps.add(new BasicNameValuePair("endTime", "2014-07-21 10:21:10"));
		nvps.add(new BasicNameValuePair("groupByType", "year"));
		nvps.add(new BasicNameValuePair("operationTypes",
				"device_organ_user_role_login_exit_config"));
		nvps.add(new BasicNameValuePair("organIds",
				"0000000000200000000000002650000"));

		httpPost.setEntity(new UrlEncodedFormEntity(nvps, HTTP.UTF_8));

		HttpEntity entity = httpclient.execute(httpPost).getEntity();
		System.err.println(EntityUtils.toString(entity));
		httpclient.getConnectionManager().shutdown();

	}

	public static void main(String[] args) throws Exception {

		// String
		// url="http://192.168.4.95:8181/TaskManager/videoRetrievalController/start?supportInputStream";

		//String url = "http://192.168.4.95:8181/TaskManager/videoRetrieval/gainRetrievalTaskId";
		System.out.println(postXML("http://25.30.5.105:8080/drs/home/cacheMonitor", "<><><><><>"));
	}
 

	

}
