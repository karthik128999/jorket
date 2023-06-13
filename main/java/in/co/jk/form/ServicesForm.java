package in.co.jk.form;

import javax.validation.constraints.NotEmpty;

import org.springframework.web.multipart.MultipartFile;

import in.co.jk.dto.BaseDTO;
import in.co.jk.dto.ServicesDTO;
import lombok.Getter;
import lombok.Setter;
@Setter
@Getter
public class ServicesForm extends BaseForm {

	@NotEmpty(message = "Service Name is required")
	private String serviceName;
	
	private MultipartFile serviceImage;
	
	@NotEmpty(message = "City is required")
	private String city;
	
	@Override
	public BaseDTO getDTO() {
		ServicesDTO bean = new ServicesDTO();
		bean.setId(id);
		bean.setServiceName(serviceName);
		bean.setCity(city);
		bean.setCreatedBy(createdBy);
		bean.setModifiedBy(modifiedBy);
		bean.setCreatedDatetime(createdDateTime);
		bean.setModifiedDatetime(modifiedDateTime);
		return bean;
	}

	@Override
	public void populate(BaseDTO bDto) {
		// TODO Auto-generated method stub
		ServicesDTO bean = (ServicesDTO)bDto;
		id = bean.getId();
		serviceName = bean.getServiceName();
		city = bean.getCity();
		createdBy=bean.getCreatedBy();
		modifiedBy=bean.getModifiedBy();
		createdDateTime=bean.getCreatedDatetime();
		modifiedDateTime=bean.getModifiedDatetime();
	}

}
