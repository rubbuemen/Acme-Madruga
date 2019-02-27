/*
 * AdministratorController.java
 * 
 * Copyright (C) 2019 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the
 * TDG Licence, a copy of which you may download from
 * http://www.tdg-seville.info/License.html
 */

package controllers.member;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.MemberService;
import services.ProcessionService;
import services.RequestMarchService;
import controllers.AbstractController;
import domain.Procession;
import domain.RequestMarch;

@Controller
@RequestMapping("/requestMarch/member")
public class MemberRequestMarchController extends AbstractController {

	@Autowired
	RequestMarchService	requestMarchService;

	@Autowired
	MemberService		memberService;

	@Autowired
	ProcessionService	processionService;


	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list(@RequestParam final int processionId) {
		ModelAndView result;
		Collection<RequestMarch> requestsMarch;
		boolean hasPendingOrApprovedRequests;

		requestsMarch = this.requestMarchService.findRequestsMarchByProcessionMember(processionId);
		hasPendingOrApprovedRequests = this.requestMarchService.memberHasPendingOrApprovedRequestToProcession(processionId);

		result = new ModelAndView("requestMarch/list");

		result.addObject("requestsMarch", requestsMarch);
		result.addObject("processionId", processionId);
		result.addObject("hasPendingOrApprovedRequests", hasPendingOrApprovedRequests);
		result.addObject("requestURI", "requestMarch/member/list.do");

		return result;
	}

	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	public ModelAndView remove(@RequestParam final int processionId, @RequestParam final int requestMarchId) {
		ModelAndView result;
		Collection<RequestMarch> requestsMarch;
		boolean hasPendingOrApprovedRequests;

		final RequestMarch requestMarch = this.requestMarchService.findOne(requestMarchId);
		this.requestMarchService.delete(requestMarch);

		requestsMarch = this.requestMarchService.findRequestsMarchByProcessionMember(processionId);
		hasPendingOrApprovedRequests = this.requestMarchService.memberHasPendingOrApprovedRequestToProcession(processionId);

		result = new ModelAndView("redirect:/requestMarch/member/list.do");

		result.addObject("requestsMarch", requestsMarch);
		result.addObject("processionId", processionId);
		result.addObject("hasPendingOrApprovedRequests", hasPendingOrApprovedRequests);
		result.addObject("requestURI", "requestMarch/member/list.do");

		return result;
	}

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create(@RequestParam final int processionId) {
		ModelAndView result;
		Collection<RequestMarch> requestsMarch;
		boolean hasPendingOrApprovedRequests;

		RequestMarch requestMarch = this.requestMarchService.create();

		final Procession procession = this.processionService.findOne(processionId);
		requestMarch = this.requestMarchService.save(requestMarch, procession);

		final Collection<RequestMarch> requestsMarchProcession = procession.getRequestsMarch();
		requestsMarchProcession.add(requestMarch);
		procession.setRequestsMarch(requestsMarchProcession);
		this.processionService.saveForRequestMarch(procession);

		final Collection<RequestMarch> requestsMarchMember = requestMarch.getMember().getRequestsMarch();
		requestsMarchMember.add(requestMarch);
		requestMarch.getMember().setRequestsMarch(requestsMarchMember);
		this.memberService.save(requestMarch.getMember());

		requestsMarch = this.requestMarchService.findRequestsMarchByProcessionMember(processionId);
		hasPendingOrApprovedRequests = this.requestMarchService.memberHasPendingOrApprovedRequestToProcession(processionId);

		result = new ModelAndView("redirect:/requestMarch/member/list.do");

		result.addObject("requestsMarch", requestsMarch);
		result.addObject("processionId", processionId);
		result.addObject("hasPendingOrApprovedRequests", hasPendingOrApprovedRequests);
		result.addObject("requestURI", "requestMarch/member/list.do");

		return result;
	}

