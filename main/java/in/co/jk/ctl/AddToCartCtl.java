package in.co.jk.ctl;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.apache.taglibs.standard.tag.common.core.NullAttributeException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import in.co.jk.dto.AddToCartDTO;
import in.co.jk.dto.FeedbackDTO;
import in.co.jk.dto.RequestServiceDTO;
import in.co.jk.dto.ServicesDTO;
import in.co.jk.dto.UserDTO;
import in.co.jk.exception.DuplicateRecordException;
import in.co.jk.form.AddToCartForm;
import in.co.jk.form.RequestServiceForm;
import in.co.jk.form.ServicesForm;
import in.co.jk.services.AddToCartServiceInt;
import in.co.jk.services.FeedbackServiceInt;
import in.co.jk.services.RequestServiceServiceInt;
import in.co.jk.services.ServicesServiceInt;
import in.co.jk.services.UserService;
import in.co.jk.util.DataUtility;

@Controller
public class AddToCartCtl extends BaseCtl {

	private static final String OP_BOOK = "Book";
	private Logger log = Logger.getLogger(AddToCartCtl.class.getName());
	@Autowired
	private ServicesServiceInt service;

	@Autowired
	private UserService userService;

	@Autowired
	private RequestServiceServiceInt requestServiceServiceInt;

	@Autowired
	private AddToCartServiceInt addToCartServiceInt;

	@Autowired
	private FeedbackServiceInt feedbackServiceInt;

	@ModelAttribute
	public void preload(Model model) {
		UserDTO dto = new UserDTO();
		dto.setRoleId(2L);
		model.addAttribute("employeeList", userService.getAllEmployees(dto));
		// model.addAttribute("servicesList", service.search(null));
		HashMap<String, String> mapForStatus = new HashMap<String, String>();
		mapForStatus.put("ACCEPT", "ACCEPT");
		mapForStatus.put("REJECT", "REJECT");
		model.addAttribute("statusShow", mapForStatus);
	}

	@PostMapping("/home/login/booking-employee")
	public String displayTesting(@RequestParam(required = false) Long id, @RequestParam(required = false) String status,
			@RequestParam(required = false) String serviceName, @RequestParam(required = false) String dob, Long pId,
			@ModelAttribute("form") AddToCartForm form, @ModelAttribute("form2") RequestServiceForm form2,
			HttpSession session, Model model) {
		// System.out.println("status " + status + " service id" + serviceId);
		UserDTO userDTO = (UserDTO) session.getAttribute("user");
		RequestServiceDTO dto = requestServiceServiceInt.findBypk(id);
		session.setAttribute("employeeId", dto.getUser().getId());
		Long employeeId = (Long) session.getAttribute("employeeId");
		AddToCartDTO addToCartBean = new AddToCartDTO();
		addToCartBean.setUser(userService.findBypk(employeeId));
		// addToCartBean.setService(service.findBypk(serviceId));
		addToCartBean.setUserId(employeeId);
		// addToCartBean.setServiceId(serviceId);
		addToCartBean.setServiceName(serviceName);
		addToCartBean.setStatus("requestbooking");
		addToCartBean.setCreatedBy(userDTO.getLogin());
		addToCartBean.setDateOfBooking(form.getDateOfAvailability());
		addToCartBean.setProfessionalName(userService.findBypk(employeeId).getFirstName());
		addToCartBean.setTimingRequest(form.getTimingRequest());
		try {

			long pk = addToCartServiceInt.add(addToCartBean);
			System.out.println("PK::: " + pk);
			model.addAttribute("pk", pk);
			model.addAttribute("success",
					"Booking Requested Added Successfully! Wait for the Professional to accept the request");

		} catch (DuplicateRecordException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NullPointerException e) {
			// TODO Auto-generated catch block
			model.addAttribute("errorPage", "Something Went Wrong!");
		}

		// if (form2.getId() > 0) {
		RequestServiceDTO bean = requestServiceServiceInt.findBypk(id);
		model.addAttribute("serviceId", bean.getServiceName());
		// }

		return "addToCart";
	}

	// Accept Booking Controller
	@GetMapping("/home/login/accept-booking")
	public String acceptBooking(@RequestParam(required = false) Long id, @RequestParam(required = false) String status,
			@RequestParam(required = false) Long serviceId, @RequestParam(required = false) String dob, Long pId,
			@ModelAttribute("form") AddToCartForm form, @ModelAttribute("form2") RequestServiceForm form2,
			HttpSession session, Model model) {

		AddToCartDTO addToCartBean = new AddToCartDTO();
		addToCartBean.setStatus(status);
		addToCartBean.setId(id);

		UserDTO userDTO = (UserDTO) session.getAttribute("user");
		addToCartBean.setProfessionalName(userDTO.getFirstName());
		try {

			addToCartServiceInt.accept(addToCartBean);
			if (status.equalsIgnoreCase("accept")) {
				model.addAttribute("success", "Booking Requested Accepted Successfully");
				return "redirect:/home/login/booking/search";
			} else {
				model.addAttribute("error", "Booking Requested Rejected Successfully");
				return "redirect:/home/login/booking/search";
			}

		} catch (DuplicateRecordException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NullPointerException e) {
			model.addAttribute("error", "Some Error Occurred! Please Login");
			return "errorPage";
		}

		return "bookingList";
	}

