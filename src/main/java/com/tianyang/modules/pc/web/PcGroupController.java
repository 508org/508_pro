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
import com.tianyang.modules.pc.entity.PcGroup;
import com.tianyang.modules.pc.entity.PcGroupMobileNumber;
import com.tianyang.modules.pc.service.PcGroupMobileNumberService;
import com.tianyang.modules.pc.service.PcGroupService;
import com.tianyang.modules.sys.entity.Dict;
import com.tianyang.modules.sys.entity.Office;
import com.tianyang.modules.sys.entity.Role;
import com.tianyang.modules.sys.entity.User;
import com.tianyang.modules.sys.service.OfficeService;
import com.tianyang.modules.sys.service.SystemService;
import com.tianyang.modules.sys.utils.DictUtils;
import com.tianyang.modules.sys.utils.UserUtils;

/**
 * 集团信息Controller
 * @author 刘笑林
 * @version 2017-06-08
 */
@Controller
@RequestMapping(value = "${adminPath}/pc/pcGroup")
public class PcGroupController extends BaseController {
	@Autowired
	private PcGroupService pcGroupService;
	@Autowired
	private OfficeService officeService;
	@Autowired
	private PcGroupMobileNumberService pcGroupMobileNumberService;
	@Autowired
	private SystemService systemService;
	
