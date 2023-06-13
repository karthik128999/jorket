package in.co.jk.ctl;

import java.io.IOException;
import java.util.List;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import in.co.jk.dto.RequestServiceDTO;
import in.co.jk.dto.UserDTO;
import in.co.jk.exception.DuplicateRecordException;
import in.co.jk.exception.RecordNotFoundException;
import in.co.jk.form.RequestServiceForm;
import in.co.jk.form.ServicesForm;
import in.co.jk.services.RequestServiceServiceInt;
import in.co.jk.services.ServicesServiceInt;
import in.co.jk.services.UserService;



@Controller
public class RequestServicesCtl extends BaseCtl {

	private Logger log = Logger.getLogger(RequestServicesCtl.class.getName());
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
	
	@GetMapping("/home/login/request-services")
	public String display(@RequestParam(required = false) Long id, Long pId, @ModelAttribute("form") RequestServiceForm form,
			HttpSession session, Model model) {
		UserDTO userDTO = (UserDTO)session.getAttribute("user");
		if(userDTO==null) {
			return "errorPage";
		}
			String skill1 = userDTO.getSkill1();
			String skill2 = userDTO.getSkill2();
			String skill3 = userDTO.getSkill_3();
			String skill4 = userDTO.getSkill_4();
			String skill5 = userDTO.getSkill_5();
			model.addAttribute("skill1", skill1);
			model.addAttribute("skill2", skill2);
			model.addAttribute("skill3", skill3);
			model.addAttribute("skill4", skill4);
			model.addAttribute("skill5", skill5);
			model.addAttribute("skill1", skill1);
		
		if (form.getId() > 0) {
			RequestServiceDTO bean = requestServiceServiceInt.findBypk(id);
			form.populate(bean);
		}
		return "requestservices";
	}

	@PostMapping("/home/login/request-services")
	public String submit(@Valid @ModelAttribute("form") RequestServiceForm form, BindingResult bindingResult,
			HttpSession session, Model model,HttpServletRequest request) {
		UserDTO userDTO = (UserDTO)session.getAttribute("user");
		String skill1 = userDTO.getSkill1();
		String skill2 = userDTO.getSkill2();
		String skill3 = userDTO.getSkill_3();
		String skill4 = userDTO.getSkill_4();
		String skill5 = userDTO.getSkill_5();
		model.addAttribute("skill1", skill1);
		model.addAttribute("skill2", skill2);
		model.addAttribute("skill3", skill3);
		model.addAttribute("skill4", skill4);
		model.addAttribute("skill5", skill5);
		model.addAttribute("skill1", skill1);
		
		if (OP_RESET.equalsIgnoreCase(form.getOperation())) {
			
			return "redirect:/home/login/request-services";
		}
		
		try {
			if (OP_SAVE.equalsIgnoreCase(form.getOperation())) {

				if (bindingResult.hasErrors()) {
					System.out.println(bindingResult.getAllErrors());
					return "requestservices";
				}
				
				if(userDTO==null) {
					return "errorPage";
				}
					
				RequestServiceDTO bean = (RequestServiceDTO) populateDTO(form.getDTO(),request);
			//	bean.setImage(form.getImage().getBytes());
				System.out.println("service----- "+bean.getServiceName());
				bean.setCity(userDTO.getCity());
				//bean.setService(service.findBypk(bean.getServiceId()));
				bean.setUserId(userDTO.getId());
				bean.setUser(userService.findBypk(userDTO.getId()));
				if (bean.getId() > 0) {
					requestServiceServiceInt.update(bean);
					model.addAttribute("success", "Service Assign update Successfully!!!!");
				} else {
					requestServiceServiceInt.add(bean);
					model.addAttribute("success", "Service Assign Successfully!!!!");
				}
				return "requestservices";
			}
		} catch (DuplicateRecordException e) {
			model.addAttribute("error", e.getMessage());
			return "requestservices";
		}
		catch (NullPointerException e) {
			
			model.addAttribute("error", "Please Login Once again! Some error occured");
			return "errorPage";
		}
		return "";
	}

	@RequestMapping(value = "/home/login/request-services/search", method = { RequestMethod.GET, RequestMethod.POST })
	public String searchList(@ModelAttribute("form") RequestServiceForm form,
			@RequestParam(required = false) String operation, Long vid, HttpSession session, Model model) {

		if (OP_RESET.equalsIgnoreCase(operation)) {
			return "redirect:/home/login/request-services/search";
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
				for (long id : form.getIds()) {
					RequestServiceDTO dto = new RequestServiceDTO();
					dto.setId(id);
					requestServiceServiceInt.delete(dto);
				}
				model.addAttribute("success", "Deleted Successfully!!!");
			} else {
				model.addAttribute("error", "Select at least one record");
			}
		}
		RequestServiceDTO dto = (RequestServiceDTO) form.getDTO();
		
		List<RequestServiceDTO> list = requestServiceServiceInt.searchRequestServiceList(dto, pageNo, pageSize);
		List<RequestServiceDTO> totallist = requestServiceServiceInt.searchRequestServiceList(dto);
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
		return "requestservicesList";
	}
	
	@GetMapping("/profile/image")
	 public void showImage(@Param("id") Long id, HttpServletResponse response, RequestServiceDTO v)
	   throws ServletException, IOException, RecordNotFoundException {
		v = requestServiceServiceInt.findBypk(id);
		response.setContentType("image/jpeg, image/jpg, image/png, image/gif");
		response.getOutputStream().write(v.getImage());
		response.getOutputStream().close();
	 }
}
