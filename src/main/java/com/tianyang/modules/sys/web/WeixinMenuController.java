/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.tianyang.modules.sys.web;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.google.common.collect.Maps;
import com.tianyang.common.config.Global;
import com.tianyang.common.utils.StringUtils;
import com.tianyang.common.web.BaseController;
import com.tianyang.modules.sys.entity.WeixinMenu;
import com.tianyang.modules.sys.service.WeixinMenuService;
import com.tianyang.modules.sys.utils.WeixinUntil;

/**
 * 微信菜单Controller
 * @author konglq
 * @version 2017-05-10
 */
@Controller
@RequestMapping(value = "${adminPath}/weixinmenu/weixinmenu")
public class WeixinMenuController extends BaseController {

	@Autowired
	private WeixinMenuService weixinMenuService;
	
	@ModelAttribute
	public WeixinMenu get(@RequestParam(required=false) String id) {
		WeixinMenu entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = weixinMenuService.get(id);
		}
		if (entity == null){
			entity = new WeixinMenu();
		}
		return entity;
	}
	
	@RequiresPermissions("weixinmenu:weixinMenu:view")
	@RequestMapping(value = {"list", ""})
	public String list(WeixinMenu weixinMenu, HttpServletRequest request, HttpServletResponse response, Model model) {
		
		List list = weixinMenuService.findList(weixinMenu);
		model.addAttribute("list",list );
		return "modules/sys/weixinMenuList";
	}

	@RequiresPermissions("weixinmenu:weixinMenu:view")
	@RequestMapping(value = "form")
	public String form(WeixinMenu weixinMenu, Model model) {
		model.addAttribute("weixinMenu", weixinMenu);
		
		model.addAttribute("parentList", weixinMenuService.getParentIdAndName(weixinMenu));
		return "modules/sys/weixinMenuForm";
	}

	@RequiresPermissions("weixinmenu:weixinMenu:edit")
	@RequestMapping(value = "save")
	public String save(WeixinMenu weixinMenu, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, weixinMenu)){
			return form(weixinMenu, model);
		}
		weixinMenuService.save(weixinMenu);
		addMessage(redirectAttributes, "保存微信菜单成功");
		return "redirect:"+Global.getAdminPath()+"/weixinmenu/weixinmenu/?repage";
	}
	
	@RequiresPermissions("weixinmenu:weixinMenu:edit")
	@RequestMapping(value = "delete")
	public String delete(WeixinMenu weixinMenu, RedirectAttributes redirectAttributes) {
		weixinMenuService.delete(weixinMenu);
		addMessage(redirectAttributes, "删除微信菜单成功");
		return "redirect:"+Global.getAdminPath()+"/weixinmenu/weixinmenu/?repage";
	}
	
	@RequiresPermissions("weixinmenu:weixinMenu:edit")
	@RequestMapping(value = "syncWeixinMenu")
	public String syncWeixinMenu(WeixinMenu weixinMenu, RedirectAttributes redirectAttributes) {
		/**
		 * 同步微信代码
		 */
		//获取企业号应用的id
				String agentid     = Global.AGENTID;
				String sCorpID     = Global.sCORPID;
				String sCorpSecret = Global.sCORPSECRET;
				Map json = Maps.newHashMap();
				json.put("msg", "");
				try {	
					
					//获取所有的一级菜单
					WeixinMenu weixinMenuSel = new WeixinMenu();
					weixinMenuSel.setMenuType("button");
					List menuList = weixinMenuService.getParentIdAndName(weixinMenuSel);
					if(menuList!=null && menuList.size()>0){
						JSONObject jsonTop = new JSONObject();
						JSONArray jsonArrayTop = new JSONArray();
						for(int i=0;i<menuList.size();i++){					
							JSONObject jsonOne = new JSONObject();
							WeixinMenu menuInfo =  (WeixinMenu) menuList.get(i);
							
							String menuid = menuInfo.getId();	
							jsonOne.put("name", menuInfo.getName());
							//判断菜单是否下是否二级机菜单	
							weixinMenuSel.setMenuType("sub_button");
							weixinMenuSel.setSelectid(menuid);
							List twomenuList = weixinMenuService.getParentIdAndName(weixinMenuSel);
							if(twomenuList!=null && twomenuList.size()>0){
								JSONArray jsonArray = new JSONArray();
								//有二级菜单,将该菜单下的二级菜单信息生成json格式的数据
								for(int j=0;j<twomenuList.size();j++){
									JSONObject jsonTwo = new JSONObject();
									WeixinMenu twoMenuInfo = (WeixinMenu) twomenuList.get(j);							
									jsonTwo.put("name", twoMenuInfo.getName());
									//判断该菜单的响应动作类型  
									Object actionMenu = twoMenuInfo.getActionType();
									jsonTwo.put("type", actionMenu.toString());
									if(actionMenu.equals("view")){
										// view类型必须 设置url，并将url处理
										String url = "";
										try {
											url = URLEncoder.encode(twoMenuInfo.getUrl(),"UTF-8");
										} catch (UnsupportedEncodingException e) {
											// TODO Auto-generated catch block
											e.printStackTrace();
										}
										jsonTwo.put("url", getMenuUrl(url,sCorpID));
									}else{
										//click等点击类型必须设置key值
										jsonTwo.put("key", twoMenuInfo.getKeywx().toString());
									}
									jsonArray.put(jsonTwo);
								}	
								jsonOne.put("sub_button", jsonArray);
							}else{
								//没有二级菜单,判断该菜单的类型,如果存在则判断类型并保存生成jsin数据
								Object actionMenu = menuInfo.getActionType();
								if(actionMenu!=null && !actionMenu.equals("")){							
									jsonOne.put("type", actionMenu.toString());
									if(actionMenu.equals("view")){
										// view类型必须 设置url
										String url = URLEncoder.encode(menuInfo.getUrl());
										jsonOne.put("url", getMenuUrl(url,sCorpID));
									}else{
										//click等点击类型必须设置key值
										jsonOne.put("key", menuInfo.getKeywx());
									}
								}
							}					
							//判断json是否为空
							if(!jsonOne.isNull("sub_button")){
								jsonArrayTop.put(jsonOne);
							}else if(!jsonOne.isNull("type")){
								jsonArrayTop.put(jsonOne);
							}		
						}
						jsonTop.put("button", jsonArrayTop);
						System.out.println("jsonTop:"+jsonTop);
						//将生成的json数据同步到微信端
						if(!jsonTop.isNull("button")){					
							String url = "https://qyapi.weixin.qq.com/cgi-bin/menu/create?access_token="+WeixinUntil.getAccessToken(sCorpID,sCorpSecret)+"&agentid="+agentid+"";
							String resultJson = WeixinUntil.post(url, jsonTop.toString());
							//判断是否同步成功{"errcode":0,"errmsg":"ok"}
							JSONObject jsonObject = new JSONObject(resultJson);
							String errmsg = jsonObject.get("errmsg").toString();
							if(errmsg.equals("ok")){
								json.put("success",true);
								json.put("msg","同步微信成功！");
							}else{
								int errcode = Integer.valueOf( jsonObject.get("errcode").toString());
								 String errcodeInfo = WeixinUntil.getErrorInfo(errcode);
								
								 json.put("success",false);
								 json.put("msg","同步微信失败,"+errcodeInfo+"!");
							}
						}
					}else{
						json.put("success",false);
						json.put("msg","未查寻到菜单数据！");
					}
				} catch (JSONException e) {
					json.put("success",false);
					json.put("msg","出现异常,请检查是否配置微信数字字典！");
					e.printStackTrace();
				}
		
		/**
		 * 同步微信代码end
		 */
		
		addMessage(redirectAttributes, json.get("msg").toString());
		return "redirect:"+Global.getAdminPath()+"/weixinmenu/weixinmenu/?repage";
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