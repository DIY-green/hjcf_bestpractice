/*
 * Copyright (C) 2016 jiashuangkuaizi, Inc.
 */
package com.huijiachifan.bestpractice.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.huijiachifan.bestpractice.BestPracticeApp;

/**
 * Description: 接入网络工具类
 * <br/>Program Name: 回家吃饭Android开发最佳实践
 * <br/>Date: 2016年2月26日
 * @author  李旺成	liwangcheng@jiashuangkuaizi.com
 * @version  1.0
 */

public class NetUtil {

	static private final String TAG = "HttpUtil";
	
	static public int CMWAP_INT = 1;
	static public int NET_INT = 2;
	static public int WIFI_INT = 3;
	static public int NONET_INT = 4;
	static public int CTWAP_INT = 5;
	
	// 电信3G
	public static String APN_TYPE_CTNET = "ctnet";
	public static String APN_TYPE_CTWAP = "ctwap";
	// 移动接入点
	public static String APN_TYPE_CMNET = "cmnet";
	public static String APN_TYPE_CMWAP = "cmwap";
	// 联通接入点
	public static String APN_TYPE_UNINET = "uninet";
	public static String APN_TYPE_UNIWAP = "uniwap";
	public static String APN_TYPE_3GNET = "3gnet";
	public static String APN_TYPE_3GWAP = "3gwap";// 代理方式
	public static final byte PROXY_TYPE_CM = 0;
	public static final byte PROXY_TYPE_CT = 1;//电信代理
	public static final String PROXY_CT_WAP = "10.0.0.200";
	public static final String PROXY_CM_WAP = "10.0.0.172";
	
	/*
	 * 获取接入网络的方式
	 */
	static public int getNetType (Context ctx) {
		// has network
		ConnectivityManager conn = null;
		try {
			conn = (ConnectivityManager) ctx.getSystemService(Context.CONNECTIVITY_SERVICE);
		} catch (Exception e) {
			Logger.i(TAG, e.getMessage());
		}
		if (conn == null) {
			return NetUtil.NONET_INT;
		}
		NetworkInfo info = conn.getActiveNetworkInfo();
		if(info == null) {
			return NetUtil.NONET_INT;
		}
		boolean available = info.isAvailable();
		if (!available){
			return NetUtil.NONET_INT;
		}
		// check use wifi
		int type = info.getType();
		String extraInfo = info.getExtraInfo();
		if(extraInfo != null) {
			extraInfo = extraInfo.trim();
		}
		if (type == ConnectivityManager.TYPE_WIFI) {
			return NetUtil.WIFI_INT;
		} else if (extraInfo!=null&&APN_TYPE_3GNET.equalsIgnoreCase(extraInfo)
			|| APN_TYPE_UNINET.equalsIgnoreCase(extraInfo)
			|| APN_TYPE_CTNET.equalsIgnoreCase(extraInfo)
			|| APN_TYPE_CMNET.equalsIgnoreCase(extraInfo)) {
				return NetUtil.NET_INT;
		} else {
			String M_APN_PROXY = android.net.Proxy.getDefaultHost();
			if (M_APN_PROXY != null && !"".equals(M_APN_PROXY)) {
				// 判断代理接入方式
				if (PROXY_CT_WAP.equals(M_APN_PROXY)) {//是否电信
					return NetUtil.CTWAP_INT;
				} else {
					return NetUtil.CMWAP_INT;
				}
			} else {//net方式
				return NetUtil.NET_INT;
			}
		}
	}
	
	public static synchronized boolean hasNetWork() {
		return NetUtil.getNetType(BestPracticeApp.getContext()) == NetUtil.NONET_INT ? false : true;
	}
	
}