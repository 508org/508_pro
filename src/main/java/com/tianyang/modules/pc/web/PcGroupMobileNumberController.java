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
import com.tianyang.modules.oa.entity.Leave;
import com.tianyang.modules.pc.entity.PcGroupMobileNumber;
import com.tianyang.modules.pc.service.PcGroupMobileNumberService;

/**
 * ABC类集团手机号码Controller
 * @author 刘笑林
 * @version 2017-06-23
 */
@Controller
@RequestMapping(value = "${adminPath}/pc/pcGroupMobileNumber")
public class PcGroupMobileNumberController extends BaseController {

	@Autowired
	private PcGroupMobileNumberService pcGroupMobileNumberService;
	
	@ModelAttribute
	public PcGroupMobileNumber get(@RequestParam(required=false) String id) {
		PcGroupMobileNumber entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = pcGroupMobileNumberService.get(id);
		}
		if (entity == null){
			entity = new PcGroupMobileNumber();
		}
		return entity;
	}
	
	@RequiresPermissions("pc:pcGroupMobileNumber:view")
	@RequestMapping(value = {"list", ""})
	public String list(PcGroupMobileNumber pcGroupMobileNumber, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<PcGroupMobileNumber> page = pcGroupMobileNumberService.findPage(new Page<PcGroupMobileNumber>(request, response), pcGroupMobileNumber); 
		model.addAttribute("page", page);
		return "modules/pc/pcGroupMobileNumberList";
	}

	@RequiresPermissions("pc:pcGroupMobileNumber:view")
	@RequestMapping(value = "form")
	public String form(PcGroupMobileNumber pcGroupMobileNumber, Model model) {
		model.addAttribute("pcGroupMobileNumber", pcGroupMobileNumber);
		return "modules/pc/pcGroupMobileNumberForm";
	}

	@RequiresPermissions("pc:pcGroupMobileNumber:edit")
	@RequestMapping(value = "save")
	public String save(PcGroupMobileNumber pcGroupMobileNumber, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, pcGroupMobileNumber)){
			return form(pcGroupMobileNumber, model);
		}
		pcGroupMobileNumberService.save(pcGroupMobileNumber);
		addMessage(redirectAttributes, "保存ABC类集团手机号码成功");
		return "redirect:"+Global.getAdminPath()+"/pc/pcGroupMobileNumber/?repage";
	}
	
	@RequiresPermissions("pc:pcGroupMobileNumber:edit")
	@RequestMapping(value = "delete")
	public String delete(PcGroupMobileNumber pcGroupMobileNumber, RedirectAttributes redirectAttributes) {
		pcGroupMobileNumberService.delete(pcGroupMobileNumber);
		addMessage(redirectAttributes, "删除ABC类集团手机号码成功");
		return "redirect:"+Global.getAdminPath()+"/pc/pcGroupMobileNumber/?repage";
	}
	/**
	 * 获取手机号码信息
	 * @param groupNumber, operators
	 * @return
	 * @throws UnsupportedEncodingException 
	 */
	@RequestMapping(value = "getMobileNumber/{groupNumber}/{operators}")
	@ResponseBody
	public String getMobileNumber(@PathVariable("groupNumber") String groupNumber, @PathVariable("operators") String operators) throws UnsupportedEncodingException {
		PcGroupMobileNumber pcGroupMobileNumber=new PcGroupMobileNumber();
		pcGroupMobileNumber.setGroupNumber(URLDecoder.decode(groupNumber, "UTF-8"));
		pcGroupMobileNumber.setOperators(URLDecoder.decode(operators, "UTF-8"));
		List<PcGroupMobileNumber> list = pcGroupMobileNumberService.findList(pcGroupMobileNumber); 
		/*Map<String, Object> map = Maps.newHashMap();
		map.put("key", list);*/
		return JsonMapper.getInstance().toJson(list);
	}
	/**
	 * 添加手机号码信息
	 * @param groupNumber, operators
	 * @return
	 * @throws UnsupportedEncodingException 
	 */
	@RequestMapping(value = "addMobileNumber/{groupNumber}/{operators}/{mobileNumber}")
	@ResponseBody
	public String addMobileNumber(@PathVariable("groupNumber") String groupNumber, @PathVariable("operators") String operators, @PathVariable("mobileNumber") String mobileNumber) throws UnsupportedEncodingException {
		PcGroupMobileNumber pcGroupMobileNumber=new PcGroupMobileNumber();
		pcGroupMobileNumber.setGroupNumber(URLDecoder.decode(groupNumber, "UTF-8"));
		pcGroupMobileNumber.setOperators(URLDecoder.decode(operators, "UTF-8"));
		pcGroupMobileNumber.setMobileNumber(URLDecoder.decode(mobileNumber, "UTF-8"));
		List<PcGroupMobileNumber> list = pcGroupMobileNumberService.findList(pcGroupMobileNumber);
		if(list.size()>0)
		{
			return "failure";
		}
		pcGroupMobileNumberService.save(pcGroupMobileNumber); 
		return "success";
	}
	/**
	 * 修改手机号码信息
	 * @param groupNumber, operators
	 * @return
	 * @throws UnsupportedEncodingException 
	 */
	@RequestMapping(value = "editMobileNumber/{groupNumber}/{oldMobileNumber}/{mobileNumber}")
	@ResponseBody
	public String editMobileNumber(@PathVariable("groupNumber") String groupNumber, @PathVariable("oldMobileNumber") String oldMobileNumber, @PathVariable("mobileNumber") String mobileNumber) throws UnsupportedEncodingException {
		PcGroupMobileNumber pcGroupMobileNumber=new PcGroupMobileNumber();
		pcGroupMobileNumber.setGroupNumber(URLDecoder.decode(groupNumber, "UTF-8"));
		pcGroupMobileNumber.setMobileNumber(URLDecoder.decode(mobileNumber, "UTF-8"));
		List<PcGroupMobileNumber> list = pcGroupMobileNumberService.findList(pcGroupMobileNumber);
		if(list.size()>0&&!oldMobileNumber.equals(mobileNumber))
		{
			return "failure";
		}
		pcGroupMobileNumber.setMobileNumber(URLDecoder.decode(oldMobileNumber, "UTF-8"));
		List<PcGroupMobileNumber> oldList = pcGroupMobileNumberService.findList(pcGroupMobileNumber);
		pcGroupMobileNumber=oldList.get(0);
		pcGroupMobileNumber.setMobileNumber(URLDecoder.decode(mobileNumber, "UTF-8"));		
		pcGroupMobileNumberService.save(pcGroupMobileNumber);
		return "success";
	}
	/**
	 * 删除手机号码信息
	 * @param groupNumber, operators
	 * @return
	 * @throws UnsupportedEncodingException 
	 */
	@RequestMapping(value = "deleteMobileNumber/{groupNumber}/{operators}/{mobileNumber}")
	@ResponseBody
	public String deleteMobileNumber(@PathVariable("groupNumber") String groupNumber, @PathVariable("operators") String operators, @PathVariable("mobileNumber") String mobileNumber) throws UnsupportedEncodingException {
		PcGroupMobileNumber pcGroupMobileNumber=new PcGroupMobileNumber();
		pcGroupMobileNumber.setGroupNumber(URLDecoder.decode(groupNumber, "UTF-8"));
		pcGroupMobileNumber.setOperators(URLDecoder.decode(operators, "UTF-8"));
		pcGroupMobileNumber.setMobileNumber(URLDecoder.decode(mobileNumber, "UTF-8"));
		List<PcGroupMobileNumber> list = pcGroupMobileNumberService.findList(pcGroupMobileNumber);
		if(list.size()==0)
		{
			return "failure";
		}
		pcGroupMobileNumberService.delete(pcGroupMobileNumberService.get(list.get(0).getId())); 
		return "success";
	}
}