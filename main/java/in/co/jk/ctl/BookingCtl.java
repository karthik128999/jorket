package in.co.jk.ctl;

import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.toCollection;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.TreeSet;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import in.co.jk.dto.AddToCartDTO;
import in.co.jk.dto.ServicesDTO;
import in.co.jk.form.AddToCartForm;
import in.co.jk.services.AddToCartServiceInt;
import in.co.jk.services.ServicesServiceInt;

@Controller
public class BookingCtl extends BaseCtl {

	private Logger log = Logger.getLogger(BookingCtl.class.getName());
	@Autowired
	private AddToCartServiceInt addToCartServiceInt;
	@Autowired
	private ServicesServiceInt servicesServiceInt;

	@ModelAttribute
	public void preload(Model model) {

		HashMap<String, String> map2 = new HashMap<String, String>();
		ServicesDTO dto = new ServicesDTO();
		List<ServicesDTO> collect = servicesServiceInt.search(dto).stream().collect(collectingAndThen(
				toCollection(() -> new TreeSet<>(comparing(ServicesDTO::getServiceName))), ArrayList::new));
		System.out.println("coolect" + collect);
		model.addAttribute("serviceList", collect);
	}

	@RequestMapping(value = "/home/login/booking-history", method = { RequestMethod.GET, RequestMethod.POST })
	public String showBookingHistory(@ModelAttribute("form") AddToCartForm form, Model model,
			HttpServletRequest request) {
		int pageNo = form.getPageNo();
		int pageSize = form.getPageSize();

		String firstName = request.getParameter("fname");
		System.out.println("fname: " + firstName);

		AddToCartDTO dto = (AddToCartDTO) form.getDTO();
		dto.setProfessionalName(request.getParameter("fname"));
		Date date = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/M/yyyy");
		String formateDate = dateFormat.format(date);
		System.out.println(formateDate);
		//dto.setDateOfBooking(formateDate);
		List<AddToCartDTO> list = addToCartServiceInt.searchBooking(dto, pageNo, pageSize);
		List<AddToCartDTO> totallist = addToCartServiceInt.searchBooking(dto);
		model.addAttribute("list", list);

		if (list.size() == 0) {
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
		addToCartServiceInt.search(null);
		return "bookingHistory";
	}

	@RequestMapping(value = "/home/login/booking-upcoming", method = { RequestMethod.GET, RequestMethod.POST })
	public String showBookingUpcoming(@ModelAttribute("form") AddToCartForm form, Model model, HttpServletRequest request) {
		int pageNo = form.getPageNo();
		int pageSize = form.getPageSize();
		System.out.println("name"+request.getParameter("fname"));
		
		AddToCartDTO dto = (AddToCartDTO) form.getDTO();
		dto.setProfessionalName(request.getParameter("fname"));
		Date date = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/M/yyyy");
		String formateDate = dateFormat.format(date);
		System.out.println(formateDate);
		//dto.setDateOfBooking(formateDate);
		List<AddToCartDTO> list = addToCartServiceInt.searchUpcomingBooking(dto, pageNo, pageSize);
		List<AddToCartDTO> totallist = addToCartServiceInt.searchUpcomingBooking(dto);
		model.addAttribute("list", list);

		if (list.size() == 0) {
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
		addToCartServiceInt.search(null);
		return "upcomingBooking";
	}

	@RequestMapping(value = "/home/login/today-booking", method = { RequestMethod.GET, RequestMethod.POST })
	public String showTodayBooking(@ModelAttribute("form") AddToCartForm form, Model model, HttpServletRequest request) {
		int pageNo = form.getPageNo();
		int pageSize = form.getPageSize();
		AddToCartDTO dto = (AddToCartDTO) form.getDTO();
		dto.setProfessionalName(request.getParameter("fname"));
		Date date = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/M/yyyy");
		String formateDate = dateFormat.format(date);
		System.out.println(formateDate);
		//dto.setDateOfBooking(formateDate);
		List<AddToCartDTO> list = addToCartServiceInt.searchTodayBooking(dto, pageNo, pageSize);
		List<AddToCartDTO> totallist = addToCartServiceInt.searchTodayBooking(dto);
		model.addAttribute("list", list);

		if (list.size() == 0) {
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
		addToCartServiceInt.search(null);
		return "todaysBooking";
	}

	@RequestMapping(value = "/home/login/statistics/search", method = { RequestMethod.GET, RequestMethod.POST })
	public String searchList(@ModelAttribute("form") AddToCartForm form,

			@RequestParam(required = false) String operation, Long vid, HttpSession session, Model model) {

		System.out.println("Service: " + form.getServiceId() + "date");
		if (OP_RESET.equalsIgnoreCase(operation)) {
			return "redirect:/home/login/statistics/search";
		}
		System.out.println("OP: " + operation);
		int pageNo = form.getPageNo();
		int pageSize = form.getPageSize();

		if (OP_NEXT.equals(operation)) {
			pageNo++;
		} else if (OP_PREVIOUS.equals(operation)) {
			pageNo--;
		}
		pageNo = (pageNo < 1) ? 1 : pageNo;
		pageSize = (pageSize < 1) ? 8 : pageSize;

		AddToCartDTO dto = (AddToCartDTO) form.getDTO();
		// dto.setServiceId(form.getServiceId());
		dto.setServiceName(form.getServiceName());
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
		return "statistics";
	}
}
