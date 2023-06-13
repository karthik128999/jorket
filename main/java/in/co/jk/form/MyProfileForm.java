package in.co.jk.form;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

import org.springframework.web.multipart.MultipartFile;

import in.co.jk.dto.BaseDTO;
import in.co.jk.dto.UserDTO;
import lombok.Getter;
import lombok.Setter;
@Setter
@Getter
public class MyProfileForm extends BaseForm {

	@NotEmpty(message = "First Name is required")
	@Pattern(regexp = "(^[A-Za-z ]*)*$",message = "First Name is Invalid")
	private String firstName;
	@NotEmpty(message = "Last Name is required")
	@Pattern(regexp = "(^[A-Za-z ]*)*$",message = "Last Name is Invalid")
	private String lastName;
	@NotEmpty(message = "Gender is required")
	private String gender;
	@NotEmpty(message = "Phone No is required")
	@Pattern(regexp="(^[7-9][0-9]{9})*$",message = "MobileNo is Invalid")
	private String mobileNo;
	@NotEmpty(message = "Email Id is required")
	@Pattern(regexp="(^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,}))*$",message = "Email id is invalid")
	private String login;
	@NotEmpty(message = "Password is required")
	private String password;
	
	private MultipartFile image;
	@Override
	public BaseDTO getDTO() {
		// TODO Auto-generated method stub
		UserDTO bean = new UserDTO();
		bean.setId(id);
		bean.setFirstName(firstName);
		bean.setLastName(lastName);
		bean.setContactNo(mobileNo);
		bean.setGender(gender);
		bean.setPassword(password);
		bean.setLogin(login);
		bean.setCreatedBy(createdBy);
		bean.setModifiedBy(modifiedBy);
		bean.setCreatedDatetime(createdDateTime);
		bean.setModifiedDatetime(modifiedDateTime);
		return bean;
	}

	@Override
	public void populate(BaseDTO bDto) {
		// TODO Auto-generated method stub
		UserDTO bean=(UserDTO) bDto;
		id = bean.getId();
		firstName = bean.getFirstName();
		lastName = bean.getLastName();
		mobileNo = bean.getContactNo();
		gender = bean.getGender();
		password = bean.getPassword();
		login = bean.getLogin();
		createdBy=bean.getCreatedBy();
		modifiedBy=bean.getModifiedBy();
		createdDateTime=bean.getCreatedDatetime();
		modifiedDateTime=bean.getModifiedDatetime();
	}

}
