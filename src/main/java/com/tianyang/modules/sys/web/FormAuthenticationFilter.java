/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package  com.tianyang.modules.sys.web;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.util.WebUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.common.collect.Maps;
import com.tianyang.modules.sys.utils.AesException;
import com.tianyang.modules.sys.utils.UserUtils;
import com.tianyang.modules.sys.utils.WXBizMsgCrypt;
import com.tianyang.common.config.Global;
import com.tianyang.common.mapper.JsonMapper;
import com.tianyang.common.persistence.Page;
import com.tianyang.common.utils.SpringContextHolder;
import com.tianyang.common.utils.StringUtils;
import com.tianyang.modules.sys.dao.UserDao;
import com.tianyang.modules.sys.entity.User;
import com.tianyang.modules.sys.security.UsernamePasswordToken;
import com.tianyang.modules.sys.utils.WeixinUntil;


/**
 * 微信和手机端处理类
 * @author ThinkGem
 * @version 2014-5-19
 */

@Controller
@RequestMapping(value = "${frontPath}/weixin")
public class FormAuthenticationFilter extends org.apache.shiro.web.filter.authc.FormAuthenticationFilter {

	public static final String DEFAULT_CAPTCHA_PARAM = "validateCode";
	public static final String DEFAULT_MOBILE_PARAM = "mobileLogin";
	public static final String DEFAULT_MESSAGE_PARAM = "message";

	private String captchaParam = DEFAULT_CAPTCHA_PARAM;
	private String mobileLoginParam = DEFAULT_MOBILE_PARAM;
	private String messageParam = DEFAULT_MESSAGE_PARAM;
	
	
	private String sToken          = Global.sTOKEN;
	private String sEncodingAESKey = Global.sENCODINGAESKEY;
	private String sCorpID         = Global.sCORPID;
	
	private String agentid         = Global.AGENTID;
	private String sCorpSecret     = Global.sCORPSECRET_sjgl;
	
	
	
	private static UserDao userDao = SpringContextHolder.getBean(UserDao.class);
	
	@RequestMapping(value = "login",method = RequestMethod.GET)
	
	protected String login(HttpServletRequest request, HttpServletResponse response) {
		

		String userCode = request.getParameter("code");
		String flag = request.getParameter("flag");
		flag=flag==null?"":flag;
		if (userCode != null && !"".equals(userCode)) {  
	        
			Map<String,String> map=new HashMap<String, String>();
			map.put("success", "false");
			
			String loginName = WeixinUntil.getWeiXinLongInfo(userCode, request.getSession(), sCorpID, sCorpSecret);
			//User user = meetingDataService.getByMobile(loginName);//微信是通过手机号来查的			
			User user=UserUtils.getByMobile(loginName);
			if(null != user)
			{
				
				String username  = user.getLoginName();
				String password  = user.getPassword();
				
				boolean rememberMe = isRememberMe(request);
				String host = StringUtils.getRemoteAddr((HttpServletRequest)request);
				String captcha = getCaptcha(request);
				boolean mobile = isMobileLogin(request);
				if(!StringUtils.equals(UserUtils.getUser().getId(), user.getId()))
				{
					Subject currentUser = SecurityUtils.getSubject();
					UsernamePasswordToken token=new UsernamePasswordToken(username, password.toCharArray(), rememberMe, host, captcha, mobile,true);
				    currentUser.login(token);
					
				}	
				
				map.put("success", "true");
				
			}	
			
		      JsonMapper.getInstance().toJson(map);
		      
		}
		if(flag.equals("1"))
		{
			return "redirect:"+Global.getAdminPath()+"/pc/pcGroup/getList?repage";
		}else
		{
			return "redirect:"+Global.getAdminPath()+"/pc/PcClusteringMarket/getList?repage";
		}
		//return "modules/marketactivitesmanage/marketingActivitiesList";
		
	}
	/**
	 * 验证微信端
	 * @param request
	 * @param response
	 * @return
	 * @throws AesException 
	 * @throws IOException 
	 */
	
