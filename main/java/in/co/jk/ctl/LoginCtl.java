package in.co.jk.ctl;


import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.mail.MailException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import in.co.jk.dto.ServicesDTO;
import in.co.jk.dto.UserDTO;
import in.co.jk.exception.DuplicateRecordException;
import in.co.jk.exception.RecordNotFoundException;
import in.co.jk.form.ChangePasswordForm;
import in.co.jk.form.EmployerRegistrationForm;
import in.co.jk.form.ForgotPasswordForm;
import in.co.jk.form.LoginForm;
import in.co.jk.form.MyProfileForm;
import in.co.jk.form.UserRegistrationForm;
import in.co.jk.services.UserService;
import in.co.jk.util.DataUtility;




@Controller
public class LoginCtl extends BaseCtl{

	protected static final String OP_SIGNIN = "SignIn";
	protected static final String OP_SIGNUP = "SignUp";
	protected static final String OP_LOGOUT = "Logout";
	private Logger log = Logger.getLogger(LoginCtl.class.getName());
	@Autowired
	private UserService userService;
	@ModelAttribute
	public void preload(Model model) {

		HashMap<String, String> map2 = new HashMap<String, String>();
		map2.put("Male", "Male");
		map2.put("Female", "Female");
		map2.put("Other", "Other");
		map2.put("Prefer not to say", "Prefer not to say");
		model.addAttribute("gender", map2);
		
		HashMap<String, String> map3 = new HashMap<String, String>();
		map3.put("Carpenter", "Carpenter");
		map3.put("Carwash", "Carwash");
		map3.put("Chef", "Chef");
		map3.put("Decor", "Decor");
		map3.put("Delivery", "Delivery");
		map3.put("Driver", "Driver");
		map3.put("Electrician", "Electrician");
		map3.put("HouseKeeping", "HouseKeeping");
		map3.put("Lawn Cutter", "Lawn Cutter");
		map3.put("Mason", "Mason");
		map3.put("Mechanic", "Mechanic");
		map3.put("Painter", "Painter");
		map3.put("Plumber", "Plumber");
		map3.put("Saloon", "Saloon");
		map3.put("Sanitation Worker", "Sanitation Worker");
		map3.put("Snow Cleaner", "Snow Cleaner");
		map3.put("Welder", "Welder");
		model.addAttribute("skills", map3);

		HashMap<String, String> map4 = new HashMap<String, String>();
		map4.put("00164", "00164");
		map4.put("00059", "00059");
		map4.put("54011", "54011");
		map4.put("53804", "53804");
		map4.put("51510", "51510");
		map4.put("57003", "57003");
		model.addAttribute("zipCode", map4);
		
		HashMap<String, String> mapForRole = new HashMap<String, String>();
		mapForRole.put("PROFESSIONAL", "PROFESSIONAL");
		mapForRole.put("CUSTOMER", "CUSTOMER");
		model.addAttribute("roles", mapForRole);

	}

	
	
	@GetMapping("/home/employee-register")
	public String display(@ModelAttribute("form") UserRegistrationForm form, Model model) {
		log.info("LoginCtl signUp display method start");
		log.info("LoginCtl signUp display method End");
		return "employeeSignUp";
	}

	@PostMapping("/home/employee-register")
	public String submit(@RequestParam String operation, @Valid @ModelAttribute("form") UserRegistrationForm form,
			BindingResult bindingResult, Model model, HttpServletRequest request) throws IOException {

		log.info("LoginCtl signUp submit method start");

		if (OP_RESET.equalsIgnoreCase(form.getOperation())) {
			return "redirect:/home/employee-register";
		}

		if (bindingResult.hasErrors()) {
			
			System.out.println(bindingResult);
			return "employeeSignUp";
		}
		
		if(!form.getPassword().equals(form.getConfirmPassword())) {
			model.addAttribute("error", "Password and Confirm Password are not matched");
			return "employeeSignUp";
		}
		
		try {
			if (OP_SAVE.equalsIgnoreCase(form.getOperation())) {
				UserDTO entity = (UserDTO) populateDTO(form.getDTO(), request);
				entity.setImage(form.getImage().getBytes());
				entity.setKyc(form.getKyc().getBytes());
				entity.setRoleId(2L);
				entity.setRoleName("PROFESSIONAL");
				
				userService.add(entity);
				
				model.addAttribute("success", "Professional Registered Successfully!!!!");
				return "employeeSignUp";
			}
		} catch (DuplicateRecordException e) {
			model.addAttribute("error", e.getMessage());
			return "employeeSignUp";
		}

		log.info("LoginCtl signUp submit method end");
		return "employeeSignUp";
	}