	@PostMapping("/home/login/booking")
	public String submit(@Valid @ModelAttribute("form") AddToCartForm form, BindingResult bindingResult,
			HttpSession session, Model model, HttpServletRequest request) {

		if (OP_RESET.equalsIgnoreCase(form.getOperation())) {
			return "redirect:/home/login/services";
		}

		if (OP_BOOK.equalsIgnoreCase(form.getOperation())) {

			if (bindingResult.hasErrors()) {
				return "addToCart";
			}
			Long employeeId = (Long) session.getAttribute("employeeId");
			Long serviceId = DataUtility.getLong(request.getParameter("serviceId"));
			AddToCartDTO bean = (AddToCartDTO) populateDTO(form.getDTO(), request);
			bean.setUser(userService.findBypk(employeeId));
			// bean.setService(service.findBypk(serviceId));
			bean.setUserId(employeeId);
			// bean.setServiceId(serviceId);

			try {
				addToCartServiceInt.accept(bean);
			} catch (DuplicateRecordException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			model.addAttribute("success", "Booking Done Successfully!!!!");
		}
		return "addToCart";

	}

	@RequestMapping(value = "/home/login/booking/search", method = { RequestMethod.GET, RequestMethod.POST })
	public String searchList(@ModelAttribute("form") AddToCartForm form, @RequestParam(required = false) String success,
			@RequestParam(required = false) String error, @RequestParam(required = false) String operation, Long vid,
			HttpSession session, Model model) {

		if (success != null) {
			model.addAttribute("success", "Booking Accepted");
		}
		if (error != null) {
			model.addAttribute("success", "Booking Rejected");
		}
		if (OP_RESET.equalsIgnoreCase(operation)) {
			return "redirect:/home/login/booking/search";
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
					ServicesDTO dto = new ServicesDTO();
					dto.setId(id);
					service.delete(dto);
				}
				model.addAttribute("success", "Deleted Successfully!!!");
			} else {
				model.addAttribute("error", "Select at least one record");
			}
		}
		AddToCartDTO dto = (AddToCartDTO) form.getDTO();

		List<AddToCartDTO> list = addToCartServiceInt.search(dto, pageNo, pageSize);
		List<AddToCartDTO> totallist = addToCartServiceInt.search(dto);
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
		return "bookingList";
	}

	// Show Bookings done by professional to customer
	@RequestMapping(value = "/home/login/bookings/search", method = { RequestMethod.GET, RequestMethod.POST })
	public String searchBookingList(@ModelAttribute("form") AddToCartForm form,
			@RequestParam(required = false) String success, @RequestParam(required = false) String error,
			@RequestParam(required = false) String operation, Long vid, HttpSession session, Model model) {

		if (success != null) {
			model.addAttribute("success", "Booking Accepted");
		}
		if (error != null) {
			model.addAttribute("success", "Booking Rejected");
		}
		if (OP_RESET.equalsIgnoreCase(operation)) {
			return "redirect:/home/login/bookings/search";
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
					ServicesDTO dto = new ServicesDTO();
					dto.setId(id);
					service.delete(dto);
				}
				model.addAttribute("success", "Deleted Successfully!!!");
			} else {
				model.addAttribute("error", "Select at least one record");
			}
		}
		AddToCartDTO dto = (AddToCartDTO) form.getDTO();
		UserDTO userDTO = (UserDTO) session.getAttribute("user");
		System.out.println("Login: " + userDTO.getLogin());
		dto.setCreatedBy(userDTO.getLogin());
		List<AddToCartDTO> list = addToCartServiceInt.showBookingsToCutomer(dto, pageNo, pageSize);
		List<AddToCartDTO> totallist = addToCartServiceInt.showBookingsToCustomer(dto);
		model.addAttribute("list", list);
		System.out.println("list: " + list.size());
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
		return "bookingListToCustomer";
	}

	@GetMapping("/home/login/payment")
	public String payment(@ModelAttribute("form") AddToCartForm form, @RequestParam("id") Long id, Model model) {
		System.out.println("Id::: " + id);
		AddToCartDTO findBypk = addToCartServiceInt.findBypk(id);
		String status = findBypk.getStatus();
		System.out.println("Status:::: " + status);
		model.addAttribute("status", status);
		model.addAttribute("form", form);
		return "payment";
	}

	@PostMapping("/home/login/payment")
	public String paymentBooking(@ModelAttribute("form") AddToCartForm form, Model model) {
		model.addAttribute("success", "Booking Done Successfully");
		return "payment";
	}

}
