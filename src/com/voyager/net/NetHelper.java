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

/**
 * 网络操作管理类
 * 
 * @author wuhaojie
 *
 */
public class NetHelper {

	/**
	 * 通信编码方式
	 */
	private static final String ENCODING = "UTF-8";

	/**
	 * 发送POST请求
	 * 
	 * @param user
	 *            用户对象
	 * @param flag
	 *            标志 "l"登录 "r"注册 "o"注销
	 * @return
	 */
	@SuppressWarnings("finally")
	public Result post(UserBean user, String flag) {
		Result result = new Result();
		// 创建HttpClient实例对象
		CloseableHttpClient httpclient = HttpClients.createDefault();
		HttpPost httppost = null;
		// 创建Post请求
		if ("l".equals(flag)) {
			httppost = new HttpPost(Constant.SERVER_ADDR_LOGIN);
		} else if ("r".equals(flag)) {
			httppost = new HttpPost(Constant.SERVER_ADDR_REGISTER);
		} else if ("o".equals(flag)) {
			httppost = new HttpPost(Constant.SERVER_ADDR_LOGOUT);
		}
		// HttpPost httppost = new HttpPost(addr);
		// 创建参数列表
		List<BasicNameValuePair> formparams = new ArrayList<BasicNameValuePair>();
		// 遍历属性列表添加属性
		if (user != null) {
			Map<String, String> infoList = user.getInfoList();
			Set<Entry<String, String>> entrySet = infoList.entrySet();
			for (Entry<String, String> e : entrySet) {
				formparams
						.add(new BasicNameValuePair(e.getKey(), e.getValue()));
			}
		}
		UrlEncodedFormEntity uefEntity;
		try {
			uefEntity = new UrlEncodedFormEntity(formparams, ENCODING);
			// 设置连接配置
			httppost.setEntity(uefEntity);
			System.out.println("executing request " + httppost.getURI());
			// 执行连接操作
			CloseableHttpResponse response = httpclient.execute(httppost);
			getResponse(result, response);
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			// 关闭连接,释放资源
			try {
				httpclient.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			return result;
		}
	}

	/**
	 * 分析处理返回结果
	 * 
	 * @param result
	 * @param response
	 * @throws IOException
	 */
	private void getResponse(Result result, CloseableHttpResponse response)
			throws IOException {
		try {
			HttpEntity entity = response.getEntity();
			if (entity != null) {
				Header[] allHeaders = response.getAllHeaders();
				for (Header h : allHeaders) {
					System.out.println(h.getName() + "  :  " + h.getValue());

					// 设置result.responseCode
					if (h.getName().equals("result")) {
						// if (h.getValue().equals(Constant.RESPONSE_OK)) {
						result.setResponseCode(h.getValue());
						System.out.println(result);
						// }
					}

					// 设置result.user
					if (h.getName().startsWith("#")) {
					}

				}
			}
		} finally {
			response.close();
		}
	}

}
