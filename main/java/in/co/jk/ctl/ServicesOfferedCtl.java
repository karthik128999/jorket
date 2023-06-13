package in.co.jk.ctl;

import java.util.Iterator;
import java.util.List;
import java.util.logging.Logger;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import in.co.jk.dto.RequestServiceDTO;
import in.co.jk.dto.ServicesDTO;
import in.co.jk.dto.UserDTO;
import in.co.jk.form.RequestServiceForm;
import in.co.jk.form.ServicesForm;
import in.co.jk.services.RequestServiceServiceInt;
import in.co.jk.services.ServicesServiceInt;
import in.co.jk.services.UserService;

@Controller
public class ServicesOfferedCtl extends BaseCtl {

	private static final String OP_SEARCH = "Search";
	private static final String OP_BOOK = "Book";
	private Logger log = Logger.getLogger(ServicesOfferedCtl.class.getName());
	@Autowired
	private ServicesServiceInt service;

	@Autowired
	private UserService userService;

	@Autowired
	private RequestServiceServiceInt requestServiceServiceInt;

	@ModelAttribute
	public void preload(Model model) {
		UserDTO dto = new UserDTO();
		dto.setRoleId(2L);
		model.addAttribute("employeeList", userService.getAllEmployees(dto));
		//model.addAttribute("servicesList", service.search(null));
	}

	@RequestMapping(value = "/home/login/services-offered/search", method = { RequestMethod.GET, RequestMethod.POST })
	public String searchList(@ModelAttribute("form") ServicesForm form,
			@RequestParam(required = false) String operation, Long vid, HttpSession session, Model model) {

		if (OP_RESET.equalsIgnoreCase(operation)) {
			return "redirect:/home/login/services-offered/search";
		}
		System.out.println("city: "+form.getCity());
		int pageNo = form.getPageNo();
		int pageSize = form.getPageSize();

		if (OP_NEXT.equals(operation)) {
			pageNo++;
		} else if (OP_PREVIOUS.equals(operation)) {
			pageNo--;
		}
		pageNo = (pageNo < 1) ? 1 : pageNo;
		pageSize = (pageSize < 1) ? 8 : pageSize;

		if (OP_DELETE.equals(operation)) {
			pageNo = 1;
			if (form.getIds() != null) {
				for (long id : form.getIds()) {
					ServicesDTO dto = new ServicesDTO();
					dto.setId(id);
					service.delete(dto);
				}
				model.addAttribute("success", "Deleted Successfully!!!");
			} else {
				model.addAttribute("error", "Select at least one record");
			}
		}
		ServicesDTO dto = (ServicesDTO) form.getDTO();
		if(form.getCity()==null) {
		UserDTO loggedInUser = (UserDTO)session.getAttribute("user");
		dto.setCity(loggedInUser.getCity());
		}
		List<ServicesDTO> list = service.search(dto, pageNo, pageSize);
		List<ServicesDTO> totallist = service.search(dto);
		model.addAttribute("list", list);

		if (list.size() == 0 && !OP_DELETE.equalsIgnoreCase(operation)) {
			model.addAttribute("error", "Record not found");
		}

		int listsize = list.size();
		int total = totallist.size();
		int pageNoPageSize = pageNo * pageSize;

		form.setPageNo(pageNo);
		form.setPageSize(pageSize);
		model.addAttribute("pageNo", pageNo);
		model.addAttribute("pageSize", pageSize);
		model.addAttribute("listsize", listsize);
		model.addAttribute("total", total);
		model.addAttribute("pagenosize", pageNoPageSize);
		model.addAttribute("form", form);
		return "servicesOffered";
	}

