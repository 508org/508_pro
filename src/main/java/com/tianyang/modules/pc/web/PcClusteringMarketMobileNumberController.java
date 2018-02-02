package com.tianyang.modules.pc.web;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.google.common.collect.Maps;
import com.tianyang.common.config.Global;
import com.tianyang.common.mapper.JsonMapper;
import com.tianyang.common.persistence.Page;
import com.tianyang.common.web.BaseController;
import com.tianyang.common.utils.StringUtils;
import com.tianyang.modules.pc.entity.PcClusteringMarketMobileNumber;
import com.tianyang.modules.pc.entity.PcGroupMobileNumber;
import com.tianyang.modules.pc.service.PcClusteringMarketMobileNumberService;

/**
 * 聚类市场手机号码Controller
 * @author 刘笑林
 * @version 2017-06-23
 */
@Controller
@RequestMapping(value = "${adminPath}/pc/pcClusteringMarketMobileNumber")
public class PcClusteringMarketMobileNumberController extends BaseController {

	@Autowired
	private PcClusteringMarketMobileNumberService pcClusteringMarketMobileNumberService;
	
	@ModelAttribute
	public PcClusteringMarketMobileNumber get(@RequestParam(required=false) String id) {
		PcClusteringMarketMobileNumber entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = pcClusteringMarketMobileNumberService.get(id);
		}
		if (entity == null){
			entity = new PcClusteringMarketMobileNumber();
		}
		return entity;
	}
	
	@RequiresPermissions("pc:pcClusteringMarketMobileNumber:view")
	@RequestMapping(value = {"list", ""})
	public String list(PcClusteringMarketMobileNumber pcClusteringMarketMobileNumber, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<PcClusteringMarketMobileNumber> page = pcClusteringMarketMobileNumberService.findPage(new Page<PcClusteringMarketMobileNumber>(request, response), pcClusteringMarketMobileNumber); 
		model.addAttribute("page", page);
		return "modules/pc/pcClusteringMarketMobileNumberList";
	}

	@RequiresPermissions("pc:pcClusteringMarketMobileNumber:view")
	@RequestMapping(value = "form")
	public String form(PcClusteringMarketMobileNumber pcClusteringMarketMobileNumber, Model model) {
		model.addAttribute("pcClusteringMarketMobileNumber", pcClusteringMarketMobileNumber);
		return "modules/pc/pcClusteringMarketMobileNumberForm";
	}

	@RequiresPermissions("pc:pcClusteringMarketMobileNumber:edit")
	@RequestMapping(value = "save")
	public String save(PcClusteringMarketMobileNumber pcClusteringMarketMobileNumber, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, pcClusteringMarketMobileNumber)){
			return form(pcClusteringMarketMobileNumber, model);
		}
		pcClusteringMarketMobileNumberService.save(pcClusteringMarketMobileNumber);
		addMessage(redirectAttributes, "保存聚类市场手机号码成功");
		return "redirect:"+Global.getAdminPath()+"/pc/pcClusteringMarketMobileNumber/?repage";
	}
	
	@RequiresPermissions("pc:pcClusteringMarketMobileNumber:edit")
	@RequestMapping(value = "delete")
	public String delete(PcClusteringMarketMobileNumber pcClusteringMarketMobileNumber, RedirectAttributes redirectAttributes) {
		pcClusteringMarketMobileNumberService.delete(pcClusteringMarketMobileNumber);
		addMessage(redirectAttributes, "删除聚类市场手机号码成功");
		return "redirect:"+Global.getAdminPath()+"/pc/pcClusteringMarketMobileNumber/?repage";
	}
	/**
	 * 获取手机号码信息
	 * @param shopName, operators
	 * @return
	 * @throws UnsupportedEncodingException 
	 */
	@RequestMapping(value = "getMobileNumber/{shopName}/{operators}")
	@ResponseBody
	public String getMobileNumber(@PathVariable("shopName") String shopName, @PathVariable("operators") String operators) throws UnsupportedEncodingException {
		PcClusteringMarketMobileNumber pcClusteringMarketMobileNumber=new PcClusteringMarketMobileNumber();
		pcClusteringMarketMobileNumber.setShopName(URLDecoder.decode(shopName, "UTF-8"));
		pcClusteringMarketMobileNumber.setOperators(URLDecoder.decode(operators, "UTF-8"));
		List<PcClusteringMarketMobileNumber> list = pcClusteringMarketMobileNumberService.findList(pcClusteringMarketMobileNumber); 
		/*Map<String, Object> map = Maps.newHashMap();
		map.put("key", list);
		return JsonMapper.getInstance().toJson(map);*/
		return JsonMapper.getInstance().toJson(list);
	}
	/**
	 * 添加手机号码信息
	 * @param shopName, operators
	 * @return
	 * @throws UnsupportedEncodingException 
	 */
	@RequestMapping(value = "addMobileNumber/{shopName}/{operators}/{mobileNumber}")
	@ResponseBody
	public String addMobileNumber(@PathVariable("shopName") String shopName, @PathVariable("operators") String operators, @PathVariable("mobileNumber") String mobileNumber) throws UnsupportedEncodingException {
		PcClusteringMarketMobileNumber pcClusteringMarketMobileNumber=new PcClusteringMarketMobileNumber();
		pcClusteringMarketMobileNumber.setShopName(URLDecoder.decode(shopName, "UTF-8"));
		pcClusteringMarketMobileNumber.setOperators(URLDecoder.decode(operators, "UTF-8"));
		pcClusteringMarketMobileNumber.setMobileNumber(URLDecoder.decode(mobileNumber, "UTF-8"));
		List<PcClusteringMarketMobileNumber> list = pcClusteringMarketMobileNumberService.findList(pcClusteringMarketMobileNumber);
		if(list.size()>0)
		{
			return "failure";
		}
		pcClusteringMarketMobileNumberService.save(pcClusteringMarketMobileNumber); 
		return "success";
	}
	/**
	 * 修改手机号码信息
	 * @param shopName, operators
	 * @return
	 * @throws UnsupportedEncodingException 
	 */
	@RequestMapping(value = "editMobileNumber/{shopName}/{oldMobileNumber}/{mobileNumber}")
	@ResponseBody
	public String editMobileNumber(@PathVariable("shopName") String shopName, @PathVariable("oldMobileNumber") String oldMobileNumber, @PathVariable("mobileNumber") String mobileNumber) throws UnsupportedEncodingException {
		PcClusteringMarketMobileNumber pcClusteringMarketMobileNumber=new PcClusteringMarketMobileNumber();
		pcClusteringMarketMobileNumber.setShopName(URLDecoder.decode(shopName, "UTF-8"));
		pcClusteringMarketMobileNumber.setMobileNumber(URLDecoder.decode(mobileNumber, "UTF-8"));
		List<PcClusteringMarketMobileNumber> list = pcClusteringMarketMobileNumberService.findList(pcClusteringMarketMobileNumber);
		if(list.size()>0&&!oldMobileNumber.equals(mobileNumber))
		{
			return "failure";
		}
		pcClusteringMarketMobileNumber.setMobileNumber(URLDecoder.decode(oldMobileNumber, "UTF-8"));
		List<PcClusteringMarketMobileNumber> oldList = pcClusteringMarketMobileNumberService.findList(pcClusteringMarketMobileNumber);
		pcClusteringMarketMobileNumber=oldList.get(0);
		pcClusteringMarketMobileNumber.setMobileNumber(URLDecoder.decode(mobileNumber, "UTF-8"));		
		pcClusteringMarketMobileNumberService.save(pcClusteringMarketMobileNumber);
		return "success";
	}
	/**
	 * 删除手机号码信息
	 * @param shopName, operators
	 * @return
	 * @throws UnsupportedEncodingException 
	 */
	@RequestMapping(value = "deleteMobileNumber/{shopName}/{operators}/{mobileNumber}")
	@ResponseBody
	public String deleteMobileNumber(@PathVariable("shopName") String shopName, @PathVariable("operators") String operators, @PathVariable("mobileNumber") String mobileNumber) throws UnsupportedEncodingException {
		PcClusteringMarketMobileNumber pcClusteringMarketMobileNumber=new PcClusteringMarketMobileNumber();
		pcClusteringMarketMobileNumber.setShopName(URLDecoder.decode(shopName, "UTF-8"));
		pcClusteringMarketMobileNumber.setOperators(URLDecoder.decode(operators, "UTF-8"));
		pcClusteringMarketMobileNumber.setMobileNumber(URLDecoder.decode(mobileNumber, "UTF-8"));
		List<PcClusteringMarketMobileNumber> list = pcClusteringMarketMobileNumberService.findList(pcClusteringMarketMobileNumber);
		if(list.size()==0)
		{
			return "failure";
		}
		pcClusteringMarketMobileNumberService.delete(pcClusteringMarketMobileNumberService.get(list.get(0).getId())); 
		return "success";
	}
	
}