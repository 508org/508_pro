/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/tianyang/tianyang">tianyang</a> All rights reserved.
 */
package com.tianyang.modules.member.web;

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
import com.tianyang.modules.member.entity.Member;
import com.tianyang.modules.member.service.MemberService;

/**
 * 关注会员信息Controller
 * @author zbc
 * @version 2017-03-06
 */
@Controller
@RequestMapping(value = "${adminPath}/member/member")
public class MemberController extends BaseController {

	@Autowired
	private MemberService memberService;
	
	@ModelAttribute
	public Member get(@RequestParam(required=false) String id) {
		Member entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = memberService.get(id);
		}
		if (entity == null){
			entity = new Member();
		}
		return entity;
	}
	
	@RequiresPermissions("member:member:view")
	@RequestMapping(value = {"list", ""})
	public String list(Member member, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<Member> page = memberService.findPage(new Page<Member>(request, response), member); 
		model.addAttribute("page", page);
		return "modules/member/memberList";
	}
	
/*	@RequiresPermissions("member:member:view")
	@RequestMapping(value = {"listjson", ""})
	public String listjson(Member member, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<Member> page = memberService.findPage(new Page<Member>(request, response), member); 
		model.addAttribute("page", page);
		List<Member> list = memberService.findList(member);
		
		return renderString(response, list);
		//return "modules/member/memberList";
	}*/

	@RequiresPermissions("member:member:view")
	@RequestMapping(value = "form")
	public String form(Member member, Model model) {
		model.addAttribute("member", member);
		return "modules/member/memberForm";
	}

	@RequiresPermissions("member:member:edit")
	@RequestMapping(value = "save")
	public String save(Member member, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, member)){
			return form(member, model);
		}
		memberService.save(member);
		addMessage(redirectAttributes, "保存会员成功");
		return "redirect:"+Global.getAdminPath()+"/member/member/?repage";
	}
	
	@RequiresPermissions("member:member:edit")
	@RequestMapping(value = "delete")
	public String delete(Member member, RedirectAttributes redirectAttributes) {
		memberService.delete(member);
		addMessage(redirectAttributes, "删除会员成功");
		return "redirect:"+Global.getAdminPath()+"/member/member/?repage";
	}

}