	@GetMapping("/home/login/view-profiles")
	public String display(@RequestParam(required = false) Long id,
			@RequestParam(required = false) String serviceName,
			@RequestParam(required = false) String city,Long pId,
			@ModelAttribute("form") RequestServiceForm form, HttpSession session, Model model) {
		System.out.println("Id from url: "+id); /// ---3
		try {
			RequestServiceDTO bean = null;
			ServicesDTO dtoServicesDTO = service.findBypk(id);
			Long serviceIdFromDto = dtoServicesDTO.getId();
			System.out.println("name: "+serviceName+" city: "+city);
			/*
			 * if (form.getId() > 0) {
			 * System.out.println("sss "+requestServiceServiceInt.findByServiceId(
			 * serviceIdFromDto)); bean =
			 * requestServiceServiceInt.findByServiceId(serviceIdFromDto);
			 * form.populate(bean); }
			 */
			//System.out.println("hereeeeeeeeee"+bean);
			RequestServiceDTO dto = (RequestServiceDTO) form.getDTO();
			dto.setServiceName(serviceName);
			dto.setCity(city);
			
			//dto.setServiceId((Long) session.getAttribute("serviceId"));
			List<RequestServiceDTO> list = requestServiceServiceInt.search(dto);
			List<RequestServiceDTO> totallist = requestServiceServiceInt.search(dto);
			model.addAttribute("list", list);
			System.out.println("hereeeeeeeeee2222:::"+list.size()+" total:: "+totallist);
			session.setAttribute("serviceId", id);
		}catch (NullPointerException e) {
			model.addAttribute("error", "No record found");
			return "viewProfileList";
		}
		
		return "viewProfileList";
	}

	@GetMapping("/home/login/view-employee-profile")
	public String displayEachProfile(@RequestParam(required = false) Long id, Long pId,
			@ModelAttribute("form") RequestServiceForm form, HttpSession session, Model model) {
		if (form.getId() > 0) {
			RequestServiceDTO bean = requestServiceServiceInt.findBypk(id);
			form.populate(bean);
		}
		
		return "viewEmployeeProfile";
	}

	@RequestMapping(value = "/home/login/view-profile/search", method = { RequestMethod.POST })
	public String searchProfileList(@ModelAttribute("form") RequestServiceForm form,
			@RequestParam(required = false) String operation, Long vid, HttpSession session, Model model) {
		System.out.println("Form.get " + form.getUserId());

		// List<RequestServiceDTO> list = null;
//		if(OP_SEARCH.equalsIgnoreCase(operation)  && form.getUserId()!=null) {
//			
//			Long id  = (Long)session.getAttribute("serviceId");
//			list = requestServiceServiceInt.findAllRequestAssignedByServiceId(id, form.getUserId());
//			System.out.println("Size of the list is :"+list.size());
//			Iterator<RequestServiceDTO> itr = list.iterator();
//			RequestServiceDTO bean = null;
//			while(itr.hasNext()) {
//				bean = (RequestServiceDTO)itr.next();
//			}
//			System.out.println("ndijkc "+bean.getId());
//			model.addAttribute("list",list);
//			return "viewProfileList";
//		}

		if (OP_RESET.equalsIgnoreCase(operation)) {
			return "redirect:/home/login/view-profile/search";
		}

		int pageNo = form.getPageNo();
		int pageSize = form.getPageSize();

		if (OP_NEXT.equals(operation)) {
			pageNo++;
		} else if (OP_PREVIOUS.equals(operation)) {
			pageNo--;
		}
		pageNo = (pageNo < 1) ? 1 : pageNo;
		pageSize = (pageSize < 1) ? 8 : pageSize;

		if (OP_DELETE.equals(operation)) {
			pageNo = 1;
			if (form.getIds() != null) {
				for (long did : form.getIds()) {
					RequestServiceDTO dto = new RequestServiceDTO();
					dto.setId(did);
					requestServiceServiceInt.delete(dto);
				}
				model.addAttribute("success", "Deleted Successfully!!!");
			} else {
				model.addAttribute("error", "Select at least one record");
			}
		}
		RequestServiceDTO dto = (RequestServiceDTO) form.getDTO();
		//dto.setServiceId((Long) session.getAttribute("serviceId"));
		List<RequestServiceDTO> list = requestServiceServiceInt.search(dto, pageNo, pageSize);
		List<RequestServiceDTO> totallist = requestServiceServiceInt.search(dto);
		model.addAttribute("list", list);

		if (!OP_DELETE.equalsIgnoreCase(operation) && list == null) {
			model.addAttribute("error", "Record not found");
		}

//		int listsize = list.size();
//		//int total = totallist.size();
//		int pageNoPageSize = pageNo * pageSize;

		form.setPageNo(pageNo);
		form.setPageSize(pageSize);
//		model.addAttribute("pageNo", pageNo);
//		model.addAttribute("pageSize", pageSize);
//		model.addAttribute("listsize", listsize);
		// model.addAttribute("total", total);
		// model.addAttribute("pagenosize", pageNoPageSize);
		model.addAttribute("form", form);
		return "viewProfileList";
	}
}