	@GetMapping("/home/employer-register")
	public String displayNGO(@ModelAttribute("form") EmployerRegistrationForm form,
			Model model) {
		log.info("LoginCtl signUp display method start");
		log.info("LoginCtl signUp display method End");
		return "employerSignUp";
	}

	@PostMapping("/home/employer-register")
	public String submitNGO(@RequestParam String operation, @Valid @ModelAttribute("form") EmployerRegistrationForm form,
			BindingResult bindingResult, Model model, HttpServletRequest request) throws IOException {

		log.info("LoginCtl employer signup submit method start");

		if (OP_RESET.equalsIgnoreCase(form.getOperation())) {
			return "redirect:/home/employer-register";
		}

		if (bindingResult.hasErrors()) {
			System.out.println(bindingResult);
			return "employerSignUp";
		}
		if(!form.getPassword().equals(form.getConfirmPassword())) {
			model.addAttribute("error", "Password and Confirm Password are not matched");
			return "employerSignUp";
		}
		try {
			if (OP_SAVE.equalsIgnoreCase(form.getOperation())) {
				UserDTO entity = (UserDTO) populateDTO(form.getDTO(), request);
				System.out.println("ENtity " + entity.getFirstName());
				entity.setRoleId(3L);
				entity.setRoleName("CUSTOMER");
				entity.setImage(form.getImage().getBytes());
				userService.add(entity);
				model.addAttribute("success", "Customer Registerd Successfully!!!!");
				return "employerSignUp";
			}
		} catch (DuplicateRecordException e) {
			model.addAttribute("error", e.getMessage());
			return "employerSignUp";
		}

		log.info("LoginCtl signUp submit method end");
		return "employerSignUp";
	}


	@GetMapping("/home/login")
	public String display(@ModelAttribute("form") LoginForm form, HttpSession session, Model model) {
		log.info("LoginCtl login display method start");
		if (session.getAttribute("user") != null) {
			session.invalidate();
			model.addAttribute("success", "You have logout Successfully!!!");
		}

		log.info("LoginCtl login display method End");
		return "login";
	}

	@PostMapping("/home/login")
	public String submit(@RequestParam String operation, HttpSession session,
			@Valid @ModelAttribute("form") LoginForm form, BindingResult result, Model model) {
		log.info("LoginCtl login submit method start");
		System.out.println("In dopost  LoginCtl");

		if (OP_RESET.equalsIgnoreCase(form.getOperation())) {
			return "redirect:/home/login";
		}

		if (OP_SIGNUP.equalsIgnoreCase(form.getOperation())) {
			return "redirect:/home/login";
		}

		if (result.hasErrors()) {
			System.out.println(result);
			return "login";
		}

		UserDTO bean = userService.authentication((UserDTO)form.getDTO());

		if (bean != null) {
			
			System.out.println(bean.toString());
			session.setAttribute("user", bean);
			return "redirect:/home";
		}
		// System.out.println("status "+bean.getStatus());

		if (bean == null) {

			model.addAttribute("error", "Login Id & Password Invalid");
		}
		log.info("LoginCtl login submit method End");
		return "login";
	}
//  End of login module	