	@ModelAttribute
	public PcGroup get(@RequestParam(required=false) String id) {
		PcGroup entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = pcGroupService.get(id);
		}
		if (entity == null){
			entity = new PcGroup();
		}
		return entity;
	}
	
	@RequiresPermissions("pc:pcGroup:view")
	@RequestMapping(value = {"list", ""})
	public String list(PcGroup pcGroup, HttpServletRequest request, HttpServletResponse response, Model model) {
		
		String incomeType=pcGroup.getIncomeType()==null?"":pcGroup.getIncomeType();
		String operators=pcGroup.getOperators()==null?"":pcGroup.getOperators();
		String allMonthlyIncome=pcGroup.getAllMonthlyIncome()==null?"":pcGroup.getAllMonthlyIncome();
		String allMonthlyIncomeTo=pcGroup.getAllMonthlyIncomeTo()==null?"":pcGroup.getAllMonthlyIncomeTo();
		if(!incomeType.equals("")&&!operators.equals(""))
		{
			if(incomeType.equals("1")&&operators.equals("1")){pcGroup.setPhoneUsageMobileMonthlyIncome(allMonthlyIncome);pcGroup.setPhoneUsageMobileMonthlyIncomeTo(allMonthlyIncomeTo);}
			if(incomeType.equals("1")&&operators.equals("2")){pcGroup.setPhoneUsageUnicomMonthlyIncome(allMonthlyIncome);pcGroup.setPhoneUsageUnicomMonthlyIncomeTo(allMonthlyIncomeTo);}
			if(incomeType.equals("1")&&operators.equals("3")){pcGroup.setPhoneUsageTelecomMonthlyIncome(allMonthlyIncome);pcGroup.setPhoneUsageTelecomMonthlyIncomeTo(allMonthlyIncomeTo);}
			
			if(incomeType.equals("2")&&operators.equals("1")){pcGroup.setDataLineMobileMonthlyIncome(allMonthlyIncome);pcGroup.setDataLineMobileMonthlyIncomeTo(allMonthlyIncomeTo);}
			if(incomeType.equals("2")&&operators.equals("2")){pcGroup.setDataLineUnicomMonthlyIncome(allMonthlyIncome);pcGroup.setDataLineUnicomMonthlyIncomeTo(allMonthlyIncomeTo);}
			if(incomeType.equals("2")&&operators.equals("3")){pcGroup.setDataLineTelecomMonthlyIncome(allMonthlyIncome);pcGroup.setDataLineTelecomMonthlyIncomeTo(allMonthlyIncomeTo);}
			
			if(incomeType.equals("3")&&operators.equals("1")){pcGroup.setInternetLineMobileMonthlyIncome(allMonthlyIncome);pcGroup.setInternetLineMobileMonthlyIncomeTo(allMonthlyIncomeTo);}
			if(incomeType.equals("3")&&operators.equals("2")){pcGroup.setInternetLineUnicomMonthlyIncome(allMonthlyIncome);pcGroup.setInternetLineUnicomMonthlyIncomeTo(allMonthlyIncomeTo);}
			if(incomeType.equals("3")&&operators.equals("3")){pcGroup.setInternetLineTelecomMonthlyIncome(allMonthlyIncome);pcGroup.setInternetLineTelecomMonthlyIncomeTo(allMonthlyIncomeTo);}
			
			if(incomeType.equals("4")&&operators.equals("1")){pcGroup.setVoiceLineMobileMonthlyIncome(allMonthlyIncome);pcGroup.setVoiceLineMobileMonthlyIncomeTo(allMonthlyIncomeTo);}
			if(incomeType.equals("4")&&operators.equals("2")){pcGroup.setVoiceLineUnicomMonthlyIncome(allMonthlyIncome);pcGroup.setVoiceLineUnicomMonthlyIncomeTo(allMonthlyIncomeTo);}
			if(incomeType.equals("4")&&operators.equals("3")){pcGroup.setVoiceLineTelecomMonthlyIncome(allMonthlyIncome);pcGroup.setVoiceLineTelecomMonthlyIncomeTo(allMonthlyIncomeTo);}

			if(incomeType.equals("5")&&operators.equals("1")){pcGroup.setImsMobileMonthlyIncome(allMonthlyIncome);pcGroup.setImsMobileMonthlyIncomeTo(allMonthlyIncomeTo);}
			if(incomeType.equals("5")&&operators.equals("2")){pcGroup.setImsUnicomMonthlyIncome(allMonthlyIncome);pcGroup.setImsUnicomMonthlyIncomeTo(allMonthlyIncomeTo);}
			if(incomeType.equals("5")&&operators.equals("3")){pcGroup.setImsTelecomMonthlyIncome(allMonthlyIncome);pcGroup.setImsTelecomMonthlyIncomeTo(allMonthlyIncomeTo);}
			
			if(incomeType.equals("6")&&operators.equals("1")){pcGroup.setOtherProductsMobileMonthlyIncome(allMonthlyIncome);pcGroup.setOtherProductsMobileMonthlyIncomeTo(allMonthlyIncomeTo);}
			if(incomeType.equals("6")&&operators.equals("2")){pcGroup.setOtherProductsUnicomMonthlyIncome(allMonthlyIncome);pcGroup.setOtherProductsUnicomMonthlyIncomeTo(allMonthlyIncomeTo);}
			if(incomeType.equals("6")&&operators.equals("3")){pcGroup.setOtherProductsTelecomMonthlyIncome(allMonthlyIncome);pcGroup.setOtherProductsTelecomMonthlyIncomeTo(allMonthlyIncomeTo);}
			
			if(incomeType.equals("7")&&operators.equals("1")){pcGroup.setWlwkYdIncome(allMonthlyIncome);pcGroup.setWlwkYdIncomeTo(allMonthlyIncomeTo);}
			if(incomeType.equals("7")&&operators.equals("2")){pcGroup.setWlwkLtIncome(allMonthlyIncome);pcGroup.setWlwkLtIncomeTo(allMonthlyIncomeTo);}
			if(incomeType.equals("7")&&operators.equals("3")){pcGroup.setWlwkDxIncome(allMonthlyIncome);pcGroup.setWlwkDxIncomeTo(allMonthlyIncomeTo);}
			
			if(incomeType.equals("8")&&operators.equals("1")){pcGroup.setiDCYdZf(allMonthlyIncome);pcGroup.setiDCYdZfTo(allMonthlyIncomeTo);}
			if(incomeType.equals("8")&&operators.equals("2")){pcGroup.setiDCLtZf(allMonthlyIncome);pcGroup.setiDCLtZfTo(allMonthlyIncomeTo);}
			if(incomeType.equals("8")&&operators.equals("3")){pcGroup.setiDCQtZf(allMonthlyIncome);pcGroup.setiDCQtZfTo(allMonthlyIncomeTo);}
		  
		}
		
		String org=request.getParameter("org");
		org=org==null?"":org;
		if(org.length()>0&& !org.equals("1"))
		{
			Office office=new Office(); 
			office=officeService.get(org);
			pcGroup.setOrganization(office);
		}
		String grid=request.getParameter("grid");
		grid=grid==null?"":grid;
		if(grid.length()>0)
		{
			Office office=new Office(); 
			office=officeService.get(grid);
			pcGroup.setAttributedGrid(office);
		}
		String manager=request.getParameter("manager");
		manager=manager==null?"":manager;
		if(manager.length()>0)
		{
			User user=new User(); 
			user=systemService.getUser(manager);
			pcGroup.setCustomerManagerNumber(user.getLoginName());
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
			pcGroup.setCustomerManagerNumber(user);
		}*/
		for (Role r : user.getRoleList()){
			if(r.getEnname().equals("qxgly") || r.getEnname().equals("d"))
			{
				pcGroup.setOrganization(user.getOffice()); 
				
			}	
			if(r.getEnname().equals("ptyh"))
			{
				pcGroup.setCustomerManagerNumber(user.getLoginName());
				
			}
		}
		
		String flag=request.getParameter("flag");		
		Page<PcGroup> page = pcGroupService.findPage(new Page<PcGroup>(request, response), pcGroup); 
		model.addAttribute("page", page);
		String display=request.getParameter("display");
		model.addAttribute("display", display);
		model.addAttribute("flag", flag);
		return "modules/pc/pcGroupList";
	}
	//微信 手机
		@ResponseBody
		@RequestMapping(value = {"listApp"})
		public String listApp(PcGroup pcGroup, HttpServletRequest request, HttpServletResponse response, Model model) {
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
					pcGroup.setOrganization(user.getOffice());
					
				}	
				if(r.getEnname().equals("ptyh"))
				{
					pcGroup.setCustomerManagerNumber(user.getLoginName());
					
				}
			}

			Page<PcGroup> page = pcGroupService.findPage(new Page<PcGroup>(request, response), pcGroup); 

			Map<String, Object> map = Maps.newHashMap();

			map.put("listentity", page.getList());
			map.put("count", page.getCount());
			return JsonMapper.getInstance().toJson(map);

		}
	@RequestMapping(value = {"getList"})
	public String testactivity(PcGroup pcGroup, HttpServletRequest request, HttpServletResponse response, Model model) {

		return "modules/pc/pcGroupList";
	}
	@RequiresPermissions("pc:pcGroup:view")
	@RequestMapping(value = "form")
	public String form(PcGroup pcGroup, Model model, HttpServletRequest request) {
		model.addAttribute("pcGroup", pcGroup);
		String display=request.getParameter("display");
		String flag=request.getParameter("flag");
		model.addAttribute("display", display);
		model.addAttribute("flag", flag);
		return "modules/pc/pcGroupForm";
	}
	@RequestMapping(value = "formApp")
	public String formApp(PcGroup pcGroup, Model model, HttpServletRequest request) {
		model.addAttribute("pcGroup", pcGroup);
		return "modules/pc/pcGroupForm";
	}
	@RequiresPermissions("pc:pcGroup:edit")
	@RequestMapping(value = "save")
	public String save(PcGroup pcGroup, Model model, RedirectAttributes redirectAttributes, HttpServletRequest request) {
		if (!beanValidator(model, pcGroup)){
			return form(pcGroup, model,request);
		}
		String display=request.getParameter("display");
		pcGroupService.save(pcGroup);
		addMessage(redirectAttributes, "保存集团信息成功");
		return "redirect:"+Global.getAdminPath()+"/pc/pcGroup/?repage&display="+display;
	}
	@RequestMapping(value = "saveApp")
	public String saveApp(PcGroup pcGroup, Model model, RedirectAttributes redirectAttributes, HttpServletRequest request) {
		if (!beanValidator(model, pcGroup)){
			return form(pcGroup, model,request);
		}
		pcGroupService.save(pcGroup);
		addMessage(redirectAttributes, "保存集团信息成功");
		return "redirect:"+Global.getAdminPath()+"/pc/pcGroup/listApp?repage";
	}
	
	@RequiresPermissions("pc:pcGroup:edit")
	@RequestMapping(value = "delete")
	public String delete(PcGroup pcGroup, RedirectAttributes redirectAttributes, Model model, HttpServletRequest request) {
		pcGroupService.delete(pcGroup);
		String display=request.getParameter("display");
		addMessage(redirectAttributes, "删除集团信息成功");
		return "redirect:"+Global.getAdminPath()+"/pc/pcGroup/?repage&display="+display;
	}
	/**
	 * 导入集团数据
	 * @param file
	 * @param redirectAttributes
	 * @return
	 */
	@RequiresPermissions("pc:pcGroup:import")
    @RequestMapping(value = "import", method=RequestMethod.POST)
    public String importFile(MultipartFile file, RedirectAttributes redirectAttributes) {
		if(Global.isDemoMode()){
			addMessage(redirectAttributes, "演示模式，不允许操作！");
			return "redirect:" + adminPath + "/pc/pcGroup/list?repage";
		}
		//导入ABC类集团信息
		try {
			int successNum = 0;
			int failureNum = 0;
			StringBuilder failureMsg = new StringBuilder();
			ImportExcel ei = new ImportExcel(file, 3, 0);
			List<PcGroup> list = ei.getDataList(PcGroup.class);
			for (PcGroup pcGroup : list){
				try{
					if (StringUtils.isNotBlank(pcGroup.getGroupNumber())) {
						
						if ("true".equals(checkGroupNumber(pcGroup.getGroupNumber()))){
							BeanValidators.validateWithException(validator, pcGroup);
							pcGroupService.save(pcGroup);
							successNum++;
						}else{
							String id=pcGroupService.getGroupByGroupNumber(pcGroup.getGroupNumber()).getId().toString();
							pcGroup.setId(id);
							pcGroupService.save(pcGroup);
							successNum++;
							//failureMsg.append("<br/>集团编号 "+pcGroup.getGroupNumber()+" 已存在; ");
							//failureNum++;
						}
					}
					
				}catch(ConstraintViolationException ex){
					failureMsg.append("<br/>集团编号 "+pcGroup.getGroupNumber()+" 导入失败：");
					List<String> messageList = BeanValidators.extractPropertyAndMessageAsList(ex, ": ");
					for (String message : messageList){
						failureMsg.append(message+"; ");
						failureNum++;
					}
				}catch (Exception ex) {
					failureMsg.append("<br/>集团编号"+pcGroup.getGroupNumber()+" 导入失败："+ex.getMessage());
				}
			}
			if (failureNum>0){
				failureMsg.insert(0, "，失败 "+failureNum+" 条集团信息，导入信息如下：");
			}
			addMessage(redirectAttributes, "已成功导入 "+successNum+" 条集团信息"+failureMsg);
		} catch (Exception e) {
			addMessage(redirectAttributes, "导入集团信息失败！失败信息："+e.getMessage());
		}
		//导入ABC类集团手机号码信息
		try {
			int successNum = 0;
			int failureNum = 0;
			StringBuilder failureMsg = new StringBuilder();
			ImportExcel ei = new ImportExcel(file, 0, 1);
			List<PcGroupMobileNumber> list = ei.getDataList(PcGroupMobileNumber.class);
			for (PcGroupMobileNumber pcGroupMobileNumber : list){
				try{
					
					if ("true".equals(checkMobileNumber(pcGroupMobileNumber.getMobileNumber()))){
						BeanValidators.validateWithException(validator, pcGroupMobileNumber);
						pcGroupMobileNumberService.save(pcGroupMobileNumber);
						successNum++;
					}else{
						//failureMsg.append("<br/>手机号码 "+pcGroupMobileNumber.getMobileNumber()+" 已存在; ");
						failureNum++;
					}
				}catch(ConstraintViolationException ex){
					//failureMsg.append("<br/>手机号码 "+pcGroupMobileNumber.getMobileNumber()+" 导入失败：");
					List<String> messageList = BeanValidators.extractPropertyAndMessageAsList(ex, ": ");
					for (String message : messageList){
						failureMsg.append(message+"; ");
						failureNum++;
					}
				}catch (Exception ex) {
					//failureMsg.append("<br/>手机号码"+pcGroupMobileNumber.getMobileNumber()+" 导入失败："+ex.getMessage());
				}
			}
			if (failureNum>0){
				//failureMsg.insert(0, "，失败 "+failureNum+" 条手机信息，导入信息如下：");
			}
			//addMessage(redirectAttributes, "已成功导入 "+successNum+" 条手机信息"+failureMsg);
		} catch (Exception e) {
			//addMessage(redirectAttributes, "导入手机信息失败！失败信息："+e.getMessage());
		}
		return "redirect:" + adminPath + "/pc/pcGroup/list?repage";
    }
	
	
	/**
	 * 验证集团编号是否有效
	 * @param groupNumber
	 * @return
	 */
	
	@RequestMapping(value = "checkGroupNumber")
	public String checkGroupNumber(String groupNumber) {
		if (StringUtils.isNoneBlank(groupNumber) && pcGroupService.getGroupByGroupNumber(groupNumber) == null) {
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
		if (mobileNumber !=null && pcGroupMobileNumberService.getMobileByMobileNumber(mobileNumber) == null) {
			return "true";
		}
		return "false";
	}
	/**
	 * 统计
	 * @param pcGroup
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "count")
	public String count(PcGroup pcGroup, HttpServletRequest request, HttpServletResponse response, Model model) {
		
		String incomeType=pcGroup.getIncomeType()==null?"":pcGroup.getIncomeType();
		String operators=pcGroup.getOperators()==null?"":pcGroup.getOperators();
		String allMonthlyIncome=pcGroup.getAllMonthlyIncome()==null?"":pcGroup.getAllMonthlyIncome();
		String allMonthlyIncomeTo=pcGroup.getAllMonthlyIncomeTo()==null?"":pcGroup.getAllMonthlyIncomeTo();
		if(!incomeType.equals("")&&!operators.equals(""))
		{
			if(incomeType.equals("1")&&operators.equals("1")){pcGroup.setPhoneUsageMobileMonthlyIncome(allMonthlyIncome);pcGroup.setPhoneUsageMobileMonthlyIncomeTo(allMonthlyIncomeTo);}
			if(incomeType.equals("1")&&operators.equals("2")){pcGroup.setPhoneUsageUnicomMonthlyIncome(allMonthlyIncome);pcGroup.setPhoneUsageUnicomMonthlyIncomeTo(allMonthlyIncomeTo);}
			if(incomeType.equals("1")&&operators.equals("3")){pcGroup.setPhoneUsageTelecomMonthlyIncome(allMonthlyIncome);pcGroup.setPhoneUsageTelecomMonthlyIncomeTo(allMonthlyIncomeTo);}
			
			if(incomeType.equals("2")&&operators.equals("1")){pcGroup.setDataLineMobileMonthlyIncome(allMonthlyIncome);pcGroup.setDataLineMobileMonthlyIncomeTo(allMonthlyIncomeTo);}
			if(incomeType.equals("2")&&operators.equals("2")){pcGroup.setDataLineUnicomMonthlyIncome(allMonthlyIncome);pcGroup.setDataLineUnicomMonthlyIncomeTo(allMonthlyIncomeTo);}
			if(incomeType.equals("2")&&operators.equals("3")){pcGroup.setDataLineTelecomMonthlyIncome(allMonthlyIncome);pcGroup.setDataLineTelecomMonthlyIncomeTo(allMonthlyIncomeTo);}
			
			if(incomeType.equals("3")&&operators.equals("1")){pcGroup.setInternetLineMobileMonthlyIncome(allMonthlyIncome);pcGroup.setInternetLineMobileMonthlyIncomeTo(allMonthlyIncomeTo);}
			if(incomeType.equals("3")&&operators.equals("2")){pcGroup.setInternetLineUnicomMonthlyIncome(allMonthlyIncome);pcGroup.setInternetLineUnicomMonthlyIncomeTo(allMonthlyIncomeTo);}
			if(incomeType.equals("3")&&operators.equals("3")){pcGroup.setInternetLineTelecomMonthlyIncome(allMonthlyIncome);pcGroup.setInternetLineTelecomMonthlyIncomeTo(allMonthlyIncomeTo);}
			
			if(incomeType.equals("4")&&operators.equals("1")){pcGroup.setVoiceLineMobileMonthlyIncome(allMonthlyIncome);pcGroup.setVoiceLineMobileMonthlyIncomeTo(allMonthlyIncomeTo);}
			if(incomeType.equals("4")&&operators.equals("2")){pcGroup.setVoiceLineUnicomMonthlyIncome(allMonthlyIncome);pcGroup.setVoiceLineUnicomMonthlyIncomeTo(allMonthlyIncomeTo);}
			if(incomeType.equals("4")&&operators.equals("3")){pcGroup.setVoiceLineTelecomMonthlyIncome(allMonthlyIncome);pcGroup.setVoiceLineTelecomMonthlyIncomeTo(allMonthlyIncomeTo);}

			if(incomeType.equals("5")&&operators.equals("1")){pcGroup.setImsMobileMonthlyIncome(allMonthlyIncome);pcGroup.setImsMobileMonthlyIncomeTo(allMonthlyIncomeTo);}
			if(incomeType.equals("5")&&operators.equals("2")){pcGroup.setImsUnicomMonthlyIncome(allMonthlyIncome);pcGroup.setImsUnicomMonthlyIncomeTo(allMonthlyIncomeTo);}
			if(incomeType.equals("5")&&operators.equals("3")){pcGroup.setImsTelecomMonthlyIncome(allMonthlyIncome);pcGroup.setImsTelecomMonthlyIncomeTo(allMonthlyIncomeTo);}
			
			if(incomeType.equals("6")&&operators.equals("1")){pcGroup.setOtherProductsMobileMonthlyIncome(allMonthlyIncome);pcGroup.setOtherProductsMobileMonthlyIncomeTo(allMonthlyIncomeTo);}
			if(incomeType.equals("6")&&operators.equals("2")){pcGroup.setOtherProductsUnicomMonthlyIncome(allMonthlyIncome);pcGroup.setOtherProductsUnicomMonthlyIncomeTo(allMonthlyIncomeTo);}
			if(incomeType.equals("6")&&operators.equals("3")){pcGroup.setOtherProductsTelecomMonthlyIncome(allMonthlyIncome);pcGroup.setOtherProductsTelecomMonthlyIncomeTo(allMonthlyIncomeTo);}
		}
		String org=request.getParameter("org");
		org=org==null?"":org;
		String grid=request.getParameter("grid");
		grid=grid==null?"":grid;
		if(org.length()==0&&grid.length()==0)
		{
			List<PcGroup> list = pcGroupService.findOrgCount(pcGroup);
			model.addAttribute("list", list);
			model.addAttribute("pcGroup", pcGroup);
			return "modules/pc/pcGroupOrgCount";
		}else if(grid.length()>0)
		{
			Office office=new Office(); 
			office=officeService.get(grid);
			pcGroup.setAttributedGrid(office);
			List<PcGroup> list = pcGroupService.findManagerCount(pcGroup);
			model.addAttribute("list", list);
			model.addAttribute("pcGroup", pcGroup);
			return "modules/pc/pcGroupManagerCount";
		}else
		{
			Office office=new Office(); 
			office=officeService.get(org);
			pcGroup.setOrganization(office);
			List<PcGroup> list = pcGroupService.findGridCount(pcGroup);
			model.addAttribute("list", list);
			model.addAttribute("pcGroup", pcGroup);
			return "modules/pc/pcGroupGridCount";
		}

	}
}