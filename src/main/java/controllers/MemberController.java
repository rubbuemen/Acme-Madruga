/*
 * AdministratorController.java
 * 
 * Copyright (C) 2019 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the
 * TDG Licence, a copy of which you may download from
 * http://www.tdg-seville.info/License.html
 */

package controllers;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.BrotherhoodService;
import services.MemberService;
import domain.Member;

@Controller
@RequestMapping("/member")
public class MemberController extends AbstractController {

	@Autowired
	MemberService		memberService;

	@Autowired
	BrotherhoodService	brotherhoodService;


	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView listMembersByBrotherhood(@RequestParam final int brotherhoodId) {
		ModelAndView result;
		Collection<Member> members;

		members = this.memberService.findMembersByBrotherhoodId(brotherhoodId);

		result = new ModelAndView("member/list");

		result.addObject("members", members);
		result.addObject("requestURI", "members/list.do");

		return result;
	}

	@RequestMapping(value = "/show", method = RequestMethod.GET)
	public ModelAndView showMember(@RequestParam final int memberId) {
		ModelAndView result;
		Member actor;

		actor = this.memberService.findOne(memberId);

		result = new ModelAndView("actor/show");

		result.addObject("actor", actor);

		return result;
	}

}
