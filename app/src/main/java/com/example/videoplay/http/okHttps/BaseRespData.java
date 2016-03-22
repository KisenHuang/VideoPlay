package com.example.videoplay.http.okHttps;

import java.io.Serializable;

/**
 * 
 *@Title:接口响应类
 *@Description:
 *@Author:trouble
 *@Since:2015-8-10
 *@Version:1.1.0
 */
public class BaseRespData implements Serializable {

	private static final long serialVersionUID = -3949965775788843028L;

	//当前模块的业务数据
	public String data;
	//文本说明，一般在error的情况下使用
	public String msg;
	//操作代码，200 表示成功
	public String code;
	

}
