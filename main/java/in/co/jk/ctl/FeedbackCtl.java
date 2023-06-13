package in.co.jk.ctl;

import java.util.HashMap;
import java.util.List;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

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

import in.co.jk.dto.FeedbackDTO;
import in.co.jk.dto.UserDTO;
import in.co.jk.exception.DuplicateRecordException;
import in.co.jk.form.FeedbackForm;
import in.co.jk.form.ServicesForm;
import in.co.jk.services.FeedbackServiceInt;
import in.co.jk.services.ServicesServiceInt;
import in.co.jk.services.UserService;

@Controller
public class FeedbackCtl extends BaseCtl {

	private Logger log = Logger.getLogger(FeedbackCtl.class.getName());
	@Autowired
	private ServicesServiceInt feedbackServiceInt;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private FeedbackServiceInt service;
	
	@ModelAttribute
	public void preload(Model model) {

		HashMap<String, String> map2 = new HashMap<String, String>();
		map2.put("1", "1");
		map2.put("2", "2");
		map2.put("3", "3");
		map2.put("4", "4");
		map2.put("5", "5");
		model.addAttribute("rate", map2);
	}
	
	@GetMapping("/home/login/feedback")
	public String display(@RequestParam(required = false) Long id, Long pId, @ModelAttribute("form") FeedbackForm form,
			HttpSession session, Model model,HttpServletRequest request) {
		System.out.println("uid >>"+id);
		session.setAttribute("uid", id);
		return "feedback";
	}

	@PostMapping("/home/login/feedback")
	public String submit(@Valid @ModelAttribute("form") FeedbackForm form, BindingResult bindingResult,
			HttpSession session, Model model,HttpServletRequest request) {

		if (OP_RESET.equalsIgnoreCase(form.getOperation())) {
			return "redirect:/home/login/feedback";
		}
		
		try {
			if (OP_SAVE.equalsIgnoreCase(form.getOperation())) {

				if (bindingResult.hasErrors()) {
					return "feedback";
				}
				UserDTO dto = (UserDTO)session.getAttribute("user");
				FeedbackDTO bean = (FeedbackDTO) populateDTO(form.getDTO(),request);
				Long uid = (Long)session.getAttribute("uid");
				bean.setUser(userService.findBypk(uid));
				bean.setUserId(uid);
				bean.setEmployerId(dto.getId());
				if (bean.getId() > 0) {
					service.update(bean);
					model.addAttribute("success", "Feedback update Successfully!!!!");
				} else {
					service.add(bean);
					model.addAttribute("success", "Feedback Added Successfully!!!!");
				}
				return "feedback";
			}
		} catch (DuplicateRecordException e) {
			model.addAttribute("error", e.getMessage());
			return "feedback";
		}
		return "";
	}

	@RequestMapping(value = "/home/login/feedback/search", method = { RequestMethod.GET, RequestMethod.POST })
	public String searchList(@ModelAttribute("form") FeedbackForm form,
			@RequestParam(required = false) String operation,
			@RequestParam(required = false) String fid,
			@RequestParam(required = false) Long userId,Long vid, HttpSession session, Model model) {

		if (OP_RESET.equalsIgnoreCase(operation)) {
			return "redirect:/home/login/feedback/search";
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
					FeedbackDTO dto = new FeedbackDTO();
					dto.setId(id);
					service.delete(dto);
				}
				model.addAttribute("success", "Deleted Successfully!!!");
			} else {
				model.addAttribute("error", "Select at least one record");
			}
		}
		FeedbackDTO dto = (FeedbackDTO) form.getDTO();
		if(fid!=null) {
			UserDTO userDTO = userService.findByLogin(fid);
			dto.setCreatedBy(userDTO.getLogin());
		}
		if(userId!=null) {
			UserDTO userDTO = userService.findBypk(userId);
			dto.setUserId(userDTO.getId());
		}
		System.out.println("user id "+dto.getUserId());
		List<FeedbackDTO> list = service.search(dto, pageNo, pageSize);
		List<FeedbackDTO> totallist = service.search(dto);
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
		return "feedbackList";
	}
	
	
}
