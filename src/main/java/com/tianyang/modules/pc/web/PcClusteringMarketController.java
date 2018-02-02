package com.tianyang.modules.pc.web;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolationException;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.google.common.collect.Maps;
import com.tianyang.common.beanvalidator.BeanValidators;
import com.tianyang.common.config.Global;
import com.tianyang.common.mapper.JsonMapper;
import com.tianyang.common.persistence.Page;
import com.tianyang.common.web.BaseController;
import com.tianyang.common.utils.StringUtils;
import com.tianyang.common.utils.excel.ImportExcel;
import com.tianyang.modules.pc.entity.PcClusteringMarket;
import com.tianyang.modules.pc.entity.PcClusteringMarketMobileNumber;
import com.tianyang.modules.pc.entity.PcGroup;
import com.tianyang.modules.pc.service.PcClusteringMarketMobileNumberService;
import com.tianyang.modules.pc.service.PcClusteringMarketService;
import com.tianyang.modules.sys.entity.Office;
import com.tianyang.modules.sys.entity.Role;
import com.tianyang.modules.sys.entity.User;
import com.tianyang.modules.sys.service.OfficeService;
import com.tianyang.modules.sys.service.SystemService;
import com.tianyang.modules.sys.utils.UserUtils;
import com.tianyang.modules.sys.utils.WeixinUntil;

/**
 * 聚类市场信息Controller
 * @author 刘笑林
 * @version 2017-06-08
 */
@Controller
@RequestMapping(value = "${adminPath}/pc/pcClusteringMarket")
public class PcClusteringMarketController extends BaseController {
	@Autowired
	private PcClusteringMarketService pcClusteringMarketService;
	@Autowired
	private OfficeService officeService;
	@Autowired
	private PcClusteringMarketMobileNumberService pcClusteringMarketMobileNumberService;
	@Autowired
	private SystemService systemService;
	
