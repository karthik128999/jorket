package in.co.jk.form;

import java.math.BigDecimal;
import java.time.LocalTime;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;



import in.co.jk.dto.BaseDTO;
import in.co.jk.dto.RequestServiceDTO;
import in.co.jk.dto.ServicesDTO;
import in.co.jk.dto.UserDTO;
import lombok.Getter;
import lombok.Setter;
@Setter
@Getter
public class RequestServiceForm extends BaseForm {

	private BigDecimal costPerHour;
	@NotEmpty(message = "Date Of Booking is required")
	private String dateOfBooking;
	
	private MultipartFile image;	
	
	private UserDTO user;
	
	private Long userId;
	
	private ServicesDTO service;
	
	private Long serviceId;
	
	@DateTimeFormat(pattern = "HH:MM")
	@NotEmpty(message = "Start Up Time is required")	
	private String timeSlotsStart;

	@DateTimeFormat(pattern = "HH:MM")
	@NotEmpty(message = "End Time is required")
	private String timeSlotsEnds;
	
	@NotEmpty(message = "Please Select Service Name")
	private String serviceName;
	
	private String city;
	
	private String dateOfAvailability;
	
	private String timingRequest;
	
	@Override
	public BaseDTO getDTO() {
		// TODO Auto-generated method stub
		RequestServiceDTO bean = new RequestServiceDTO();
		bean.setId(id);
		bean.setCostPerHour(costPerHour);
		//bean.setService(service);
		bean.setUser(user);
		//bean.setServiceId(serviceId);
		bean.setTimeSlotsStart(timeSlotsStart);
		bean.setUserId(userId);
		bean.setTimeSlotsEnds(timeSlotsEnds);
		bean.setDateOfBooking(dateOfBooking);
		bean.setCreatedBy(createdBy);
		bean.setModifiedBy(modifiedBy);
		bean.setCreatedDatetime(createdDateTime);
		bean.setModifiedDatetime(modifiedDateTime);
		bean.setServiceName(serviceName);
		bean.setCity(city);
		return bean;
	}

	@Override
	public void populate(BaseDTO bDto) {
		// TODO Auto-generated method stub
		RequestServiceDTO bean = (RequestServiceDTO)bDto;
		id = bean.getId();
		costPerHour = bean.getCostPerHour();
		//service = bean.getService();
		//serviceId = bean.getServiceId();
		user = bean.getUser();
		userId = bean.getUserId();
		timeSlotsStart = bean.getTimeSlotsStart();
		timeSlotsEnds = bean.getTimeSlotsEnds();
		dateOfBooking = bean.getDateOfBooking();
		createdBy=bean.getCreatedBy();
		modifiedBy=bean.getModifiedBy();
		createdDateTime=bean.getCreatedDatetime();
		modifiedDateTime=bean.getModifiedDatetime();
		serviceName = bean.getServiceName();
		city = bean.getCity();
	}

}
