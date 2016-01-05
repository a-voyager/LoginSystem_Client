package com.voyager.net;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import com.voyager.constant.Constant;
import com.voyager.engin.Result;
import com.voyager.engin.UserBean;

public class NetHelper {

	private static final String ENCODING = "UTF-8";

	@SuppressWarnings("finally")
	public Result post(UserBean user) {
		Result result = new Result();
		// ����HttpClientʵ������
		CloseableHttpClient httpclient = HttpClients.createDefault();
		// ����Post����
		HttpPost httppost = new HttpPost(Constant.SERVER_ADDR);
		// ���������б�
		List<BasicNameValuePair> formparams = new ArrayList<BasicNameValuePair>();
		// ���������б��������
		Map<String, String> infoList = user.getInfoList();
		Set<Entry<String, String>> entrySet = infoList.entrySet();
		for (Entry<String, String> e : entrySet) {
			formparams.add(new BasicNameValuePair(e.getKey(), e.getValue()));
		}

		UrlEncodedFormEntity uefEntity;
		try {
			uefEntity = new UrlEncodedFormEntity(formparams, ENCODING);
			// ������������
			httppost.setEntity(uefEntity);
			System.out.println("executing request " + httppost.getURI());
			// ִ�����Ӳ���
			CloseableHttpResponse response = httpclient.execute(httppost);
			getResponse(result, response);
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			// �ر�����,�ͷ���Դ
			try {
				httpclient.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			return result;
		}
	}

	private void getResponse(Result result, CloseableHttpResponse response)
			throws IOException {
		try {
			HttpEntity entity = response.getEntity();
			if (entity != null) {
				Header[] allHeaders = response.getAllHeaders();
				for (Header h : allHeaders) {
					System.out.println(h.getName() + "  :  " + h.getValue());

					// ����result.responseCode
					if (h.getName().equals("result")) {
						// if (h.getValue().equals(Constant.RESPONSE_OK)) {
						result.setResponseCode(h.getValue());
						System.out.println(result);
						// }
					}

					// ����result.user
					if (h.getName().startsWith("#")) {
					}

				}
			}
		} finally {
			response.close();
		}
	}

}
