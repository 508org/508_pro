package com.tianyang.modules.sys.utils;


import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;














import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Maps;




public class WeixinUntil {
	
	public static long getTime;
	
	public static long getGongTime;
//	public static String sToken = "tianyangxinxikeji";
//	public static String sCorpID = "wx0c20e33440d3a1d4";
//	public static String sEncodingAESKey = "b6ZOyZ43vZw3IoNhyhVZZx4kU2vZyQltfG5fIpYbzGX";
//	public static String sCorpSecret = "RY1nsHS7FEfArX_qeiIA4FNKzo6SY-DQjvb6a0t7iRb4SXv7qP8yYvFmP3vo1L_-";
	//企业号的accesstoken
	public static String access_token;
	//公众号的accesstoken
	public static String gong_access_token;
	
	//这个用来判断此次传进的corpid是否和上次的一样，如果不一样，access_token需要重新获取，不然在有效期内一直是获取的上一个的信息
	public static String currentCorpid;
	
	//公众号的appid记录
	//这个用来判断此次传进的corpid是否和上次的一样，如果不一样，access_token需要重新获取，不然在有效期内一直是获取的上一个的信息
	public static String currentAppid;
	
	public static Map sCorpSecretmap = Maps.newHashMap();
	
	
	public static String post(String url, String json){

		String result = "";
		System.out.println(url);
		System.out.println(json);
		CloseableHttpClient httpclient = HttpClients.createDefault();
		

		HttpPost post = new HttpPost(url);
		try{
			if(json!=null && !json.equals("")){
				StringEntity s = new StringEntity(json, "UTF-8");
				s.setContentEncoding("utf-8");
				s.setContentType("application/json");
				post.setEntity(s);				
			}
			post.setHeader("Content-Type", "application/json; charset=utf-8");
			HttpResponse res = httpclient.execute(post);
			Header[] headers = (Header[]) res.getAllHeaders();
			for (Header header : headers) {
				System.out.println(header.getName() + "|||" + header.getValue());
			}
			if (res.getStatusLine().getStatusCode() == HttpStatus.SC_OK)
			{
				HttpEntity entity = res.getEntity();
			    result = EntityUtils.toString(entity, "utf-8");
				System.out.println(result);
				
			}
			httpclient.close();			
		}catch (Exception e){
			throw new RuntimeException(e);
		}		
		return result;
	}
	
	
	public static String getAccessToken(String sCorpID,String sCorpSecret){
		String url = "https://qyapi.weixin.qq.com/cgi-bin/gettoken?corpid="
				+ sCorpID + "&corpsecret=" + sCorpSecret;
		CloseableHttpClient httpclient = HttpClients.createDefault();
		HttpPost post = new HttpPost(url);
		try
		{						
			if (sCorpID.equals(currentCorpid) && null != sCorpSecretmap.get(sCorpSecret)) {// 已经获取了access_token
				long currentTime = System.currentTimeMillis();
				if ((currentTime - getTime) < 7200000) {// 过期了 |														// access_token有效期为7200秒
					System.out.println(sCorpSecret+"未过期" + sCorpSecretmap.get(sCorpSecret));
					return sCorpSecretmap.get(sCorpSecret)+"";
				}
			}
			currentCorpid=sCorpID;
			HttpResponse res = httpclient.execute(post);
			HttpEntity entity = res.getEntity();
			String jsonString = EntityUtils.toString(entity, "utf-8");
			JSONObject obj = JSON.parseObject(jsonString);  
			
			
			getTime = System.currentTimeMillis();
			access_token = obj.getString("access_token");
			System.out.println(sCorpSecret+"已过期" + sCorpSecretmap.get(sCorpSecret));
			
			//独立存储sCorpSecret值和与之对应的access_token
			sCorpSecretmap.put(sCorpSecret, access_token);
			//end
			
			return access_token;
		}
		catch (Exception e)
		{
			throw new RuntimeException(e);
		}
	}
	
	
	public static String getGongAccessToken(String appid,String appsecret){
		String url = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid="+appid+"&secret=" + appsecret;
		CloseableHttpClient httpclient = HttpClients.createDefault();
		HttpPost post = new HttpPost(url);
		try
		{						
			if (appid.equals(currentAppid) && null != gong_access_token) {// 已经获取了access_token
				long currentTime = System.currentTimeMillis();
				if ((currentTime - getGongTime) < 7200000) {// 过期了 |														// access_token有效期为7200秒
					System.out.println("未过期" + gong_access_token);
					return gong_access_token;
				}
			}
			currentAppid  = appid;
			HttpResponse res = httpclient.execute(post);
			HttpEntity entity = res.getEntity();
			String jsonString = EntityUtils.toString(entity, "utf-8");
			JSONObject obj = JSON.parseObject(jsonString);
			getGongTime = System.currentTimeMillis();
			gong_access_token = obj.getString("access_token");
			System.out.println("已过期" + gong_access_token);
			return gong_access_token;
		}
		catch (Exception e)
		{
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * 去除字符串前面的0
	 */
	public static String deleteZero(String str){
		int len = str.length();//取得字符串的长度
		int index = 0;//预定义第一个非零字符串的位置

		char strs[] = str.toCharArray();// 将字符串转化成字符数组
			for(int i=0; i<len; i++){
			if('0'!=strs[i]){
			index=i;// 找到非零字符串并跳出
			break;
			}
		}
		String strLast = str.substring(index, len);// 截取字符串
		return strLast;
	}
	
	/**
	 * 生成创建部门完成后将部门信息更新到微信上所要上传的json格式的数据
	 * @param name 部门名称。长度限制为1~64个字节，字符不能包括\:*?"<>｜ 
	 * @param parentid 父亲部门id。根部门id为1 
	 * @param order 在父部门中的次序值。order值小的排序靠前。 
	 * @param id 部门id，整型。指定时必须大于1，不指定时则自动生成 
	 * @return 生成的json字符串
	 */
	public static String getCreateorganizationJson(String name,String parentid,String order,String id){
		String strJson = "{\"name\" :\"" + name + "\",";
		strJson += "\"parentid\":\""+parentid+"\",";
		strJson += "\"order\":\"" +order+ "\",";
		strJson += "\"id\":\"" +id+ "\"";
		strJson += "}}";		
		
		return strJson;
	}
	
	/**
	 * 微信登陆后获取登录人的微信id
	 * @param code 通过成员授权获取到的code，每次成员授权带上的code将不一样，code只能使用一次，5分钟未被使用自动过期 
	 * @param session 将用户保存的session
	 * @return userId：用户的登录名，erro：非企业成员或者出错
	 * 企业成员授权时返回示例如下:{
		   "UserId":"USERID",
		   "DeviceId":"DEVICEID"
		}
		非企业成员授权时返回示例如下：{
		   "OpenId":"OPENID",
		   "DeviceId":"DEVICEID"
		}
		出错时返回示例如下：{
		   "errcode": "40029",
		   "errmsg": "invalid code"
		}
	 */
	public static String getWeiXinLongInfo(String code,HttpSession session,String sCorpID,String sCorpSecret){
		String userId = "";
		if(code!=null && !code.equals("")){
//			String sCorpID = session.getAttribute("sCorpID").toString();
//			String sCorpSecret =session.getAttribute("sCorpSecret").toString();
			String url = "https://qyapi.weixin.qq.com/cgi-bin/user/getuserinfo?access_token="+getAccessToken(sCorpID, sCorpSecret)+"&code="+code+"";
			String resultJson  = post(url,null); 
			System.out.println("https://qyapi.weixin.qq.com/cgi-bin/user/getuserinfo返回值:"+resultJson);
			JSONObject jsonObject = new JSONObject();
			JSONObject result = jsonObject.parseObject(resultJson);
			if(resultJson.contains("UserId")){//企业成员返回成功
				userId = result.get("UserId").toString();
				session.setAttribute("UserId", userId);
			}else {//非企业成员或者出错
				userId = "erro";
			}
		}
		return userId;	
	}
	
	
	/**第二步：通过code换取网页授权access_token
	首先请注意，这里通过code换取的是一个特殊的网页授权access_token,与基础支持中的access_token（该access_token用于调用其他接口）不同。公众号可通过下述接口来获取网页授权access_token。如果网页授权的作用域为snsapi_base，则本步骤中获取到网页授权access_token的同时，也获取到了openid，snsapi_base式的网页授权流程即到此为止。
	尤其注意：由于公众号的secret和获取到的access_token安全级别都非常高，必须只保存在服务器，不允许传给客户端。后续刷新access_token、通过access_token获取用户信息等步骤，也必须从服务器发起。
	请求方法
	获取code后，请求以下链接获取access_token：  https://api.weixin.qq.com/sns/oauth2/access_token?appid=APPID&secret=SECRET&code=CODE&grant_type=authorization_code 
	参数说明
	参数	是否必须	说明
	appid	是	公众号的唯一标识
	secret	是	公众号的appsecret
	code	是	填写第一步获取的code参数
	grant_type	是	填写为authorization_code   
	返回说明
	正确时返回的JSON数据包如下：
	{ "access_token":"ACCESS_TOKEN",    
	 "expires_in":7200,    
	 "refresh_token":"REFRESH_TOKEN",    
	 "openid":"OPENID",    
	 "scope":"SCOPE" } 
	参数	描述
	access_token	网页授权接口调用凭证,注意：此access_token与基础支持的access_token不同
	expires_in	access_token接口调用凭证超时时间，单位（秒）
	refresh_token	用户刷新access_token
	openid	用户唯一标识，请注意，在未关注公众号时，用户访问公众号的网页，也会产生一个用户和公众号唯一的OpenID
	scope	用户授权的作用域，使用逗号（,）分隔
	错误时微信会返回JSON数据包如下（示例为Code无效错误）:
	{"errcode":40029,"errmsg":"invalid code"} */
	public static String getGongWeiXinLongInfo(String code,HttpSession session,String sCorpID,String sCorpSecret){
		
		String openid = "";
		if(code!=null && !code.equals("")){
//			String sCorpID = session.getAttribute("sCorpID").toString();
//			String sCorpSecret =session.getAttribute("sCorpSecret").toString();
			//https://api.weixin.qq.com/sns/oauth2/access_token?appid=APPID&secret=SECRET&code=CODE&grant_type=authorization_code 
			String url = "https://api.weixin.qq.com/sns/oauth2/access_token?appid="+sCorpID+"&secret="+sCorpSecret+"&code="+code+"&grant_type=authorization_code";
			
		String resultJson  = post(url,null); 
			JSONObject jsonObject = new JSONObject();
			JSONObject result = jsonObject.parseObject(resultJson);
			if(resultJson.contains("openid")){//如果正常
				openid = result.get("openid").toString();
				//session.setAttribute("UserId", openid);
			}else {//非企业成员或者出错
				openid = result.get("errmsg").toString();
			}
		}
		return openid;	
		
		
	}
	
	 
	
	/**
	 * 发送消息方法
	 * @param jsonInfo 消息封装的json字符串
	 * @param sCorpID 微信的sCorpID
	 * @param sCorpSecret 微信的sCorpSecret
	 * @return 发送是否成功
	 */
	public static boolean sendInformation(String jsonInfo,String sCorpID,String sCorpSecret){
		boolean isSuccess = false;
		String accessToken = getAccessToken(sCorpID, sCorpSecret);
		String url = "https://qyapi.weixin.qq.com/cgi-bin/message/send?access_token="+accessToken; 
		String resultJson = post(url,jsonInfo);
		JSONObject jsonObject = new JSONObject();
		JSONObject result = jsonObject.parseObject(resultJson);
		String errmsg = result.getString("errmsg");
		if(errmsg.equals("ok")){
			isSuccess = true;
		}
		return isSuccess;
	}	
	
	/**
	 * 获取包装后的url
	 * @param url
	 * @return 
	 */
	public static String getOnclickLink(String url,String sCorpID){
		String menuUrl;
		try {
			menuUrl = "https://open.weixin.qq.com/connect/oauth2/authorize?appid="+sCorpID+"&redirect_uri="+URLEncoder.encode(url,"UTF-8")+"&response_type=code&scope=snsapi_base#wechat_redirect";
		} catch (UnsupportedEncodingException e) {
			menuUrl = "error";
			e.printStackTrace();
		} 		
		return menuUrl;	
	}
	
	
	/**
	 * 获取域名加应用链接路径
	 * @param url
	 * @return 
	 */
	public static String getContextPath(HttpServletRequest request){
		
		String basePath = request.getScheme()+"://";
		String serverName = request.getServerName();
		//如果serverName不包含www，则添加上
		if(serverName.indexOf("www") < 0) {
			serverName = "www." + serverName;
		}
		basePath += serverName;
		
		//80端口无需拼接
		if(!"80".equals(String.valueOf(request.getServerPort()))) {
			basePath += ":"+request.getServerPort();
		}
		
		basePath += request.getContextPath();
		return basePath;	
	}
	
	/**
	 * 获取域名加应用链接路径
	 * @param url
	 * @return 
	 */
	public static String getContextPathNotAddWWW(HttpServletRequest request){
		
		String basePath = request.getScheme()+"://";
		String serverName = request.getServerName();
		/*//如果serverName不包含www，则添加上
		if(serverName.indexOf("www") < 0) {
			serverName = "www." + serverName;
		}*/
		basePath += serverName;
		
		//80端口无需拼接
		if(!"80".equals(String.valueOf(request.getServerPort()))) {
			basePath += ":"+request.getServerPort();
		}
		
		basePath += request.getContextPath();
		return basePath;	
	}
	

	/**
	 * 
	 *方法描述：
	 *@author: blank
	 *@date： 日期：2016年1月7日 时间：下午3:08:33
	 *@param users 发送用户 user1|user2|user3
	 *@param url 消息链接
	 *@param message 消息体
	 *@param agentid 应用ID
	 *@param sCorpID 企业号sCorpID
	 *@param sCorpSecret 企业号sCorpSecret
	 *@return
	 *@version 1.0
	 */
	public static boolean sendTextMessageToAdmin(String users,String url, String message,String agentid,String sCorpID,String sCorpSecret) {	
	        	TextMessage textMessage = new TextMessage();
		        Content content = new Content();		
		        textMessage.setTouser(users);
		        textMessage.setAgentid(Integer.valueOf(agentid));
		        textMessage.setMsgtype("text");
		        if(!"".equals(url)) {
		        	content.setContent("<a href='"+url+"'> "+message+"</a>");
		        } else {
		        	content.setContent(message);
		        }
		        textMessage.setText(content);		       
		       return WeixinUntil.sendInformation( JSON.toJSONString(textMessage), sCorpID, sCorpSecret);
	        
	}
	
    public static String long2Short(String strurl,String sCorpID,String sCorpSecret){  
    	
    		String accessToken = getGongAccessToken(sCorpID,sCorpSecret);
	       // boolean result = false;  
	        String requestUrl = "https://api.weixin.qq.com/cgi-bin/shorturl?access_token=ACCESS_TOKEN"; 
	        String jsonMsg = "{\"action\":\"long2short\",\"long_url\":\"%s\"}";
	        requestUrl = requestUrl.replace("ACCESS_TOKEN", accessToken);  
	        
	        String shortUrl = "";
			String resultJson  = post(requestUrl,String.format(jsonMsg, strurl)); 
			JSONObject jsonObject = new JSONObject();
			JSONObject result = jsonObject.parseObject(resultJson);
			if(resultJson.contains("short_url")){//如果正常
				shortUrl = result.get("short_url").toString();
				//session.setAttribute("UserId", openid);
			}else {//非企业成员或者出错
				shortUrl = result.get("errmsg").toString();
			} 
	        return shortUrl;  
	    }
	
	/**
	 * 
	 *方法描述：获取WebApp根目录
	 *@author: blank
	 *@date： 日期：2016年1月12日 时间：下午5:54:49
	 *@return
	 *@version 1.0
	 */
	public static String getWebAppPath(HttpServletRequest request) {			
		return new File(request.getSession().getServletContext().getRealPath("/")).getParent();	        
	}
	
	/**
	 * 
	 *方法描述：获取文件上传文件路径，相对路径，用于保存入数据库/files/file
	 *@author: blank
	 *@date： 日期：2016年1月12日 时间：下午6:17:28
	 *@return
	 *@version 1.0
	 */
	public static String getFilePath() {			
		return "/files/file";	        
	}
	
	/**
	 * 
	 *方法描述：获取微信图片上传路径，相对路径，用于拼接保存入数据库 /files/img
	 *@author: blank
	 *@date： 日期：2016年1月12日 时间：下午6:18:35
	 *@return
	 *@version 1.0
	 */
	public static String getWeixinImgPath() {			
		return "/files/img";	        
	}
	
	
	private final static String ENCODE = "UTF-8"; 
    /**
     * URL 解码
     *
     * @return String
     * @author lifq
     * @date 2015-3-17 下午04:09:51
     */
    public static String getURLDecoderString(String str) {
        String result = "";
        if (null == str) {
            return "";
        }
        try {
            result = java.net.URLDecoder.decode(str, ENCODE);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return result;
    }
    /**
     * URL 转码
     *
     * @return String
     * @author lifq
     * @date 2015-3-17 下午04:10:28
     */
    public static String getURLEncoderString(String str) {
        String result = "";
        if (null == str) {
            return "";
        }
        try {
            result = java.net.URLEncoder.encode(str, ENCODE);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return result;
    }
	
	/**
	 * 获取微信返回的错误信息
	 * @param errcode
	 * @return
	 */
	public static String getErrorInfo(Integer errcode){
		Map<Integer,String> map = new HashMap<Integer, String>();
		
		map.put(-1,"系统繁忙 ");
		map.put(0,"请求成功  ");
		map.put(40001,"获取access_token时Secret错误，或者access_token无效 ");
		map.put(40002,"不合法的凭证类型");
		map.put(40003,"不合法的UserID");
		map.put(40004,"不合法的媒体文件类型");
		map.put(40005,"不合法的文件类型");
		map.put(40006,"不合法的文件大小");
		map.put(40007,"不合法的媒体文件id");
		map.put(40008,"不合法的消息类型 ");
		map.put(40013,"不合法的corpid");
		map.put(40014,"不合法的access_token");
		map.put(40015,"不合法的菜单类型");
		map.put(40016,"不合法的按钮个数 ");
		map.put(40017,"不合法的按钮类型");
		map.put(40018,"不合法的按钮名字长度");
		map.put(40019,"不合法的按钮KEY长度");
		map.put(40020,"不合法的按钮URL长度");
		map.put(40021,"不合法的菜单版本号 ");
		map.put(40022,"不合法的子菜单级数 ");
		map.put(40023,"不合法的子菜单按钮个数 ");
		map.put(40024,"不合法的子菜单按钮类型 ");
		map.put(40025,"不合法的子菜单按钮名字长度");
		map.put(40026,"不合法的子菜单按钮KEY长度");
		map.put(40027,"不合法的子菜单按钮URL长度 ");
		map.put(40028,"不合法的自定义菜单使用成员");
		map.put(40029,"不合法的oauth_code");
		map.put(40031,"不合法的UserID列表 ");
		map.put(40032,"不合法的UserID列表长度");
		map.put(40033,"不合法的请求字符，不能包含\\uxxxx格式的字符 ");
		map.put(40035,"不合法的参数");
		map.put(40038,"不合法的请求格式 ");
		map.put(40039,"不合法的URL长度");
		map.put(40040,"不合法的插件token");
		map.put(40041,"不合法的插件id");
		map.put(40042,"不合法的插件会话");
		map.put(40048,"url中包含不合法domain");
		map.put(40054,"不合法的子菜单url域名");
		map.put(40055,"不合法的按钮url域名");
		map.put(40056,"不合法的agentid");
		map.put(40057,"不合法的callbackurl或者callbackurl验证失败");
		map.put(40058,"不合法的红包参数");
		map.put(40059,"不合法的上报地理位置标志位");
		map.put(40060,"设置上报地理位置标志位时没有设置callbackurl");
		map.put(40061,"设置应用头像失败 ");
		map.put(40062,"不合法的应用模式");
		map.put(40063,"参数为空 ");
		map.put(40064,"管理组名字已存在");
		map.put(40065,"不合法的管理组名字长度");
		map.put(40066,"不合法的部门列表");
		map.put(40067,"标题长度不合法");
		map.put(40068,"不合法的标签ID");
		map.put(40069,"不合法的标签ID列表");
		map.put(40070,"列表中所有标签（成员）ID都不合法 ");
		map.put(40071,"不合法的标签名字，标签名字已经存在");
		map.put(40072,"不合法的标签名字长度");
		map.put(40073,"不合法的openid ");
		map.put(40074,"news消息不支持指定为高保密消息");
		map.put(40077,"不合法的预授权码 ");
		map.put(40078,"不合法的临时授权码 ");
		map.put(40079,"不合法的授权信息");
		map.put(40080,"不合法的suitesecret");
		map.put(40082,"不合法的suitetoken");
		map.put(40083,"不合法的suiteid");
		map.put(40084,"不合法的永久授权码");
		map.put(40085,"不合法的suiteticket ");
		map.put(40086,"不合法的第三方应用appid ");
		map.put(40092,"导入文件存在不合法的内容");
		map.put(40093,"不合法的跳转target");
		map.put(40094,"不合法的URL");
		map.put(41001,"缺少access_token参数");
		map.put(41002,"缺少corpid参数 ");
		map.put(41003,"缺少refresh_token参数");
		map.put(41004,"缺少secret参数 ");
		map.put(41005,"缺少多媒体文件数据");
		map.put(41006,"缺少media_id参数 ");
		map.put(41007,"缺少子菜单数据 ");
		map.put(41008,"缺少oauth code ");
		map.put(41009,"缺少UserID ");
		map.put(41010,"缺少url");
		map.put(41011,"缺少agentid");
		map.put(41012,"缺少应用头像mediaid");
		map.put(41013,"缺少应用名字");
		map.put(41014,"缺少应用描述");
		map.put(41015,"缺少Content");
		map.put(41016,"缺少标题");
		map.put(41017,"缺少标签ID ");
		map.put(41018,"缺少标签名字");
		map.put(41021,"缺少suiteid");
		map.put(41022,"缺少suitetoken ");
		map.put(41023,"缺少suiteticket");
		map.put(41024,"缺少suitesecret");
		map.put(41025,"缺少永久授权码");
		map.put(41034,"缺少login_ticket");
		map.put(41035,"缺少跳转target ");
		map.put(42001,"access_token超时");
		map.put(42002,"refresh_token超时");
		map.put(42003,"oauth_code超时 ");
		map.put(42004,"插件token超时");
		map.put(42007,"预授权码失效");
		map.put(42008,"临时授权码失效");
		map.put(42009,"suitetoken失效 ");
		map.put(43001,"需要GET请求");
		map.put(43002,"需要POST请求");
		map.put(43003,"需要HTTPS");
		map.put(43004,"需要成员已关注");
		map.put(43005,"需要好友关系");
		map.put(43006,"需要订阅");
		map.put(43007,"需要授权");
		map.put(43008,"需要支付授权");
		map.put(43010,"需要处于回调模式");
		map.put(43011,"需要企业授权");
		map.put(43013,"应用对成员不可见");
		map.put(44001,"多媒体文件为空");
		map.put(44002,"POST的数据包为空");
		map.put(44003,"图文消息内容为空");
		map.put(44004,"文本消息内容为空");
		map.put(45001,"多媒体文件大小超过限制");
		map.put(45002,"消息内容超过限制");
		map.put(45003,"标题字段超过限制");
		map.put(45004,"描述字段超过限制");
		map.put(45005,"链接字段超过限制");
		map.put(45006,"图片链接字段超过限制");
		map.put(45007,"语音播放时间超过限制");
		map.put(45008,"图文消息的文章数量不能超过10条");
		map.put(45009,"接口调用超过限制 ");
		map.put(45010,"创建菜单个数超过限制 ");
		map.put(45015,"回复时间超过限制");
		map.put(45016,"系统分组，不允许修改");
		map.put(45017,"分组名字过长");
		map.put(45018,"分组数量超过上限");
		map.put(45022,"应用名字长度不合法，合法长度为2-16个字");
		map.put(45024,"账号数量超过上限");
		map.put(45025,"同一个成员每周只能邀请一次");
		map.put(45026,"触发删除用户数的保护 ");
		map.put(45027,"mpnews每天只能发送100次 ");
		map.put(45028,"素材数量超过上限 ");
		map.put(45029,"media_id对该应用不可见 ");
		map.put(46001,"不存在媒体数据 ");
		map.put(46002,"不存在的菜单版本");
		map.put(46003,"不存在的菜单数据");
		map.put(46004,"不存在的成员");
		map.put(47001,"解析JSON/XML内容错误 ");
		map.put(48001,"Api未授权 ");
		map.put(48002,"Api禁用 ");
		map.put(48003,"suitetoken无效 ");
		map.put(48004,"授权关系无效 ");
		map.put(50001,"redirect_uri未授权 ");
		map.put(50002,"成员不在权限范围 ");
		map.put(50003,"应用已停用 ");
		map.put(50004,"成员状态不正确，需要成员为企业验证中状态");
		map.put(50005,"企业已禁用");
		map.put(60001,"部门长度不符合限制 ");
		map.put(60002,"部门层级深度超过限制 ");
		map.put(60003,"部门不存在 ");
		map.put(60004,"父亲部门不存在 ");
		map.put(60005,"不允许删除有成员的部门");
		map.put(60006,"不允许删除有子部门的部门 ");
		map.put(60007,"不允许删除根部门 ");
		map.put(60008,"部门名称已存在");
		map.put(60009,"部门名称含有非法字符 ");
		map.put(60010,"部门存在循环关系 ");
		map.put(60011,"管理组权限不足，（user/department/agent）无权限 ");
		map.put(60012,"不允许删除默认应用 ");
		map.put(60013,"不允许关闭应用");
		map.put(60014,"不允许开启应用 ");
		map.put(60015,"不允许修改默认应用可见范围");
		map.put(60016,"不允许删除存在成员的标签");
		map.put(60017,"不允许设置企业 ");
		map.put(60019,"不允许设置应用地理位置上报开关");
		map.put(60020,"访问ip不在白名单之中 ");
		map.put(60025,"主页型应用不支持的消息类型 ");
		map.put(60027,"主页型应用不支持调用该接口 ");
		map.put(60028,"应用已授权予第三方，不允许通过分级管理员主页url ");
		map.put(60029,"应用已授权予第三方，不允许通过分级管理员修改可信域名 ");
		map.put(60102,"UserID已存在");
		map.put(60103,"手机号码不合法 ");
		map.put(60104,"手机号码已存在");
		map.put(60105,"邮箱不合法");
		map.put(60106,"邮箱已存在 ");
		map.put(60107,"微信号不合法 ");
		map.put(60108,"微信号已存在 ");
		map.put(60109,"QQ号已存在");
		map.put(60110,"用户同时归属部门超过20个");
		map.put(60111,"UserID不存在");
		map.put(60112,"成员姓名不合法");
		map.put(60113,"身份认证信息（微信号/手机/邮箱）不能同时为空 ");
		map.put(60114,"性别不合法");
		map.put(60115,"已关注成员微信不能修改 ");
		map.put(60116,"扩展属性已存在");
		map.put(60118,"成员无有效邀请字段，详情参考(邀请成员关注)的接口说明");
		map.put(60119,"成员已关注 ");
		map.put(60120,"成员已禁用 ");
		map.put(60121,"找不到该成员");
		map.put(60122,"邮箱已被外部管理员使用 ");
		map.put(60123,"无效的部门id ");
		map.put(60124,"无效的父部门id ");
		map.put(60125,"非法部门名字，长度超过限制、重名等 ");
		map.put(60126,"创建部门失败 ");
		map.put(60127,"缺少部门id ");
		map.put(60128,"字段不合法，可能存在主键冲突或者格式错误 ");
		map.put(80001,"可信域名没有IPC备案，后续将不能在该域名下正常使用jssdk");
		map.put(82001,"发送消息或者邀请的参数全部为空或者全部不合法 ");
		map.put(82002,"不合法的PartyID列表长度");
		map.put(82003,"不合法的TagID列表长度 ");
		map.put(82004,"微信版本号过低 ");
		map.put(85002,"包含不合法的词语");
		map.put(86001,"不合法的会话ID ");
		map.put(86003,"不存在的会话ID ");
		map.put(86004,"不合法的会话名 ");
		map.put(86005,"不合法的会话管理员");
		map.put(86006,"不合法的成员列表大小 ");
		map.put(86007,"不存在的成员");
		map.put(86101,"需要会话管理员权限");
		map.put(86201,"缺少会话ID");
		map.put(86202,"缺少会话名 ");
		map.put(86203,"缺少会话管理员");
		map.put(86204,"缺少成员");
		map.put(86205,"非法的会话ID长度 ");
		map.put(86206,"非法的会话ID数值");
		map.put(86207,"会话管理员不在用户列表中");
		map.put(86208,"消息服务未开启 ");
		map.put(86209,"缺少操作者");
		map.put(86210,"缺少会话参数 ");
		map.put(86211,"缺少会话类型（单聊或者群聊)");
		map.put(86213,"缺少发件人");
		map.put(86214,"非法的会话类型 ");
		map.put(86215,"会话已存在 ");
		map.put(86216,"非法会话成员");
		map.put(86217,"会话操作者不在成员列表中");
		map.put(86218,"非法会话发件人 ");
		map.put(86219,"非法会话收件人 ");
		map.put(86220,"非法会话操作者 ");
		map.put(86221,"单聊模式下，发件人与收件人不能为同一人 ");
		map.put(86222,"不允许消息服务访问的API ");
		map.put(86304,"不合法的消息类型 ");
		map.put(86305,"客服服务未启用");
		map.put(86306,"缺少发送人id");
		map.put(86307,"缺少发送人类型");
		map.put(86308,"缺少发送人id");
		map.put(86309,"缺少接收人");
		map.put(86310,"86310");
		map.put(86311,"缺少接收人id");
		map.put(86312,"缺少消息类型");
		map.put(86313,"缺少客服，发送人或接收人类型，必须有一个为kf ");
		map.put(86314,"客服不唯一，发送人或接收人类型，必须只有一个为kf ");
		map.put(86315,"不合法的发送人类型 ");
		map.put(86316,"不合法的发送人id。Userid不存在、openid不存在、kf不存在 ");
		map.put(86317,"不合法的接收人类型 ");
		map.put(86318,"不合法的接收人id。Userid不存在、openid不存在、kf不存在 ");
		map.put(86319,"不合法的客服，kf不在客服列表中 ");
		map.put(86320,"不合法的客服类型 ");
		map.put(90001,"未认证摇一摇周边 ");
		map.put(90002,"缺少摇一摇周边ticket参数 ");
		map.put(90003,"摇一摇周边ticket参数不合法 ");
		map.put(90004,"摇一摇周边ticket过期 ");
		map.put(90005,"未开启摇一摇周边服务 ");
		
		String errorInfo = map.get(errcode);
		if(errorInfo==null || errorInfo.equals("")){
			errorInfo = "未获取到错误提示";
		}
		return errorInfo;
	}

}