	@RequestMapping(value = "execute",method = RequestMethod.GET)
	@ResponseBody
	protected String execute(ServletRequest request, ServletResponse response) throws AesException, IOException {
		
		
		
		// 微信加密签名
		String signature = request.getParameter("msg_signature");
		
		// 时间戳
		String timestamp = request.getParameter("timestamp");
		
		// 随机数
		String nonce = request.getParameter("nonce");
		
		// 随机字符串
		String echostr = request.getParameter("echostr");
		
		WXBizMsgCrypt wxcpt;		
		wxcpt = new WXBizMsgCrypt(sToken, sEncodingAESKey, sCorpID);
		
		String sEchoStr = ""; // 需要返回的明文
		// 是否为初次验证
		if (echostr != null && echostr.length() > 1) {
			
			sEchoStr = wxcpt.VerifyURL(signature, timestamp, nonce, echostr);
			PrintWriter out = response.getWriter();
			out.print(sEchoStr);
			out.close();
			out = null;
		}	
	      return null;
	}
	
	/**
	 *  将菜单信息同步到微信端
	 *  zbc814.oicp.net  hy.jnty.com.cn
	 */
	
	@RequestMapping(value = "menuWeixin",method = RequestMethod.GET)
	@ResponseBody
	protected String menuWeixin(ServletRequest request, ServletResponse response) throws AesException, IOException {
		
		
		String url          = "https://qyapi.weixin.qq.com/cgi-bin/menu/create?access_token="+WeixinUntil.getAccessToken(sCorpID,sCorpSecret)+"&agentid="+agentid+"";
		//先把跳转的url 转下码，并在拼接包在微信的url下
		String myMeetingUrl =getMenuUrl(URLEncoder.encode(Global.wEIXINURL+"/yxzs/f/weixin/login"       ,"UTF-8"),sCorpID);
		String meetingUrl   =getMenuUrl(URLEncoder.encode(Global.wEIXINURL+"/yxzs","UTF-8"),sCorpID);
		String menu = ""+
		"{"+
			"\"button\":["+
			      " {"	+
			      "\"type\":\"view\",  "+
			      "\"name\":\"我的活动\","+
			      "\"url\":\""+myMeetingUrl+"\""+
			      " }"
//			      +","+
//			      " {"+
//			      "	   \"type\":\"view\",   "+
//			      "	   \"name\":\"微信登陆\", "+
//			      "	   \"url\":\""+meetingUrl+"\""+
//			           
//			      "}"+
			  + "]"+
			"}";
		String resultJson = WeixinUntil.post(url, menu);
		//判断是否同步成功{"errcode":0,"errmsg":"ok"}
		return resultJson;
	}
	
	/**
	 * 手机端我的会议列表/呈报消息列表 
	 * fromPage 通过从哪个页面传来的参数来决定回显json数据
	 * @param oaNotify
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	/*@ResponseBody
	@RequestMapping(value = {"listnoticeApp"})
	public String listnoticeApp(MeetingData meetingData, HttpServletRequest request, HttpServletResponse response, Model model) {
		
	
		String title    = request.getParameter("title");//手机端的查询条件
		String fromPage = request.getParameter("fromPage");
		String loginName= request.getParameter("mobile");//前台传过来的手机号
		//设置手机端分页参数
		String pageNo   = request.getParameter("pageNo");
		String pageSize = request.getParameter("pageSize");
		Page<User> pageApp = new Page<User>();
		pageApp.setPageNo( Integer.valueOf(pageNo));
		pageApp.setPageSize(Integer.valueOf(pageSize));
		
		
		String userCode = request.getParameter("code");
		
		///////////////////////////////////////////////////
		if (userCode != null && !"".equals(userCode)) {  
	        
			
			
			if(StringUtils.isBlank(loginName))
			{
				loginName = WeixinUntil.getWeiXinLongInfo(userCode, request.getSession(), sCorpID, sCorpSecret);
			}	
			
			
			
			User user = meetingDataService.getByMobile(loginName);//微信是通过手机号来查的 
			//if(null != user&&StringUtils.equals(user.getNo(), Global.CHR))
			if(null != user)
			{
				meetingData.getTempMap().put("mobile", loginName);//把手机号传到前台
				
				user.setPage(pageApp);
				
				if(StringUtils.equals(fromPage, "mychengbao"))
				{
					user.setNo("2");//这里只代表查询条件参数用--表示只查询参会人状态2   已呈报的
				}
				else
				{
					
					user.setNo("1");//这里只代表查询条件参数用--表示只查询参会人状态1   已上报的
				}	
				user.setName(title);//当查询条件使用
				meetingData.getTempMap().put("listentity", meetingDataService.findListappforattendee(user));
				meetingData.getTempMap().put("total", pageApp.getCount());
				
			}
			else
			{
				if(StringUtils.equals("erro", loginName))
				{
					meetingData.getTempMap().put("msg", "无法获取微信用户信息！");
				}else
				{
					meetingData.getTempMap().put("msg", "系统没有该用户信息！");
				}	
				
			}	
			
		} else {
			meetingData.getTempMap().put("msg", "无法获取用户信息！");
		}
		///////////////////////////////////////////////////////////////////
		
		
		return JsonMapper.getInstance().toJson(meetingData.getTempMap());
	}
	
	
	*/
	
