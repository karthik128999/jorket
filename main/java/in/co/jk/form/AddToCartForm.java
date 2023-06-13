package in.co.jk.form;

import in.co.jk.dto.AddToCartDTO;
import in.co.jk.dto.BaseDTO;
import in.co.jk.dto.ServicesDTO;
import in.co.jk.dto.UserDTO;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class AddToCartForm extends BaseForm {
	private String dateOfBooking;

	private Long userId;
	
	private String serviceName;
	
	private String professionalName;

	private String status;
	
	private UserDTO user;

	private String dateOfAvailability;
	
	private String timingRequest;
	private Long serviceId;

	
	private ServicesDTO service;

	@Override
	public BaseDTO getDTO() {
		// TODO Auto-generated method stub
		AddToCartDTO bean = new AddToCartDTO();
		bean.setId(id);
		bean.setDateOfBooking(dateOfBooking);
		//bean.setService(service);
	//	bean.setServiceId(serviceId);
		bean.setUser(user);
		bean.setUserId(userId);
		bean.setStatus(status);
		bean.setCreatedBy(createdBy);
		bean.setModifiedBy(modifiedBy);
		bean.setCreatedDatetime(createdDateTime);
		bean.setModifiedDatetime(modifiedDateTime);
		bean.setServiceName(serviceName);
		bean.setProfessionalName(professionalName);
		return bean;
	}

	@Override
	public void populate(BaseDTO bDto) {
		// TODO Auto-generated method stub
		AddToCartDTO bean = (AddToCartDTO)bDto;
		id = bean.getId();
		dateOfBooking = bean.getDateOfBooking();
	//	service = bean.getService();
		//serviceId = bean.getServiceId();
		user = bean.getUser();
		userId = bean.getUserId();
		status = bean.getStatus();
		createdBy=bean.getCreatedBy();
		modifiedBy=bean.getModifiedBy();
		createdDateTime=bean.getCreatedDatetime();
		modifiedDateTime=bean.getModifiedDatetime();
		serviceName = bean.getServiceName();
		professionalName = bean.getProfessionalName();
	}

}
