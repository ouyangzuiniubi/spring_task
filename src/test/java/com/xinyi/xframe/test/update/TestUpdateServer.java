/**
*	Copyright © 1997 - 2016 Xinyi Tech. All Rights Reserved 
*/
package com.xinyi.xframe.test.update;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.IdentityHashMap;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.xinyi.xframe.dataserver_util.common.HttpHelper;
import com.xinyi.xframe.dataserver_util.common.ResponseContent;

/**
 * 功能说明：
 * 
 * TestPostLogServerData.java
 * 
 * Original Author: liangliangl.jia,2016年3月24日下午5:42:50
 * 
 * Copyright (C)1997-2016 深圳信义科技 All rights reserved.
 */
public class TestUpdateServer {
	
	public static void testUpdateData(List<Object[]> params) throws UnsupportedEncodingException{
		Map<String, Object> paramsMap = new IdentityHashMap<String, Object>();
		System.out.println("start update data");
		String url =  "http://10.235.156.179:8072/execute/updateBulk";
		String storeName ="dbmycat1";
		String sql ="UPDATE JZ_ASJ_B_ASJ_JQ t set t.xyflag='1',t.px=?,t.py=? where t.ajbh=? ";
//		List<Object[]> params = new ArrayList<Object[]>();
//		Object[] A = new Object[]{111,110};
//		Object[] B = new Object[]{122,120};
//		Object[] C = new Object[]{133,130};
//		
//		params.add(A);
//		params.add(B);
//		params.add(C);
		
		paramsMap.put("storeName", storeName);
		paramsMap.put("sql", sql);
		paramsMap.put("params", JSON.toJSONString(params));
		
		ResponseContent ret=null;
		try {
			ret = HttpHelper.postEntity(url, paramsMap);
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println(ret.getContent().toString());
	}
	
	public static void testCountData() throws UnsupportedEncodingException{
		Map<String, Object> paramsMap = new IdentityHashMap<String, Object>();
		System.out.println("start count data");
		String url =  "http://10.235.156.179:8072/execute/countQuery";
		String storeName ="dbmycat1";
		String sql ="SELECT COUNT(1) from JZ_ASJ_B_ASJ_JQ t1 ";
		
		paramsMap.put("storeName", storeName);
		paramsMap.put("sql", sql);
		
		ResponseContent ret=null;
		try {
			ret = HttpHelper.postEntity(url, paramsMap);
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println(ret.getContent().toString());
	}
	
	
	public static List<Map> testSelectData(int index,int pageSize) throws UnsupportedEncodingException{
		int start = index * pageSize ;
		int end = pageSize;
		Map<String, Object> paramsMap = new IdentityHashMap<String, Object>();
		System.out.println("start select data");
		String url =  "http://10.235.156.179:8072/execute/selectQuery";
		String storeName ="dbmycat1";
		String sql ="select t1.ajbh,t1.fadd,t1.px,t1.py,t1.xyflag from JZ_ASJ_B_ASJ_JQ t1  order by t1.ajbh asc limit "+start+","+end;
		
		paramsMap.put("storeName", storeName);
		paramsMap.put("sql", sql);
		
		ResponseContent ret=null;
		try {
			ret = HttpHelper.postEntity(url, paramsMap);
		} catch (Exception e) {
			e.printStackTrace();
		}
		List<Map> list = JSON.parseArray(ret.getContent().toString(), Map.class);
		System.out.println(list.toString());
		return list;
	}

	public static void main(String[] args) throws UnsupportedEncodingException {
//		testUpdateData();
		 testCountData();
		 testSelectData(0,10);
	}
}