	@RequestMapping(value = "/home/login/users/search", method = { RequestMethod.GET, RequestMethod.POST })
	public String searchList(@ModelAttribute("form") UserRegistrationForm form,
			@RequestParam(required = false) String operation, Long vid, HttpSession session, Model model) {

		if (OP_RESET.equalsIgnoreCase(operation)) {
			return "redirect:/home/login/users/search";
		}

		int pageNo = form.getPageNo();
		int pageSize = form.getPageSize();

		if (OP_NEXT.equals(operation)) {
			pageNo++;
		} else if (OP_PREVIOUS.equals(operation)) {
			pageNo--;
		} else if (OP_NEW.equals(operation)) {
			return "redirect:/home/user";
		}

		pageNo = (pageNo < 1) ? 1 : pageNo;
		pageSize = (pageSize < 1) ? 8 : pageSize;

		if (OP_DELETE.equals(operation)) {
			pageNo = 1;
			if (form.getIds() != null) {
				for (long id : form.getIds()) {
					UserDTO dto = new UserDTO();
					dto.setId(id);
					userService.delete(dto);
				}
				model.addAttribute("success", "Deleted Successfully!!!");
			} else {
				model.addAttribute("error", "Select at least one record");
			}
		}
		
		UserDTO dto = (UserDTO) form.getDTO();
		
		UserDTO uDto = (UserDTO) session.getAttribute("user");
		
		List<UserDTO> list = userService.search(dto, pageNo, pageSize);
		List<UserDTO> totallist = userService.search(dto);
		
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
		return "userList";
	}
	
	@RequestMapping(value = "/home/login/customers/search", method = { RequestMethod.GET, RequestMethod.POST })
	public String searchCustomersList(@ModelAttribute("form") UserRegistrationForm form,
			@RequestParam(required = false) String operation, Long vid, HttpSession session, Model model) {

		if (OP_RESET.equalsIgnoreCase(operation)) {
			return "redirect:/home/login/customers/search";
		}

		int pageNo = form.getPageNo();
		int pageSize = form.getPageSize();

		if (OP_NEXT.equals(operation)) {
			pageNo++;
		} else if (OP_PREVIOUS.equals(operation)) {
			pageNo--;
		} else if (OP_NEW.equals(operation)) {
			return "redirect:/home/user";
		}

		pageNo = (pageNo < 1) ? 1 : pageNo;
		pageSize = (pageSize < 1) ? 8 : pageSize;

		if (OP_DELETE.equals(operation)) {
			pageNo = 1;
			if (form.getIds() != null) {
				for (long id : form.getIds()) {
					UserDTO dto = new UserDTO();
					dto.setId(id);
					userService.delete(dto);
				}
				model.addAttribute("success", "Deleted Successfully!!!");
			} else {
				model.addAttribute("error", "Select at least one record");
			}
		}
		
		UserDTO dto = (UserDTO) form.getDTO();
		
		UserDTO uDto = (UserDTO) session.getAttribute("user");
		
		List<UserDTO> list = userService.searchCustomers(dto, pageNo, pageSize);
		List<UserDTO> totallist = userService.searchCustomers(dto);
		
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
		return "customerList";
	}
	
	@RequestMapping(value = "/home/login/myprofile", method = RequestMethod.GET)
	public String displayMyProfile(@ModelAttribute("form") MyProfileForm form, HttpSession session, Model model) {
		UserDTO dto = (UserDTO) session.getAttribute("user");
		long id = dto.getId();
		UserDTO bean = userService.findBypk(id);
		form.populate(bean);
		return "myProfile";
	}

	@RequestMapping(value = "/home/login/myprofile", method = RequestMethod.POST)
	public String submitMyProfile(HttpSession session, @ModelAttribute("form") @Valid MyProfileForm form,
			BindingResult bindingResult, Model model, HttpServletRequest request) throws DuplicateRecordException {

		if (OP_RESET.equalsIgnoreCase(form.getOperation())) {
			return "redirect:/home/login/myprofile";
		}

		if (OP_SAVE.equalsIgnoreCase(form.getOperation())) {
			if (bindingResult.hasErrors()) {
				System.out.println("I am heer"+bindingResult.getAllErrors());
				return "myProfile";
		}
			System.out.println("I am heer");
			UserDTO bean = (UserDTO) populateDTO(form.getDTO(), request);
			UserDTO uDto = (UserDTO) session.getAttribute("user");
			long id = uDto.getId();
			System.out.println("bean id: " + id);
			if (id > 0) {
				uDto.setFirstName(bean.getFirstName());
				uDto.setLastName(bean.getLastName());
				uDto.setGender(bean.getGender());
				uDto.setContactNo(bean.getContactNo());
				uDto.setPassword(bean.getPassword());
				uDto.setLogin(bean.getLogin());

				userService.update(uDto);
				model.addAttribute("success", "Profile Updated Successfully!!!!");
			}

			return "myProfile";
		}
		return "";
	}
	