	@ModelAttribute
	public PcClusteringMarket get(@RequestParam(required=false) String id) {
		PcClusteringMarket entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = pcClusteringMarketService.get(id);
		}
		if (entity == null){
			entity = new PcClusteringMarket();
		}
		return entity;
	}
	
	@RequiresPermissions("pc:pcClusteringMarket:view")
	@RequestMapping(value = {"list", ""})
	public String list(PcClusteringMarket pcClusteringMarket, HttpServletRequest request, HttpServletResponse response, Model model) {
		String org=request.getParameter("org");
		org=org==null?"":org;
		if(org.length()>0 && !org.equals("1"))
		{
			Office office=new Office(); 
			office=officeService.get(org);
			pcClusteringMarket.setOrganization(office);
		}
		String grid=request.getParameter("grid");
		grid=grid==null?"":grid;
		if(grid.length()>0)
		{
			Office office=new Office(); 
			office=officeService.get(grid);
			pcClusteringMarket.setAttributedGrid(office);
		}
		String manager=request.getParameter("manager");
		manager=manager==null?"":manager;
		if(manager.length()>0)
		{
			User user=new User(); 
			user=systemService.getUser(manager);
			pcClusteringMarket.setCustomerManagerNumber(user);
		}
		//如果不是管理员只能看到客户经理是自己的信息
		User user=new User();
		user=UserUtils.getUser();
		/*boolean glyFlag=false;//判断是否为管理员
		for (Role r : user.getRoleList()){
			if(r.getEnname().equals("gly")||r.getEnname().equals("xtgly"))
			{
				glyFlag=true;
				
			}			
		}
		if(!glyFlag){
			pcClusteringMarket.setCustomerManagerNumber(user);
		}*/
		for (Role r : user.getRoleList()){
			if(r.getEnname().equals("qxgly"))
			{
				pcClusteringMarket.setOrganization(user.getOffice());
				
			}	
			if(r.getEnname().equals("ptyh"))
			{
				pcClusteringMarket.setCustomerManagerNumber(user);
				
			}
		}
		String flag=request.getParameter("flag");
		
		Page<PcClusteringMarket> page = pcClusteringMarketService.findPage(new Page<PcClusteringMarket>(request, response), pcClusteringMarket); 
		model.addAttribute("page", page);
		String display=request.getParameter("display");
		model.addAttribute("display", display);
		model.addAttribute("flag", flag);
		return "modules/pc/pcClusteringMarketList";
	}
	
	@RequestMapping(value = {"listApp"})
	public String listApp(PcClusteringMarket pcClusteringMarket, HttpServletRequest request, HttpServletResponse response, Model model) {
		String pageNo   = request.getParameter("pageNo");
		String pageSize = request.getParameter("pageSize");

		Page<PcGroup> pageApp = new Page<PcGroup>();
		pageApp.setPageNo( Integer.valueOf(pageNo));
		pageApp.setPageSize(Integer.valueOf(pageSize));
		/*String userCode = request.getParameter("code");
		String loginName="";
		if (userCode != null && !"".equals(userCode))
		{  
			loginName = WeixinUntil.getWeiXinLongInfo(userCode, request.getSession(), sCorpID, sCorpSecret);
		}
		model.addAttribute("loginName", loginName);*/
		//如果不是管理员只能看到客户经理是自己的信息
		User user=new User();
		//user=UserUtils.getByMobile(loginName);
		user=UserUtils.getUser();
		for (Role r : user.getRoleList()){
			if(r.getEnname().equals("qxgly"))
			{
				pcClusteringMarket.setOrganization(user.getOffice());
				
			}	
			if(r.getEnname().equals("ptyh"))
			{
				pcClusteringMarket.setCustomerManagerNumber(user);
				
			}
		}
		
		Page<PcClusteringMarket> page = pcClusteringMarketService.findPage(new Page<PcClusteringMarket>(request, response), pcClusteringMarket); 
		Map<String, Object> map = Maps.newHashMap();

		map.put("listentity", page.getList());
		map.put("count", page.getCount());
		return JsonMapper.getInstance().toJson(map);
	}
	@RequestMapping(value = {"getList"})
	public String testactivity(PcClusteringMarket pcClusteringMarket, HttpServletRequest request, HttpServletResponse response, Model model) {

		return "modules/pc/pcClusteringMarketList";
	}
	@RequiresPermissions("pc:pcClusteringMarket:view")
	@RequestMapping(value = "form")
	public String form(PcClusteringMarket pcClusteringMarket, Model model, HttpServletRequest request) {
		model.addAttribute("pcClusteringMarket", pcClusteringMarket);
		String display=request.getParameter("display");
		String flag=request.getParameter("flag");
		model.addAttribute("display", display);
		model.addAttribute("flag", flag);
		return "modules/pc/pcClusteringMarketForm";
	}
	@RequestMapping(value = "formApp")
	public String formApp(PcClusteringMarket pcClusteringMarket, Model model, HttpServletRequest request) {
		model.addAttribute("pcClusteringMarket", pcClusteringMarket);
		return "modules/pc/pcClusteringMarketForm";
	}
	@RequiresPermissions("pc:pcClusteringMarket:edit")
	@RequestMapping(value = "save")
	public String save(PcClusteringMarket pcClusteringMarket, Model model, RedirectAttributes redirectAttributes, HttpServletRequest request) {
		if (!beanValidator(model, pcClusteringMarket)){
			return form(pcClusteringMarket, model,request);
		}
		String display=request.getParameter("display");
		pcClusteringMarketService.save(pcClusteringMarket);
		addMessage(redirectAttributes, "保存聚类市场信息成功");
		return "redirect:"+Global.getAdminPath()+"/pc/pcClusteringMarket/?repage&display="+display;
	}
	@RequestMapping(value = "saveApp")
	public String saveApp(PcClusteringMarket pcClusteringMarket, Model model, RedirectAttributes redirectAttributes, HttpServletRequest request) {
		if (!beanValidator(model, pcClusteringMarket)){
			return form(pcClusteringMarket, model,request);
		}
		pcClusteringMarketService.save(pcClusteringMarket);
		addMessage(redirectAttributes, "保存聚类市场信息成功");
		return "redirect:"+Global.getAdminPath()+"/pc/pcClusteringMarket/listApp?repage";
	}
	@RequiresPermissions("pc:pcClusteringMarket:edit")
	@RequestMapping(value = "delete")
	public String delete(PcClusteringMarket pcClusteringMarket, RedirectAttributes redirectAttributes, Model model, HttpServletRequest request) {
		pcClusteringMarketService.delete(pcClusteringMarket);
		String display=request.getParameter("display");
		addMessage(redirectAttributes, "删除聚类市场信息成功");
		return "redirect:"+Global.getAdminPath()+"/pc/pcClusteringMarket/?repage&display="+display;
	}
	/**
	 * 导入聚类市场数据
	 * @param file
	 * @param redirectAttributes
	 * @return
	 */
	@RequiresPermissions("pc:pcClusteringMarket:import")
    @RequestMapping(value = "import", method=RequestMethod.POST)
    public String importFile(MultipartFile file, RedirectAttributes redirectAttributes) {
		if(Global.isDemoMode()){
			addMessage(redirectAttributes, "演示模式，不允许操作！");
			return "redirect:" + adminPath + "/pc/pcClusteringMarket/list?repage";
		}
		//导入聚类市场信息
		try {
			int successNum = 0;
			int failureNum = 0;
			StringBuilder failureMsg = new StringBuilder();
			ImportExcel ei = new ImportExcel(file, 0, 0);
			List<PcClusteringMarket> list = ei.getDataList(PcClusteringMarket.class);
			for (PcClusteringMarket pcClusteringMarket : list){
				try{
					if ("true".equals(checkClusteringMarket(null,null,pcClusteringMarket.getShopName()))){
						BeanValidators.validateWithException(validator, pcClusteringMarket);
						pcClusteringMarketService.save(pcClusteringMarket);
						successNum++;
					}else{
						String id=pcClusteringMarketService.getClusteringMarket(null,null,pcClusteringMarket.getShopName()).getId().toString();
						pcClusteringMarket.setId(id);
						pcClusteringMarketService.save(pcClusteringMarket);
						/*failureMsg.append("<br/>店铺 "+pcClusteringMarket.getShopName()+" 已存在; ");
						failureNum++;*/
					}
				}catch(ConstraintViolationException ex){
					failureMsg.append("<br/>店铺 "+pcClusteringMarket.getShopName()+" 导入失败：");
					List<String> messageList = BeanValidators.extractPropertyAndMessageAsList(ex, ": ");
					for (String message : messageList){
						failureMsg.append(message+"; ");
						failureNum++;
					}
				}catch (Exception ex) {
					failureMsg.append("<br/>店铺 "+pcClusteringMarket.getShopName()+" 导入失败："+ex.getMessage());
				}
			}
			if (failureNum>0){
				failureMsg.insert(0, "，失败 "+failureNum+" 条聚类市场信息，导入信息如下：");
			}
			addMessage(redirectAttributes, "已成功导入 "+successNum+" 条聚类市场信息信息"+failureMsg);
		} catch (Exception e) {
			addMessage(redirectAttributes, "导入聚类市场信息失败！失败信息："+e.getMessage());
		}
		//导入聚类市场手机信息
		try {
			int successNum = 0;
			int failureNum = 0;
			StringBuilder failureMsg = new StringBuilder();
			ImportExcel ei = new ImportExcel(file, 0, 1);
			List<PcClusteringMarketMobileNumber> list = ei.getDataList(PcClusteringMarketMobileNumber.class);
			for (PcClusteringMarketMobileNumber pcClusteringMarketMobileNumber : list){
				try{
					if ("true".equals(checkMobileNumber(pcClusteringMarketMobileNumber.getMobileNumber()))){
						BeanValidators.validateWithException(validator, pcClusteringMarketMobileNumber);
						pcClusteringMarketMobileNumberService.save(pcClusteringMarketMobileNumber);
						successNum++;
					}else{
						//failureMsg.append("<br/>手机号码 "+pcClusteringMarketMobileNumber.getMobileNumber()+" 已存在; ");
						failureNum++;
					}
				}catch(ConstraintViolationException ex){
					//failureMsg.append("<br/>手机号码 "+pcClusteringMarketMobileNumber.getMobileNumber()+" 导入失败：");
					List<String> messageList = BeanValidators.extractPropertyAndMessageAsList(ex, ": ");
					for (String message : messageList){
						failureMsg.append(message+"; ");
						failureNum++;
					}
				}catch (Exception ex) {
					//failureMsg.append("<br/>手机号码 "+pcClusteringMarketMobileNumber.getMobileNumber()+" 导入失败："+ex.getMessage());
				}
			}
			if (failureNum>0){
				//failureMsg.insert(0, "，失败 "+failureNum+" 条手机号码信息，导入信息如下：");
			}
			//addMessage(redirectAttributes, "已成功导入 "+successNum+" 条手机号码信息信息"+failureMsg);
		} catch (Exception e) {
			//addMessage(redirectAttributes, "导入手机号码信息失败！失败信息："+e.getMessage());
		}
		return "redirect:" + adminPath + "/pc/pcClusteringMarket/list?repage";
    }
	
	
	/**
	 * 验证聚类市场是否有效
	 * @param 
	 * @return
	 */

	@RequestMapping(value = "checkClusteringMarket")
	public String checkClusteringMarket(String attributedGrid,String clusterMarketType,String shopName) {
		if (/*attributedGrid !=null &&clusterMarketType !=null &&shopName !=null &&*/ pcClusteringMarketService.getClusteringMarket(attributedGrid,clusterMarketType,shopName) == null) {
			return "true";
		}
		return "false";
	}
	
	/**
	 * 验证手机号码是否有效
	 * @param mobileNumber
	 * @return
	 */
	
	@RequestMapping(value = "checkMobileNumber")
	public String checkMobileNumber(String mobileNumber) {
		if (mobileNumber !=null && pcClusteringMarketMobileNumberService.getMobileByMobileNumber(mobileNumber) == null) {
			return "true";
		}
		return "false";
	}
	/**
	 * 统计
	 * @param pcClusteringMarket
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value ="count")
	public String count(PcClusteringMarket pcClusteringMarket, HttpServletRequest request, HttpServletResponse response, Model model) {
		String org=request.getParameter("org");
		org=org==null?"":org;
		String grid=request.getParameter("grid");
		grid=grid==null?"":grid;
		if(org.length()==0&&grid.length()==0)
		{
			List<PcClusteringMarket> list = pcClusteringMarketService.findOrgCount(pcClusteringMarket);
			model.addAttribute("list", list);
			model.addAttribute("pcClusteringMarket", pcClusteringMarket);
			return "modules/pc/pcClusteringMarketOrgCount";
		}else if(grid.length()>0)
		{
			Office office=new Office(); 
			office=officeService.get(grid);
			pcClusteringMarket.setAttributedGrid(office);
			List<PcClusteringMarket> list = pcClusteringMarketService.findManagerCount(pcClusteringMarket);
			model.addAttribute("list", list);
			model.addAttribute("pcGroup", pcClusteringMarket);
			return "modules/pc/pcClusteringMarketManagerCount";
		}else
		{
			Office office=new Office(); 
			office=officeService.get(org);
			pcClusteringMarket.setOrganization(office);
			List<PcClusteringMarket> list = pcClusteringMarketService.findGridCount(pcClusteringMarket);
			model.addAttribute("list", list);
			model.addAttribute("pcClusteringMarket", pcClusteringMarket);
			return "modules/pc/pcClusteringMarketGridCount";
		}
	}
}