	@RequestMapping(value = "test",method = RequestMethod.GET)
	@ResponseBody
	protected String bill(ServletRequest request, ServletResponse response) {
		 String getParam=request.getParameter("test");
		 System.out.println(getParam);

		
		return  "ceshi1";
	}
	
	
	
	
	

	public String getCaptchaParam() {
		return captchaParam;
	}

	protected String getCaptcha(ServletRequest request) {
		return WebUtils.getCleanParam(request, getCaptchaParam());
	}

	public String getMobileLoginParam() {
		return mobileLoginParam;
	}
	
	protected boolean isMobileLogin(ServletRequest request) {
        return WebUtils.isTrue(request, getMobileLoginParam());
    }
	
	public String getMessageParam() {
		return messageParam;
	}
	
	/**
	 * 登录成功之后跳转URL
	 */
	public String getSuccessUrl() {
		return super.getSuccessUrl();
	}
	
	@Override
	protected void issueSuccessRedirect(ServletRequest request,
			ServletResponse response) throws Exception {
//		Principal p = UserUtils.getPrincipal();
//		if (p != null && !p.isMobileLogin()){
			 WebUtils.issueRedirect(request, response, getSuccessUrl(), null, true);
//		}else{
//			super.issueSuccessRedirect(request, response);
//		}
	}

	/**
	 * 登录失败调用事件
	 */
	@Override
	protected boolean onLoginFailure(AuthenticationToken token,
			AuthenticationException e, ServletRequest request, ServletResponse response) {
		String className = e.getClass().getName(), message = "";
		if (IncorrectCredentialsException.class.getName().equals(className)
				|| UnknownAccountException.class.getName().equals(className)){
			message = "用户或密码错误, 请重试.";
		}
		else if (e.getMessage() != null && StringUtils.startsWith(e.getMessage(), "msg:")){
			message = StringUtils.replace(e.getMessage(), "msg:", "");
		}
		else{
			message = "系统出现点问题，请稍后再试！";
			e.printStackTrace(); // 输出到控制台
		}
        request.setAttribute(getFailureKeyAttribute(), className);
        request.setAttribute(getMessageParam(), message);
        return true;
	}
	
	/**
 	 * 下载附件
 	 * 
 	 */
	@RequestMapping(value = {"downLoad"})
	public  void  downLoad(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException{
		 
		 
			response.setCharacterEncoding("UTF-8");
			String fName		=  request.getParameter("fileName");
			fName = new String(fName.getBytes("UTF-8"),"UTF-8");
			
			String filePath =  request.getParameter("filePath");
			String webPath = new File( request.getSession().getServletContext().getRealPath("/")).getParent();// 当前WEB环境的目录
		 
				
				try {
					File file = null;  
					file = new File(webPath+filePath);
					 if(file.exists()){
							
		
							 response.reset();
							 response.setCharacterEncoding("UTF-8");
							 response.setContentType("application/x-msdownload"); 
							 response.setHeader("Content-Disposition", "attachment; filename=" + new String(fName.getBytes(), "ISO8859-1"));
		
							 FileCopyUtils.copy(new FileInputStream(file), response.getOutputStream());
								return;
							
					 }else{
						 response.getWriter().print("<script>alert('系统找不到目标文件');history.back();</script>");
						 response.getWriter().flush();
					 }
				} catch (Exception e) {
					e.printStackTrace();
					
				} 
			}
	
	
	/**
 	 * 获取封装后菜单的url
 	 * @param url
 	 * @param sCorpID
 	 * @return
 	 * 
 	 */
 	public String getMenuUrl(String url,String sCorpID){
 		String menuUrl = "https://open.weixin.qq.com/connect/oauth2/authorize?appid="+sCorpID+"&redirect_uri="+url+"&response_type=code&scope=snsapi_base#wechat_redirect"; 		
		return menuUrl;
 	}
	
}