	//	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	//	public ModelAndView createAndEdit(@RequestParam final int requestMarchId) {
	//		ModelAndView result;
	//
	//		RequestMarch requestMarch = null;
	//		try {
	//			requestMarch = this.requestMarchService.findRequestMarchPenddingBrotherhoodLogged(requestMarchId);
	//			result = this.createEditModelAndView(requestMarch, decision);
	//
	//		} catch (final Throwable oops) {
	//			if (oops.getMessage().equals("The logged actor is not the owner of this entity"))
	//				result = this.createEditModelAndView(requestMarch, "hacking.logged.error", decision);
	//			else
	//				result = this.createEditModelAndView(requestMarch, "commit.error", decision);
	//		}
	//
	//		return result;
	//	}
	//	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	//	public ModelAndView createOrEdit(RequestMarch requestMarch, final BindingResult binding) {
	//		ModelAndView result;
	//
	//		try {
	//			requestMarch = this.requestMarchService.reconstruct(requestMarch, binding);
	//			if (binding.hasErrors())
	//				result = this.createEditModelAndView(requestMarch, requestMarch.getStatus());
	//			else {
	//				this.requestMarchService.save(requestMarch);
	//				result = new ModelAndView("redirect:/procession/brotherhood/list.do");
	//			}
	//		} catch (final Throwable oops) {
	//			if (oops.getMessage().equals("The brotherhood must provide an explanation about the request march rejected"))
	//				result = this.createEditModelAndView(requestMarch, "requestMarch.error.rejectReason", requestMarch.getStatus());
	//			else if (oops.getMessage().equals("You must select a row position"))
	//				result = this.createEditModelAndView(requestMarch, "requestMarch.error.selectRow", requestMarch.getStatus());
	//			else if (oops.getMessage().equals("You must select a column position"))
	//				result = this.createEditModelAndView(requestMarch, "requestMarch.error.selectColumn", requestMarch.getStatus());
	//			else if (oops.getMessage().equals("You have exceeded the maximum number of rows established"))
	//				result = this.createEditModelAndView(requestMarch, "requestMarch.error.exceededRow", requestMarch.getStatus());
	//			else if (oops.getMessage().equals("You have exceeded the maximum number of columns established"))
	//				result = this.createEditModelAndView(requestMarch, "requestMarch.error.exceededColumn", requestMarch.getStatus());
	//			else if (oops.getMessage().equals("Two members can not march at the same row/column"))
	//				result = this.createEditModelAndView(requestMarch, "requestMarch.error.repeatedRowColumn", requestMarch.getStatus());
	//			else
	//				result = this.createEditModelAndView(requestMarch, "commit.error", requestMarch.getStatus());
	//		}
	//
	//		return result;
	//	}
	//	// Ancillary methods
	//	protected ModelAndView createEditModelAndView(final RequestMarch requestMarch, final String decision) {
	//		ModelAndView result;
	//		result = this.createEditModelAndView(requestMarch, null, decision);
	//		return result;
	//	}
	//
	//	protected ModelAndView createEditModelAndView(final RequestMarch requestMarch, final String message, final String decision) {
	//		ModelAndView result;
	//
	//		if (requestMarch == null)
	//			result = new ModelAndView("redirect:/welcome/index.do");
	//		else {
	//			result = new ModelAndView("requestMarch/edit");
	//			if (decision.equals("APPROVED")) {
	//				final Map<Integer, Integer> suggestedPosition = this.systemConfigurationService.suggestedRowColumn(requestMarch.getId());
	//				result.addObject("rowSuggested", suggestedPosition.keySet().iterator().next());
	//				result.addObject("columnSuggested", suggestedPosition.values().iterator().next());
	//			}
	//		}
	//
	//		result.addObject("decision", decision);
	//		result.addObject("requestMarch", requestMarch);
	//		result.addObject("actionURL", "requestMarch/brotherhood/edit.do");
	//		result.addObject("message", message);
	//
	//		return result;
	//	}

}