	@RequestMapping(value = "/home/login/forgetPassword", method = RequestMethod.GET)
	public String display(@ModelAttribute("form") ForgotPasswordForm form, HttpSession session, Model model) {

		System.out.println("In doget LoginCtl forgetpassword");

		return "forgetPassword";

	}

	@RequestMapping(value = "/home/login/forgetPassword", method = RequestMethod.POST)
	public String display(@ModelAttribute("form") @Valid ForgotPasswordForm form, BindingResult bindingResult,
			Model model, HttpSession session) {

		if (bindingResult.hasErrors()) {
			return "forgetPassword";
		}
			
		UserDTO dto = userService.findByLogin(form.getLogin());
		
		try {
			if (dto == null) {
				
				model.addAttribute("error", "Invalid Input Entered");
				return "forgetPassword";
			}

			if (dto != null) {
				userService.sendMail(dto);
				model.addAttribute("success", "Password has been sent to your registered Email ID!!");
				 if(!DataUtility.getStringData(dto.getLogin()).equalsIgnoreCase(form.getLogin()) ) {
						model.addAttribute("error", "Incorrect details matched");
						return "forgetPassword";
					}
				
				model.addAttribute("success", "Password is Sent to Email id");
			}
		}catch (NullPointerException e) {
			model.addAttribute("error", "Invalid Data");
			return "forgetPassword";
		}catch (MailException e) {
			model.addAttribute("error", "Error in sending mail. Please try again!");
			return "forgetPassword";
		}
		
		return "forgetPassword";
	}
	@RequestMapping(value = "/home/login/changepassword", method = RequestMethod.GET)
	public String displayChangePassword(@ModelAttribute("form") ChangePasswordForm form, HttpSession session,
			Model model) {

		return "changePassword";

	}

	@RequestMapping(value = "/home/login/changepassword", method = RequestMethod.POST)
	public String submitChangePassword(HttpSession session, @ModelAttribute("form") @Valid ChangePasswordForm form,
			BindingResult bindingResult, Model model) {
		if (OP_RESET.equalsIgnoreCase(form.getOperation())) {
			return "redirect:/home/login/changepassword";
		}
		if (bindingResult.hasErrors()) {
			System.out.println("erro" + bindingResult.getAllErrors());
			return "changePassword";
		}

		if (form.getNewPassword().equalsIgnoreCase(form.getConfirmPassword())) {

			UserDTO dto = (UserDTO) session.getAttribute("user");
			dto = userService.findBypk(dto.getId());

			if (userService.changePassword(dto.getId(), form.getOldPassword(), form.getNewPassword())) {
				model.addAttribute("success", "Password changed Successfully");
			} else {
				model.addAttribute("error", "Old Password Does not Matched");
			}
		} else {
			model.addAttribute("error", "New Password and confirm password does not matched");
		}
		return "changePassword";
	}
	@GetMapping("/user/image")
	 public void showImage(@Param("id") Long id, HttpServletResponse response, UserDTO v)
	   throws ServletException, IOException, RecordNotFoundException {
		v = userService.findBypk(id);
		response.setContentType("image/jpeg, image/jpg, image/png, image/gif");
		response.getOutputStream().write(v.getImage());
		response.getOutputStream().close();
	 }
	@GetMapping("/user/kyc")
	 public void showPDF(@Param("id") Long id, HttpServletResponse response, UserDTO v)
	   throws ServletException, IOException, RecordNotFoundException {
		v = userService.findBypk(id);
		response.setContentType("application/pdf, application/docx, application/msword");
		response.getOutputStream().write(v.getKyc());
		response.getOutputStream().close();
	 }
}
