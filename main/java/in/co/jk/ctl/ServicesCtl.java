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

import in.co.jk.dto.ServicesDTO;
import in.co.jk.exception.DuplicateRecordException;
import in.co.jk.exception.RecordNotFoundException;
import in.co.jk.form.ServicesForm;
import in.co.jk.services.ServicesServiceInt;



@Controller
public class ServicesCtl extends BaseCtl {

	private Logger log = Logger.getLogger(ServicesCtl.class.getName());
	@Autowired
	private ServicesServiceInt service;
	
	@GetMapping("/home/login/services")
	public String display(@RequestParam(required = false) Long id, Long pId, @ModelAttribute("form") ServicesForm form,
			HttpSession session, Model model) {
		if (form.getId() > 0) {
			ServicesDTO bean = service.findBypk(id);
			form.populate(bean);
		}
		return "services";
	}

	@PostMapping("/home/login/services")
	public String submit(@Valid @ModelAttribute("form") ServicesForm form, BindingResult bindingResult,
			HttpSession session, Model model,HttpServletRequest request) {

		if (OP_RESET.equalsIgnoreCase(form.getOperation())) {
			return "redirect:/home/login/services";
		}
		
		try {
			if (OP_SAVE.equalsIgnoreCase(form.getOperation())) {

				if (bindingResult.hasErrors()) {
					return "services";
				}
				ServicesDTO bean = (ServicesDTO) populateDTO(form.getDTO(),request);
				bean.setServiceImage(form.getServiceImage().getBytes());
				if (bean.getId() > 0) {
					service.update(bean);
					model.addAttribute("success", "Service update Successfully!!!!");
				} else {
					service.add(bean);
					model.addAttribute("success", "Service Added Successfully!!!!");
				}
				return "services";
			}
		} catch (DuplicateRecordException e) {
			model.addAttribute("error", e.getMessage());
			return "services";
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "";
	}

	@RequestMapping(value = "/home/login/services/search", method = { RequestMethod.GET, RequestMethod.POST })
	public String searchList(@ModelAttribute("form") ServicesForm form,
			@RequestParam(required = false) String operation, Long vid, HttpSession session, Model model) {

		if (OP_RESET.equalsIgnoreCase(operation)) {
			return "redirect:/home/login/services/search";
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
		ServicesDTO dto = (ServicesDTO) form.getDTO();
		
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
		return "servicesList";
	}
	
	@GetMapping("/image")
	 public void showImage(@Param("id") Long id, HttpServletResponse response, ServicesDTO v)
	   throws ServletException, IOException, RecordNotFoundException {
		v = service.findBypk(id);
		response.setContentType("image/jpeg, image/jpg, image/png, image/gif");
		response.getOutputStream().write(v.getServiceImage());
		response.getOutputStream().close();
	 }
	
}
