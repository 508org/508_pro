/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/tianyang/tianyang">tianyang</a> All rights reserved.
 */
package com.tianyang.modules.pc.web;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.tianyang.common.persistence.Page;
import com.tianyang.common.web.BaseController;
import com.tianyang.common.utils.DateUtils;
import com.tianyang.common.utils.IdGen;
import com.tianyang.common.utils.StringUtils;
import com.tianyang.modules.pc.entity.SuccessCase;
import com.tianyang.modules.pc.entity.SuccessCaseRecord;
import com.tianyang.modules.pc.service.SuccessCaseService;
import com.tianyang.modules.sys.utils.UserUtils;

/**
 * 案例Controller
 * @author tianyang
 * @version 2014-05-16
 */
@Controller
@RequestMapping(value = "${adminPath}/pc/successCase")
public class SuccessCaseController extends BaseController {

	@Autowired
	private SuccessCaseService successCaseService;
	
	@ModelAttribute
	public SuccessCase get(@RequestParam(required=false) String id) {
		SuccessCase entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = successCaseService.get(id);
			if (StringUtils.equals(entity.getCreateBy().getId(), UserUtils.getUser().getId())) {
				entity.setSelf(true);//表示这条数据是当前人创建的
			}
		}
		if (entity == null){
			entity = new SuccessCase();
		}
		return entity;
	}
	
	@RequiresPermissions("pc:successCase:view")
	@RequestMapping(value = {"list", ""})
	public String list(SuccessCase successCase, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<SuccessCase> page = successCaseService.find(new Page<SuccessCase>(request, response), successCase);
		model.addAttribute("page", page);
		return "modules/pc/successCaseList";
	}

	@RequiresPermissions("pc:successCase:view")
	@RequestMapping(value = "form")
	public String form(SuccessCase successCase, Model model,RedirectAttributes redirectAttributes) {
		
		
		if (StringUtils.isNotBlank(successCase.getId())){
			
			successCase =  saveSuccessCaseRecord(successCase,"show");//查看时，添加新查看记录,并且查询RecordList
		}
		
		model.addAttribute("successCase", successCase);
		return "modules/pc/successCaseForm";
	}

	@RequiresPermissions("pc:successCase:edit")
	@RequestMapping(value = "save")
	public String save(SuccessCase successCase, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, successCase)){
			return form(successCase, model, redirectAttributes);
		}
		// 如果是修改，则状态为已发布，则不能再进行操作
		if (StringUtils.isNotBlank(successCase.getId())){
			SuccessCase e = successCaseService.get(successCase.getId());
			if ("1".equals(e.getStatus())){
				addMessage(redirectAttributes, "已发布，不能操作！");
				return "redirect:" + adminPath + "/pc/successCase/form?id="+successCase.getId();
			}
		}
		
		if (StringUtils.isBlank(successCase.getStatus())) {//当状态没值时  给一个默认值
			successCase.setStatus("0");
		}
		successCaseService.save(successCase);
		addMessage(redirectAttributes, "保存案例'" + successCase.getTitle() + "'成功");
		return "redirect:" + adminPath + "/pc/successCase/?repage";
	}
	/**
	 * 保存点赞，评论
	 * @param successCase
	 * @param model
	 * @param redirectAttributes
	 * @return
	 */
	@RequestMapping(value = "saveRecord")
	public String saveRecord(SuccessCase successCase, Model model, RedirectAttributes redirectAttributes) {
		//从页面传上来的likeflag  1,点赞 2取消赞  3评论
		successCase = saveSuccessCaseRecord(successCase,successCase.getLikeFlag());
		addMessage(redirectAttributes, "操作案例'" + successCase.getTitle() + "'成功");
		return "redirect:" + adminPath + "/pc/successCase/list?repage";
	}
	
	//查看记录，点赞，评论 的插入操作
	public SuccessCase saveSuccessCaseRecord(SuccessCase successCase,String operate) {
		
		SuccessCaseRecord successCaseRecord = null;
		
		boolean isExit = false;
		//判断 记录表里 有没有已存在的记录
		successCaseService.getRecordList(successCase);  
		for (int i = 0; i < successCase.getSuccessCaseRecordList().size(); i++) {
			
			successCaseRecord = successCase.getSuccessCaseRecordList().get(i);
			if (StringUtils.equals(successCaseRecord.getUser().getId(), UserUtils.getUser().getId())) {
				isExit = true;
				successCase.setLikeFlag(successCaseRecord.getLikeFlag());//用来区别页面上 点赞和取消赞
				break;
			}
		}
		
		if (!isExit) {//如果为新记录
			
			successCaseRecord = new SuccessCaseRecord(successCase);
			successCaseRecord.setId(IdGen.uuid());
			successCaseRecord.setIsNewRecord(true);         //设置新记录
			successCaseRecord.setUser(UserUtils.getUser()); //查看人
			successCaseRecord.setReadFlag("1");             //已查看
			successCaseRecord.setReadDate(new Date());      //查看时间
		}
		
		
		if (StringUtils.equals("show", operate)) {//查看
			
			if (!isExit && !StringUtils.equals(successCase.getCreateBy().getId(), UserUtils.getUser().getId())) {//当没有查看记录时，则添加一条查看记录,并且记录中不能添加自己
				successCaseService.saveSuccessCaseRecord(successCaseRecord);
				successCase.getSuccessCaseRecordList().add(successCaseRecord);//当插入完成后，把数据写入list中 供页面显示
			}
			
		} else if(StringUtils.equals("1", operate) || StringUtils.equals("2", operate)) {//点赞，//取消赞
			
//			if (StringUtils.equals(operate, "1")) {
//				
//				successCaseRecord.setLikeFlag("已点赞");//点赞
//				
//			} else if(StringUtils.equals(operate, "2")){
//				
//				successCaseRecord.setLikeFlag("取消赞");//取消赞
//				
//			}
			successCaseRecord.setLikeFlag(operate);//点赞
			successCaseRecord.setComment(null);//目的是在update时  不更新这个字段 只更新点赞的
			successCaseService.saveSuccessCaseRecord(successCaseRecord);
			
		} else if(StringUtils.equals("3", operate)) {//评论
			
			successCaseRecord.setLikeFlag(null);//目的是在update时  不更新这个字段 只更新评论的
			successCaseRecord.setComment(successCase.getComment());
			successCaseService.saveSuccessCaseRecord(successCaseRecord);
		}
		
		
		return successCase;
	}
	
	@RequiresPermissions("pc:successCase:edit")
	@RequestMapping(value = "delete")
	public String delete(SuccessCase successCase, RedirectAttributes redirectAttributes) {
		successCaseService.delete(successCase);
		addMessage(redirectAttributes, "删除案例成功");
		return "redirect:" + adminPath + "/pc/successCase/?repage";
	}
	
	/**
	 * 我的案例列表
	 */
	@RequestMapping(value = "self")
	public String selfList(SuccessCase successCase, HttpServletRequest request, HttpServletResponse response, Model model) {
		successCase.setSelf(true);
		Page<SuccessCase> page = successCaseService.find(new Page<SuccessCase>(request, response), successCase); 
		model.addAttribute("page", page);
		return "modules/pc/oaNotifyList";
	}

	/**
	 * 我的案例列表-数据
	 */
	@RequiresPermissions("pc:successCase:view")
	@RequestMapping(value = "selfData")
	@ResponseBody
	public Page<SuccessCase> listData(SuccessCase successCase, HttpServletRequest request, HttpServletResponse response, Model model) {
		successCase.setSelf(true);
		Page<SuccessCase> page = successCaseService.find(new Page<SuccessCase>(request, response), successCase);
		return page;
	}
	
	/**
	 * 查看我的案例
	 */
	@RequestMapping(value = "view")
	public String view(SuccessCase successCase, Model model) {
		if (StringUtils.isNotBlank(successCase.getId())){
			successCaseService.updateReadFlag(successCase);
			successCase = successCaseService.getRecordList(successCase);
			model.addAttribute("successCase", successCase);
			return "modules/pc/oaNotifyForm";
		}
		return "redirect:" + adminPath + "/pc/successCase/self?repage";
	}

	/**
	 * 查看我的案例-数据
	 */
	@RequestMapping(value = "viewData")
	@ResponseBody
	public SuccessCase viewData(SuccessCase successCase, Model model) {
		if (StringUtils.isNotBlank(successCase.getId())){
			successCaseService.updateReadFlag(successCase);
			return successCase;
		}
		return null;
	}
	
	/**
	 * 查看我的案例-发送记录
	 */
	@RequestMapping(value = "viewRecordData")
	@ResponseBody
	public SuccessCase viewRecordData(SuccessCase successCase, Model model) {
		if (StringUtils.isNotBlank(successCase.getId())){
			successCase = successCaseService.getRecordList(successCase);
			return successCase;
		}
		return null;
	}
	
	/**
	 * 获取我的案例数目
	 */
	@RequestMapping(value = "self/count")
	@ResponseBody
	public String selfCount(SuccessCase successCase, Model model) {
		successCase.setSelf(true);
		successCase.setReadFlag("0");
		return String.valueOf(successCaseService.findCount(successCase));
	}
}