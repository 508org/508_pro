/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/tianyang/tianyang">tianyang</a> All rights reserved.
 */
package com.tianyang.modules.files.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.tianyang.common.config.Global;
import com.tianyang.common.persistence.Page;
import com.tianyang.common.web.BaseController;
import com.tianyang.common.utils.StringUtils;
import com.tianyang.modules.files.entity.Files;
import com.tianyang.modules.files.service.FilesService;
import com.tianyang.modules.member.entity.Member;

/**
 * 上传文件Controller
 * @author zbc
 * @version 2017-03-06
 */
@Controller
@RequestMapping(value = "${adminPath}/files/files")
public class FilesController extends BaseController {

	@Autowired
	private FilesService filesService;
	
	@ModelAttribute
	public Files get(@RequestParam(required=false) String id) {
		Files entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = filesService.get(id);
		}
		if (entity == null){
			entity = new Files();
		}
		return entity;
	}
	
	@RequiresPermissions("files:files:view")
	@RequestMapping(value = {"list", ""})
	public String list(Files files, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<Files> page = filesService.findPage(new Page<Files>(request, response), files); 
		model.addAttribute("page", page);
		return "modules/files/filesList";
	}
	
	@RequiresPermissions("files:files:view")
	@RequestMapping(value = {"listjson", ""})
	public String listjson(Files files, HttpServletRequest request, HttpServletResponse response, Model model) {
		//Page<Member> page = filesService.findPage(new Page<Member>(request, response), member); 
		//model.addAttribute("page", page);
		List<Files> list = filesService.findList(files);
		
		return renderString(response, list);
	}

	@RequiresPermissions("files:files:view")
	@RequestMapping(value = "form")
	public String form(Files files, Model model) {
		model.addAttribute("files", files);
		return "modules/files/filesForm";
	}

	@RequiresPermissions("files:files:edit")
	@RequestMapping(value = "save")
	public String save(Files files, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, files)){
			return form(files, model);
		}
		filesService.save(files);
		addMessage(redirectAttributes, "保存文件成功");
		return "redirect:"+Global.getAdminPath()+"/files/files/?repage";
	}
	
	@RequiresPermissions("files:files:edit")
	@RequestMapping(value = "delete")
	public String delete(Files files, RedirectAttributes redirectAttributes) {
		filesService.delete(files);
		addMessage(redirectAttributes, "删除文件成功");
		return "redirect:"+Global.getAdminPath()+"/files/files/?repage";
	}

}